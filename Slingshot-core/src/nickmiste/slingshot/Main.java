package nickmiste.slingshot;


import nickmiste.slingshot.leveldesign.Level;
import nickmiste.slingshot.leveldesign.levels.TestLevel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter 
{
	public static OrthographicCamera camera;
	public static ShapeRenderer shapes;
	public static Player player;
	
	public static Level level;
	
	@Override
	public void create() 
	{
		shapes = new ShapeRenderer();
		player = new Player();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 640, 1136);

		level = new TestLevel();
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		level.playLevel();
		
		player.doMovement();
		
		//Temporary code for level restarts (will later be replaced by menu)
		if (Gdx.input.isKeyPressed(Input.Keys.R))
		{
			player = new Player();
			level = new TestLevel();
		}
	}
}
