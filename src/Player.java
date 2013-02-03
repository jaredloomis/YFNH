package net.fiendfan1.src;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player extends GameObject
{
	public int selected;
	
	public int up = Input.KEY_W;
	public int down = Input.KEY_S;
	public int left = Input.KEY_A;
	public int right = Input.KEY_D;
	public int jump = Input.KEY_SPACE;
	public int warp = Input.KEY_Q;
	public int warpWorld = Input.KEY_E;
	public int swap = Input.KEY_1;
	
	public float speed = 0.25f;

	public Player(World world) 
	{
		super(world, 1, 2);
		this.selected = 0;
	}

	@SuppressWarnings("unused")
	public void handleInput(GameContainer gc)
	{
		Physics p = new Physics();
		Input i = gc.getInput();
		int x = (int)this.position.x;
		int y = (int)this.position.y;
		boolean lClick = Mouse.isButtonDown(0);
		int mX = Mouse.getX();
		int mY = Mouse.getY();
		
		int xv = 0;
		int yv = 0;

		if(i.isKeyDown(left))
		{
			xv -= (int)((speed*PlayState.delta));
		}

		if(i.isKeyDown(right))
		{
			xv += (int)((speed*PlayState.delta));
		}

		if(i.isKeyPressed(jump))
		{
			p.applyForce(this, p.jumpID, p.jumpVec);
		}

		if(i.isKeyPressed(warp))
		{	
			PlayerClone pc = new PlayerClone(this.world, this, 200, 3000);
			this.world.addToWorld(pc);
		}
		
		if(i.isKeyPressed(warpWorld))
		{
			this.world.goBackInTime(100);
		}
		
		if(i.isKeyPressed(swap))
		{
			if(this.selected < PlayState.objectsIndex.size())
			{
				this.selected++;
			}
			else
			{
				this.selected = 0;
			}
		}
		
		if(lClick)
		{
			/*@SuppressWarnings("rawtypes")
			Class g = PlayState.objectsIndex.get(selected);
			try 
			{
				GameObject go = (GameObject)g.newInstance();
				world.addToWorld(go);
			} 
			catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}*/
		}

		this.world.transformObject(this, x + xv, y + yv);
	}

	@Override
	public boolean applyGravity()
	{
		return true;
	}
}
