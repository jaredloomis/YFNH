package net.fiendfan1.src;
import org.newdawn.slick.GameContainer;

public class PlayerClone extends Player
{
	public int lifetime;
	
	public PlayerClone(World world, Player orig, int offset, int lifetime)
	{
		super(world);
		this.lifetime = lifetime;
		this.timeOffset = offset;
		this.history = orig.history;
	}
	
	@Override
	public void mainUpdate()
	{
		lifetime -= PlayState.delta;
		
		if(lifetime <= 0)
		{
			this.world.transformObject(this, 0, 0);
			this.world.deleteObject(this);
		}
	}
	
	@Override
	public void handleInput(GameContainer gc){}
}
