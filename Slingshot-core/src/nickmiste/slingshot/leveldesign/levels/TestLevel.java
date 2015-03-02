package nickmiste.slingshot.leveldesign.levels;

import nickmiste.slingshot.leveldesign.Level;
import nickmiste.slingshot.leveldesign.Obstacle;
import nickmiste.slingshot.leveldesign.obstacles.Meteor;
import nickmiste.slingshot.leveldesign.obstacles.Nebula;
import nickmiste.slingshot.leveldesign.obstacles.Void;
import nickmiste.slingshot.leveldesign.obstacles.Wall;

import com.badlogic.gdx.math.Vector3;

public class TestLevel extends Level
{	
	public TestLevel()
	{
		
	}

	@Override
	protected Vector3 getFinish() 
	{
		return new Vector3(320, 900, 0);
	}

	@Override
	public Obstacle[] obstacles()
	{
		return new Obstacle[] 
			{
				new Wall(0, 0, 5, 1136),
				new Wall(0, 0, 640, 5),
				new Wall(0, 1136, 640, 0),
				new Wall(640, 0, 0, 1136),
				new Void(500, 100, 25),
				new Nebula(640, 1136, 200),
				new Meteor(100, 100)
			};
	}
}