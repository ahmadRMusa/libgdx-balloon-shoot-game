package com.yazlab.balonpatlatma.objects;

import com.badlogic.gdx.Gdx;
import com.yazlab.balonpatlatma.BalonPatlatma;

/**
 * Created by OkanDemir on 5.05.2016.
 */
public interface GameObjects {

    public float sizeSetWidht = (float) Gdx.graphics.getWidth()/ BalonPatlatma.WIDTH;
    public float sizeSetHeight = (float)Gdx.graphics.getHeight()/BalonPatlatma.HEIGHT;


    public static float gameWidth = com.yazlab.balonpatlatma.BalonPatlatma.WIDTH*sizeSetWidht;
    public static float gameHeight = com.yazlab.balonpatlatma.BalonPatlatma.HEIGHT*sizeSetHeight;
    //public float objectWidthSize=0.625f;
    //public float objectHeightSize=0.5f;
    //  public float objectWidthSize = TankWarGame.WIDTH/ AssetLoader.backGround.getWidth();
    //  public float objectHeightSize = TankWarGame.HEIGHT/AssetLoader.backGround.getHeight();

    public int
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

    public int
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

    public void Draw(float delta);
    public void Update(float delta);
    public void Dispose();

}
