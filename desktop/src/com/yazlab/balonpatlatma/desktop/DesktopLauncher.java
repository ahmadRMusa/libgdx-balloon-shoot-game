package com.yazlab.balonpatlatma.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.yazlab.balonpatlatma.BalonPatlatma;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BalonPatlatma.WIDTH;
		config.height = BalonPatlatma.HEIGHT;
		config.title= "Balon Patlatma";
		new LwjglApplication(new BalonPatlatma(), config);
	}
}
