package net.fiendfan1.src;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Image;

public class Textures 
{
	public int standardHeight = 64;
	public int standardWidth = 64;
	public List<Image> textures;

	public Textures()
	{
		textures = new ArrayList<Image>();
	}

	public void addImage(Image i, boolean standard)
	{
		if(standard)
		{
			if(i.getHeight() == this.standardHeight && i.getWidth() == this.standardWidth)
			{
				textures.add(i);
			}
			else
			{

				textures.add(i.getScaledCopy(this.standardHeight, this.standardWidth));
			}
		}
		else
		{
			textures.add(i);
		}
	}
}
