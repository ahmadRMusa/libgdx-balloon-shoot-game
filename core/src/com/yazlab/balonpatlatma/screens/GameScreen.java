package com.yazlab.balonpatlatma.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.yazlab.balonpatlatma.BalonPatlatma;


public abstract class GameScreen implements Screen, InputProcessor{

    public static float sizeSetWidht = (float) Gdx.graphics.getWidth()/ com.yazlab.balonpatlatma.BalonPatlatma.WIDTH;
    public static float sizeSetHeight = (float)Gdx.graphics.getHeight()/ com.yazlab.balonpatlatma.BalonPatlatma.HEIGHT;

    public static float gameWidth = com.yazlab.balonpatlatma.BalonPatlatma.WIDTH*sizeSetWidht;
    public static float gameHeight = com.yazlab.balonpatlatma.BalonPatlatma.HEIGHT*sizeSetHeight;

    //public float objectWidthSize=0.625f;
    //public float objectHeightSize=0.5f;
    //public float objectWidthSize = PacketWar.WIDTH/AssetLoader.backGround.getWidth();
    //public float objectHeightSize = PacketWar.HEIGHT/AssetLoader.backGround.getHeight();
    public static int
            width_1=Gdx.graphics.getWidth()/480,
            width_2=Gdx.graphics.getWidth()/240,
            width_4=Gdx.graphics.getWidth()/120,
            width_8=Gdx.graphics.getWidth()/60,
            width_16=Gdx.graphics.getWidth()/30,
            width_32=Gdx.graphics.getWidth()/15,
            width_40=Gdx.graphics.getWidth()/12,
            width_80=Gdx.graphics.getWidth()/6,
            width_120=Gdx.graphics.getWidth()/4,
            width_160=Gdx.graphics.getWidth()/3,
            width_240=Gdx.graphics.getWidth()/2,
            width_480=Gdx.graphics.getHeight()/1;

    public static int
            height_1=Gdx.graphics.getHeight()/800,
            height_2=Gdx.graphics.getHeight()/400,
            height_4=Gdx.graphics.getHeight()/200,
            height_8=Gdx.graphics.getHeight()/100,
            height_16=Gdx.graphics.getHeight()/50,
            height_32=Gdx.graphics.getHeight()/25,
            height_40=Gdx.graphics.getHeight()/20,
            height_80=Gdx.graphics.getHeight()/10,
            height_160=Gdx.graphics.getHeight()/5,
            height_200=Gdx.graphics.getHeight()/4,
            height_400=Gdx.graphics.getHeight()/2,
            height_800=Gdx.graphics.getHeight()/1;

    protected BalonPatlatma BalonPatlatma;
    private String visibleScreen;

    public GameScreen(BalonPatlatma BalonPatlatma){
        this.BalonPatlatma = BalonPatlatma;
    }


    public abstract void Update (float delta);

    public abstract void Draw (float delta);

    public abstract boolean isScreenState();

    @Override
    public void render (float delta) {
        Update(delta);
        Draw(delta);
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }

    public String getVisibleScreen() {
        return visibleScreen;
    }

    public void setVisibleScreen(String visibleScreen) {
        this.visibleScreen = visibleScreen;
    }

}
