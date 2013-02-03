package net.fiendfan1.src;
import org.newdawn.slick.geom.Vector2f;

public class Physics 
{
	public int gravityID = 0;
	public Vector2f gravityVec = new Vector2f(0, 0.25f);
	public int jumpID = 1;
	public Vector2f jumpVec = new Vector2f(0, -0.5f);
	
	public void applyForce(GameObject go, int id, Vector2f force)
	{
		Vector2f curVel= go.velocity[id];
		
		if(curVel == null)
		{
			go.velocity[id] = force;
		}
		else if(curVel.y == 0 && curVel.x == 0)
		{
			go.setVelocity(id, force);
		}
		else
		{
			float finalX = 0;
			float finalY = 0;
			float maxX = Math.max(Math.abs(force.x), Math.abs(curVel.x));
			float maxY = Math.max(Math.abs(force.y), Math.abs(curVel.y));
			
			if(maxX == Math.abs(force.x))
			{
				finalX = force.x;
			}
			else
			{
				finalX = curVel.x;
			}
			
			if(maxY == Math.abs(force.y))
			{
				finalY = force.y;
			}
			else
			{
				finalY = curVel.y;
			}
			
			go.velocity[id] = new Vector2f(finalX, finalY);
			go.velocity[id].x = finalX;
			go.velocity[id].y = finalY;
		}
	}
}
