package nickmiste.slingshot.leveldesign.obstacles;

import nickmiste.slingshot.Main;
import nickmiste.slingshot.leveldesign.Obstacle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class Wall extends Obstacle
{
	private int width;
	private int height;
	
	public Wall(int x, int y, int w, int h)
	{
		super(x, y);
		this.width = w;
		this.height = h;
	}
	
	@Override
	public void effect()
	{
		if (Main.player.pos.x + Main.player.radius > this.x && Main.player.pos.x - Main.player.radius < this.x + width 
				&& Main.player.pos.y +Main.player.radius > this.y && Main.player.pos.y - Main.player.radius < this.y + height) 				//Any Collision
		{
			if (Main.player.oldPos.x < this.x || Main.player.oldPos.x > this.x + width)														//Left or Right Collision
				if (Main.player.velocityx > 2 || Main.player.velocityx < -2)
					Main.player.setVelocity(-Main.player.velocityx, Main.player.velocityy);
				else
					Main.player.setVelocity(Main.player.velocityx > 0 ? -2 : 2, Main.player.velocityy);
			else if (Main.player.oldPos.y < this.y || Main.player.oldPos.y > this.y + height)												//Top or Bottom Collision
				if (Main.player.velocityy > 2 || Main.player.velocityy < -2)
					Main.player.setVelocity(Main.player.velocityx, -Main.player.velocityy);
				else
					Main.player.setVelocity(Main.player.velocityx, Main.player.velocityy > 0 ? -2 : 2);
		}
		
		Main.shapes.setProjectionMatrix(Main.camera.combined);
		Main.shapes.begin(ShapeType.Filled);
		Main.shapes.setColor(Color.LIGHT_GRAY);
		Main.shapes.rect(this.x, this.y, this.width, this.height);
		Main.shapes.end();
	}
}
