package net.fiendfan1.src;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends BasicGameState
{
	@SuppressWarnings("rawtypes")
	public static List<Class> objectsIndex;
	public static List<Image> textures;
	public Textures tClass;
	private int counter;
	Player player;
	public World world;
	public int updateRate;
	public static int delta;

	@SuppressWarnings("rawtypes")
	public PlayState() throws SlickException
	{
		objectsIndex = new ArrayList<Class>();
		tClass = new Textures();
		counter = 0;
		world = new World();
		textures = new ArrayList<Image>();
		updateRate = 110;
	}

	int count = 0;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		if(count == 0)
		{
			objectsIndex.add(GameObject.class);
			objectsIndex.add(Button.class);
			objectsIndex.add(Door.class);
			
			Image testImg = new Image("res/Test.png");
			Image testTwo = new Image("res/Test2.png");
			Image playerImg = new Image("res/Ian.jpg");
			tClass.addImage(testImg, true);
			tClass.addImage(testTwo, true);
			tClass.addImage(playerImg, true);
			
			textures = tClass.textures;
			
			this.setInitialObjects();
			count++;
		}
	}

	public void setInitialObjects()
	{
		player = new Player(world);
		world.addToWorld(player);
		
		/*for(int i =0; i < 100; i++)
		{
			Random rand = new Random(System.nanoTime());
			GameObject test2 = new GameObject(world, 2, 0);
			world.transformObject(test2, (int)(700*Math.sin(rand.nextInt(1180))) + 50, (int)(100*Math.sin(rand.nextInt(620))+50));
			world.addToWorld(test2);
		}*/
		
		Button butn = new Button(world);
		world.transformObject(butn, 1280/2 - 72, 720/2);
		world.addToWorld(butn);
		
		Button butn2 = new Button(world);
		world.transformObject(butn2, 1280/2 + 72, 720/2);
		world.addToWorld(butn2);
		
		Button[] butAr = {butn, butn2};
		
		Sensor sens = new Sensor(this.world, butAr);
		world.transformObject(sens, 64, 64);
		world.addToWorld(sens);
		
		Door door = new Door(this.world, butAr, true);
		world.transformObject(door, 320, 64);
		world.addToWorld(door);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		world.renderObjects(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		counter += delta;

		if(counter >= updateRate)
		{
			PlayState.delta = delta;
			player.handleInput(gc);
			world.updateData();
		}
	}

	@Override
	public int getID() 
	{
		return 1;
	}

}
