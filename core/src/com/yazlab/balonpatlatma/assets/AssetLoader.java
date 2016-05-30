package com.yazlab.balonpatlatma.assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.yazlab.balonpatlatma.BalonPatlatma;
import com.yazlab.balonpatlatma.screens.GameScreen;


public class AssetLoader {

    public static int velocity;

    public static BitmapFont [] fontBlack;
    public static BitmapFont [] fontWhite;
    public static GlyphLayout timerLayout, pointLayout, levelLayout, balloonPointLayout;

    public static Texture[] balloonsTex;
    public static Sprite[] balloonsSp;
    public static Sprite[] balloonScoreSp;
    public static int [][] directionStates;

    public static Texture [] pausePlayTex;
    public static Sprite [] pausePlaySp;

  public static Texture [] soundOnOffTex;
    public static Sprite [] soundOnOffSp;



    public static Texture backTex;
    public static Sprite backSp, backSpPlay;

    public static Texture[] bgTex;
    public static Sprite[] bgSp;


    public static Sound gameBg, expBalloon;


    public static ParticleEffect expParEffect;


//Setting the position of the ParticleEffect


//Updating and Drawing the particle effect
//Delta being the time to progress the particle effect by, usually you pass in Gdx.graphics.getDeltaTime();




    public static void load(){



        setFonts();

        setBg();

        setPausePlay();
        
        setBalloons();

        setSounds();

        
        setVelocity(3);
        //setEffects();

    }

    public static void setVelocity(int id) {
        velocity = id;
        setDirectionDistance();
    }

    private static void setPausePlay() {
        pausePlayTex = new Texture[2];
        pausePlaySp = new Sprite[2];
        soundOnOffTex = new Texture[2];
        soundOnOffSp = new Sprite[2];



        pausePlayTex[0] = new Texture(Gdx.files.internal("tools/pause.png"));
        pausePlaySp[0] = new Sprite(pausePlayTex[0]);
        pausePlaySp[0].setSize(pausePlayTex[0].getWidth()*GameScreen.sizeSetWidht, pausePlayTex[0].getHeight()*GameScreen.sizeSetHeight);
        pausePlaySp[0].setPosition(GameScreen.gameWidth - pausePlaySp[0].getWidth()-3*GameScreen.sizeSetWidht, GameScreen.gameHeight - pausePlaySp[0].getHeight()-3*GameScreen.sizeSetHeight);


        pausePlayTex[1] = new Texture(Gdx.files.internal("tools/play.png"));
        pausePlaySp[1] = new Sprite(pausePlayTex[1]);
        pausePlaySp[1].setSize(pausePlayTex[1].getWidth()*GameScreen.sizeSetWidht, pausePlayTex[1].getHeight()*GameScreen.sizeSetHeight);
        pausePlaySp[1].setPosition(GameScreen.gameWidth - pausePlaySp[1].getWidth()-3*GameScreen.sizeSetWidht, GameScreen.gameHeight - pausePlaySp[1].getHeight()-3*GameScreen.sizeSetHeight);


        soundOnOffTex[0] = new Texture(Gdx.files.internal("tools/soundOn.png"));
        soundOnOffSp[0] = new Sprite(soundOnOffTex[0]);
        soundOnOffSp[0].setSize(soundOnOffTex[0].getWidth()*GameScreen.sizeSetWidht, soundOnOffTex[0].getHeight()*GameScreen.sizeSetHeight);
        soundOnOffSp[0].setPosition(GameScreen.gameWidth - soundOnOffSp[0].getWidth()-3*GameScreen.sizeSetWidht, GameScreen.gameHeight - soundOnOffSp[0].getHeight()-3*GameScreen.sizeSetHeight);


        soundOnOffTex[1] = new Texture(Gdx.files.internal("tools/soundOff.png"));
        soundOnOffSp[1] = new Sprite(soundOnOffTex[1]);
        soundOnOffSp[1].setSize(soundOnOffTex[1].getWidth()*GameScreen.sizeSetWidht, soundOnOffTex[1].getHeight()*GameScreen.sizeSetHeight);
        soundOnOffSp[1].setPosition(GameScreen.gameWidth - soundOnOffSp[1].getWidth()-3*GameScreen.sizeSetWidht, GameScreen.gameHeight - soundOnOffSp[1].getHeight()-3*GameScreen.sizeSetHeight);


        backTex = new Texture(Gdx.files.internal("tools/back.png"));
        backSp = new Sprite(backTex);
        backSp.setSize(backTex.getWidth()*GameScreen.sizeSetWidht, backTex.getHeight()*GameScreen.sizeSetHeight);
        backSp.setPosition(backSp.getWidth(),backSp.getHeight()+20);

        backSpPlay = new Sprite(backTex);
        backSpPlay.setSize(backTex.getWidth()*GameScreen.sizeSetWidht, backTex.getHeight()*GameScreen.sizeSetHeight);
        backSpPlay.setPosition(5*GameScreen.sizeSetWidht,GameScreen.gameHeight-backSpPlay.getHeight()-5*GameScreen.sizeSetHeight);


    }

    private static void setFonts() {
        timerLayout = new GlyphLayout();
        pointLayout = new GlyphLayout();
        balloonPointLayout = new GlyphLayout();
        levelLayout = new GlyphLayout();
        fontWhite = new BitmapFont[10];


        fontWhite[0] = new BitmapFont(Gdx.files.internal("fonts/font10.fnt"),Gdx.files.internal("fonts/font10.png"), false);

        fontWhite[0].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[0].setColor(1,1,1,1.0f);
        fontWhite[0].getData().scale(0.5f*GameScreen.sizeSetHeight);


        fontWhite[1] = new BitmapFont(Gdx.files.internal("fonts/arial12.fnt"),Gdx.files.internal("fonts/arial12_0.png"), false);

        fontWhite[1].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[1].setColor(1,1,1,0.3f);
        fontWhite[1].getData().scale(1.0f*GameScreen.sizeSetHeight);


        fontWhite[2] = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"),Gdx.files.internal("fonts/font16.png"), false);
        fontWhite[2].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[2].setColor(1,1,1,0.5f);
        fontWhite[2].getData().scale(2.0f*GameScreen.sizeSetHeight);

        fontWhite[3] = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"),Gdx.files.internal("fonts/font16.png"), false);
        fontWhite[3].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[3].setColor(1,1,1,1f);
        fontWhite[3].getData().scale(2.0f*GameScreen.sizeSetHeight);

        fontWhite[4] = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"),Gdx.files.internal("fonts/font16.png"), false);
        fontWhite[4].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[4].setColor(1,1,1,0.5f);
        fontWhite[4].getData().scale(1.0f*GameScreen.sizeSetHeight);

        fontWhite[5] = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"),Gdx.files.internal("fonts/font16.png"), false);
        fontWhite[5].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[5].setColor(1,1,1,1f);
        fontWhite[5].getData().scale(1.0f*GameScreen.sizeSetHeight);

        fontWhite[6] = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"),Gdx.files.internal("fonts/font16.png"), false);
        fontWhite[6].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[6].setColor(1,1,1,0.5f);
        fontWhite[6].getData().scale(0.5f*GameScreen.sizeSetHeight);

        fontWhite[7] = new BitmapFont(Gdx.files.internal("fonts/font16.fnt"),Gdx.files.internal("fonts/font16.png"), false);
        fontWhite[7].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[7].setColor(1,1,1,1.0f);
        fontWhite[7].getData().scale(0.5f*GameScreen.sizeSetHeight);

        fontWhite[8] = new BitmapFont(Gdx.files.internal("fonts/font10.fnt"),Gdx.files.internal("fonts/font10.png"), false);
        fontWhite[8].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[8].setColor(0,1,0,0.5f);
        fontWhite[8].getData().scale(0.5f*GameScreen.sizeSetHeight);

        fontWhite[9] = new BitmapFont(Gdx.files.internal("fonts/font10.fnt"),Gdx.files.internal("fonts/font10.png"), false);
        fontWhite[9].getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontWhite[9].setColor(0,1,0,2f);
        fontWhite[9].getData().scale(0.5f*GameScreen.sizeSetHeight);
    }

    private static void setBg() {
        bgTex = new Texture[6];
        bgSp = new Sprite[6];

        for(int i=0;i<3;i++) {
            bgTex[i] = new Texture(Gdx.files.internal("bg/"+(i+1)+".jpg"));
            bgSp[i] = new Sprite(bgTex[i]);
            bgSp[i].setSize(GameScreen.gameWidth,GameScreen.gameHeight);
        }

        for (int i=3;i<6;i++) {
            bgTex[i] = new Texture(Gdx.files.internal("bg/"+(i+1)+".png"));
            bgSp[i] = new Sprite(bgTex[i]);
            bgSp[i].setSize(GameScreen.gameWidth, GameScreen.gameHeight);
        }


    }//BG


    public static void setBalloons(){

        balloonsTex = new Texture[4];
        balloonsSp = new Sprite[4];
        balloonScoreSp = new Sprite[4];

        for(int i=0;i<4;i++) {
            balloonsTex[i] = new Texture(Gdx.files.internal("balloons/"+i+".png"));
            balloonsSp[i] = new Sprite(balloonsTex[i]);
            balloonsSp[i].setSize(balloonsTex[i].getWidth()*GameScreen.sizeSetWidht,balloonsTex[i].getHeight()*GameScreen.sizeSetHeight);

            balloonScoreSp[i] = new Sprite(balloonsTex[i]);
            balloonScoreSp[i].setSize(15*GameScreen.sizeSetWidht, 25*GameScreen.sizeSetHeight);
            balloonScoreSp[i].setPosition(10+4*(i)*15*GameScreen.sizeSetWidht, 10);
        }






    }//BALLOOONS


    public static void setSounds(){

        gameBg = Gdx.audio.newSound(Gdx.files.internal("sounds/gamebg.mp3"));
        expBalloon = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));

    }//Sounds



    public static void setEffects(){

        expParEffect = new ParticleEffect();
        expParEffect.load(Gdx.files.internal("effects/explosion.p"), Gdx.files.internal(""));


    }


    public static void setDirectionDistance(){

        directionStates = new int[9][2];


        directionStates[0][0] = -velocity*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[0][1] = velocity*(int)Math.ceil(GameScreen.sizeSetHeight);

        directionStates[1][0] = 0*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[1][1] = velocity*(int)Math.ceil(GameScreen.sizeSetHeight);;

        directionStates[2][0] = velocity*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[2][1] = velocity*(int)Math.ceil(GameScreen.sizeSetHeight);;

        directionStates[3][0] = -velocity*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[3][1] = 0*(int)Math.ceil(GameScreen.sizeSetHeight);;

        directionStates[8][0] = 0*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[8][1] = 0*(int)Math.ceil(GameScreen.sizeSetHeight);;

        directionStates[4][0] = velocity*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[4][1] = 0*(int)Math.ceil(GameScreen.sizeSetHeight);;

        directionStates[5][0] = -velocity*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[5][1] = -velocity*(int)Math.ceil(GameScreen.sizeSetHeight);;

        directionStates[6][0] = 0*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[6][1] = -5*(int)Math.ceil(GameScreen.sizeSetHeight);;

        directionStates[7][0] = velocity*(int)Math.ceil(GameScreen.sizeSetWidht);
        directionStates[7][1] = -velocity*(int)Math.ceil(GameScreen.sizeSetHeight);;


    }






}
