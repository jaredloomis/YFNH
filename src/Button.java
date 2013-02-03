package net.fiendfan1.src;

public class Button extends GameObject
{
	public boolean on;
	public int imgA, imgB;
	
	public Button(World world)
	{
		super(world, 3, 1);
		this.on = false;
		
		imgA = 0;
		imgB = 1;
	}
	
	@Override
	public void mainUpdate()
	{
		if(world.data[(int)this.position.x][(int)(this.position.y - 4)] instanceof Player)
		{
			this.texture = PlayState.textures.get(imgA);
			on = true;
		}
		else if(world.data[(int)this.position.x + this.texture.getWidth()][(int)(this.position.y - 4)] instanceof Player)
		{
			this.texture = PlayState.textures.get(imgA);
			on = true;
		}
		else
		{
			this.texture = PlayState.textures.get(imgB);
			on = false;
		}
	}
}
