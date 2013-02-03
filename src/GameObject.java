package net.fiendfan1.src;
import java.util.Arrays;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class GameObject 
{
	public World world;
	public int id;
	public Image texture;
	public Vector2f position;
	public Vector2f[] velocity;
	public Vector2f[] history;
	public GameObject[] gHis;
	public float timeOffset;

	@SuppressWarnings("unused")
	public GameObject(World world, int id, int texture)
	{
		int timeOffset = 0;
		gHis = new GameObject[10000];
		//Arrays.fill(gHis, new GameObject(world, 0, 0));
		history = new Vector2f[10000];
		Arrays.fill(history, new Vector2f(0, 0));
		this.world = world;
		velocity = new Vector2f[10];
		position = new Vector2f(0, 0);
		this.id = id;
		this.texture = PlayState.textures.get(texture);
	}
	
	public GameObject()
	{
		new GameObject(new World(), 0, 0);
	}

	public void mainUpdate(){}

	public void updateHistory()
	{
		if(timeOffset <= 0)
		{
			timeOffset = 0;

			for(int j = 0; j < history.length - 1; j++)
			{
				history[j] = history[j + 1];
			}

			/*for(int j = 0; j < gHis.length - 1; j++)
			{
				gHis[j] = gHis[j + 1];
			}*/

			history[history.length-1] = new Vector2f(this.position.x, this.position.y);
			//gHis[gHis.length - 1] = this;

		}
		else
		{
			/*if(gHis[(int)(gHis.length - timeOffset)] != null)
			{
				this.position = gHis[(int)(history.length - timeOffset)].position;
				this.velocity = gHis[(int)(history.length - timeOffset)].velocity;
			}*/

			if(history[(int)(history.length - timeOffset)] != null)
			{
				this.position = history[(int)(history.length - timeOffset)];
			}


			timeOffset -= PlayState.delta/30;
		}

		this.mainUpdate();
	}

	public void setVelocity(int id, Vector2f vel)
	{
		velocity[id] = vel;
	}

	public boolean collide()
	{
		return true;
	}
	
	public boolean applyGravity()
	{
		return false;
	}
}