package nickmiste.slingshot.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nickmiste.slingshot.Main;

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Slingshot";
		config.width = 640;
		config.height = 1136;
		new LwjglApplication(new Main(), config);
	}
}
