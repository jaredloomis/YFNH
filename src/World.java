package net.fiendfan1.src;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

public class World 
{
	public GameObject data[][];
	public List<GameObject> objects;
	int width;
	int height;

	public World()
	{
		width = 1280;
		height = 720;
		data = new GameObject[width][height];
		objects = new ArrayList<GameObject>();
	}

	public void addToWorld(GameObject go)
	{
		objects.add(go);
	}

	public void transformObject(GameObject go, int x, int y)
	{
		int w = go.texture.getWidth();
		int h = go.texture.getHeight();

		if(x+w < 1280 && x >= 0 && y >= 0 && y+h < 720)
		{
			if(canPlaceHere(go, x, y))
			{
				go.position.x = x;
				go.position.y = y;
			}
		}
	}

	public boolean canPlaceHere(GameObject go, int x, int y)
	{
		boolean ret = true;
		int w = go.texture.getWidth();
		int h = go.texture.getHeight();

		for(int x1 = x; x1 < x+w; x1++)
		{
			if(!ret)
				break;

			for(int y1 = y; y1 < y+h; y1++)
			{
				if(x1 <= 1280 && x1 >= 0 && y1 < 720 && y1 >= 0)
				{
					if(data[x1][y1] != null && data[x1][y1] != go)
					{
						if(!(go.id == 1 && data[x1][y1].id == 1))
						{
							if(data[x1][y1].collide())
							{
								ret = false;
								break;
							}
						}
					}
				}
				else
				{
					ret = false;
				}
			}
		}

		return ret;
	}

	public void renderObjects(Graphics g)
	{
		for(int i = 0; i < objects.size(); i++)
		{
			GameObject cur = objects.get(i);

			g.drawImage(cur.texture, cur.position.x, cur.position.y);
		}
	}

	/*Updates array of ids in the world*/
	public void updateData()
	{
		for(int i = 0; i < objects.size(); i++)
		{
			GameObject cur = objects.get(i);
			cur.updateHistory();
			int xP = (int)cur.position.x;
			int yP = (int)cur.position.y;
			int width = cur.texture.getWidth();
			int height = cur.texture.getHeight();


			for(int x = xP; x < xP + width; x++)
			{
				for(int y = yP; y < yP + height; y++)
				{
					data[x][y] = cur;
				}
			}
		}

		for(int xc = 0; xc < this.width; xc++)
		{
			for(int yc = 0; yc < this.height; yc++)
			{
				GameObject c = data[xc][yc];

				if(c != null)
				{
					int rx = (int)c.position.x;
					int ry = (int)c.position.y;
					int sx = (int)c.texture.getWidth();
					int sy = (int)c.texture.getHeight();

					if(rx > xc || rx + sx < xc || ry > yc || ry + sy < yc)
					{
						data[xc][yc] = null;
					}
				}
			}
		}

		this.applyPhysics();
	}

	public void applyPhysics()
	{
		Physics p = new Physics();
		/*
		 * Applies Gravity to all objects that have physics enabled
		 */
		for(int i = 0; i < objects.size(); i++)
		{
			GameObject cur = objects.get(i);
			float tx = 0;
			float ty = 0;

			if(cur.applyGravity())
			{
				p.applyForce(cur, p.gravityID, p.gravityVec);
			}

			for(int j = 0; j < 9; j++)
			{
				if(cur.velocity[j] != null)
				{
					tx += cur.velocity[j].x;
					ty += cur.velocity[j].y;
				}
			}

			this.transformObject(cur, (int)(cur.position.x + (tx*PlayState.delta)), (int)(cur.position.y + (ty*PlayState.delta)));

			if(cur.velocity[1] != null)
			{
				if(cur.velocity[1].y <= 0)
				{
					if(cur.velocity[1].y < -0.1)
					{
						cur.velocity[1].y += 0.01;
					}
					else if(cur.velocity[1].y != 0)
					{
						cur.velocity[1].y = 0;
					}
				}
				else
				{
					cur.velocity[1].y = 0;
				}
			}
		}
	}

	public void deleteObject(GameObject go)
	{
		for(int i = 0; i < objects.size(); i++)
		{
			if(objects.get(i) == go)
			{
				objects.remove(i);
			}
		}

		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.height; y++)
			{
				if(data[x][y] == go)
				{
					data[x][y] = null;
				}
			}
		}
	}

	public void goBackInTime(int amount)
	{
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).timeOffset = amount;
		}
	}
}