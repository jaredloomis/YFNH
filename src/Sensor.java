package net.fiendfan1.src;

public class Sensor extends GameObject
{
	public Button[] input;
	public boolean on;
	public int imgA, imgB;
	
	public Sensor(World world, Button[] in) 
	{
		super(world, 3, 0);
		this.input = in;
		on = true;
		imgA = 1;
		imgB = 0;
	}
	
	/**
	 * When Overriding this make sure to
	 * add "this.on = true;" to the end of 
	 * your function
	 * */
	@Override
	public void mainUpdate()
	{
		for(int i = 0; i < input.length; i++)
		{
			if(!input[i].on)
			{
				this.on = false;
				break;
			}
		}
		
		/*if(this.on)
		{
			this.texture = PlayState.textures.get(imgA);
		}
		else
		{
			this.texture = PlayState.textures.get(imgB);
		}*/
	}
}
