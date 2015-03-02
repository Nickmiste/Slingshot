package nickmiste.slingshot.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import nickmiste.slingshot.Main;

public class HtmlLauncher extends GwtApplication
{

    @Override
    public GwtApplicationConfiguration getConfig () 
    {
		return new GwtApplicationConfiguration(640, 1136);
    }

    @Override
    public ApplicationListener getApplicationListener () 
    {
        return new Main();
    }
}