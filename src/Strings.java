package net.fiendfan1.src;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Strings extends StateBasedGame
{
	public static String title = "Your Future Needs Help";
	public static int play = 1;
	public static int menu = 0;
	
	public Strings(String name) throws SlickException 
	{
		super(name);
		this.addState(new PlayState());
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		//this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(play);
	}
	
	public static void main(String[] args)
	{
		AppGameContainer appgc;
		
		try
		{
			appgc = new AppGameContainer(new Strings(title));
			appgc.setDisplayMode(1280, 720, false);
			appgc.setMinimumLogicUpdateInterval(5);
			appgc.setMaximumLogicUpdateInterval(80);
			appgc.start();
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
}
