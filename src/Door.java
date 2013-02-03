package net.fiendfan1.src;

public class Door extends Sensor
{	
	public boolean col;
	public boolean stay;
	
	public Door(World world, Button[] in, boolean stayOn) 
	{
		super(world, in);
		col = true;
		this.stay = stayOn;
	}
	
	public void mainUpdate()
	{
		super.mainUpdate();
		
		if(this.on)
		{
			col = false;
			this.texture = PlayState.textures.get(1);
		}
		else if(!this.stay)
		{
			col = true;
			this.texture = PlayState.textures.get(0);
		}
		
		this.on = true;
	}
	
	@Override
	public boolean collide()
	{
		return col;
	}
}
