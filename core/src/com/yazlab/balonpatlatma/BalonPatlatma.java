package com.yazlab.balonpatlatma;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.yazlab.balonpatlatma.assets.AssetLoader;
import com.yazlab.balonpatlatma.screens.GameScreen;
import com.yazlab.balonpatlatma.screens.About;
import com.yazlab.balonpatlatma.screens.Menu;
import com.yazlab.balonpatlatma.screens.Play;

public class BalonPatlatma extends Game {

	public static int WIDTH = 480;
	public static int HEIGHT = 800;

	//SpriteBatch batch;
	//Texture img;
	
	@Override
	public void create () {

		AssetLoader.load();
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		setScreen(new Menu(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		GameScreen currentScreen = getScreen();
		currentScreen.render(Gdx.graphics.getDeltaTime());


		if(currentScreen.isScreenState()==false){

			if(currentScreen.getVisibleScreen()=="play"){
				currentScreen.dispose();
				setScreen(new Play(this));
			}

			if(currentScreen.getVisibleScreen()=="menu"){
				currentScreen.dispose();
				setScreen(new Menu(this));
			}

			if(currentScreen.getVisibleScreen()=="about"){
				currentScreen.dispose();
				setScreen(new About(this));
			}



		}//false

		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}


	public GameScreen getScreen() {
		return (GameScreen)super.getScreen();
	}
}
