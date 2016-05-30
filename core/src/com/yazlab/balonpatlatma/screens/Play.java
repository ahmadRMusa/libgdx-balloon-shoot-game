package com.yazlab.balonpatlatma.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.yazlab.balonpatlatma.BalonPatlatma;
import com.yazlab.balonpatlatma.assets.AssetLoader;
import com.yazlab.balonpatlatma.objects.Balloon;
import com.yazlab.balonpatlatma.objects.Player;

import java.util.ArrayList;
import java.util.Random;

public class Play extends GameScreen{

    SpriteBatch batch;
    //Texture img;

    private Sprite map;
    private Boolean pause, gameOver, gameEnd, levelChange;
    private Boolean gameMusic;

    private float balloonStartTime = 0;
    private ArrayList<Balloon> balloons;

    private ArrayList<Integer> yellowTimes;
    private int yellowMaxCount=10;
    private int randomTime;



    private Random rnd;
    private ArrayList<Integer> blackTimes;
    private float timer =0f, gameTime=15.0f, gameSeconds=0f;


    private Vector3 input;


    private Player player;

    public Play(BalonPatlatma BalonPatlatma) {
        super(BalonPatlatma);
        batch = new SpriteBatch();


        map = new Sprite();

        pause=false;
        gameOver=false;
        gameEnd=false;
        levelChange=false;

        Gdx.input.setInputProcessor(this);

        rnd = new Random();

        balloons = new ArrayList<Balloon>();

        yellowTimes= new ArrayList<Integer>();


        createYellow();


        blackTimes = new ArrayList<Integer>();

        for(int i=0;i<30;i=i+2){
            if(rnd.nextBoolean()){
                blackTimes.add(i);
            }
        }


        input = new Vector3();

        player = new Player(batch);




        map = AssetLoader.bgSp[player.getLevel()-1];

        //gameMusic=false;

        if(Menu.soundState){

        }else {

            AssetLoader.gameBg.play(0.5f);
            AssetLoader.gameBg.loop(0.5f);

        }
    }//consructer


    public void createYellow(){
        yellowMaxCount = 10;
        while(yellowMaxCount!=0){
            randomTime = rnd.nextInt(29)+1;

            if(!yellowTimes.contains(randomTime)){
                yellowTimes.add(randomTime);
            }

            yellowMaxCount--;
        }
    }


    @Override
    public void Update(float delta) {
        //AssetLoader.gameBg.resume();



        if(!pause && !gameOver && !gameEnd && !levelChange){


            balloonStartTime += Gdx.graphics.getDeltaTime();
            timer += Gdx.graphics.getDeltaTime();
            gameSeconds = gameTime - (int) timer;


            //System.out.println("bln zamn : "+(int)(balloonStartTime % 2));


            for (int i = 0; i < yellowTimes.size(); i++) {
                if (yellowTimes.get(i) == (int) timer) {
                    balloons.add(new Balloon(2, timer, batch));
                    yellowTimes.remove(i);
                    //System.out.println("Sarı Balon Olusturuldu");
                    break;
                }
            }

            if (balloonStartTime >= 0.5) {

                balloons.add(new Balloon(rnd.nextInt(2), timer, batch));

                balloonStartTime = 0;
            }


            //updating position of balloon
            if (!balloons.isEmpty()) {

                for (int i = 0; i < balloons.size(); i++) {


                    //((int)timer%(rnd.nextInt(3)+1))

                    //BLACK TIMER

                    if (blackTimes.contains((int) timer)) {
                        if (balloons.get(i).getColor() == "green") {
                            balloons.get(i).setSprite(AssetLoader.balloonsSp[3]);
                            balloons.get(i).setPoint(-10);
                            balloons.get(i).setType(3);
                        }
                        //blackState = true;
                    } else {
                        if (balloons.get(i).getColor() == "green") {
                            balloons.get(i).setSprite(AssetLoader.balloonsSp[0]);
                            balloons.get(i).setPoint(5);
                            balloons.get(i).setType(0);
                        }
                        //blackTimer=0f;
                        //blackState=false;
                    }


                    //BLACK TIMER


                    balloons.get(i).Update(delta);


                    //yellow timer
                    if (i < balloons.size()) {
                        if (balloons.get(i).getColor() == "yellow") {
                            if (timer >= balloons.get(i).getLifeTime() + balloons.get(i).getInstantTime()) {
                                balloons.get(i).setState(false);
                                //balloons.remove(i);
                                //System.out.println("Sarı Balon Silindi");
                            }
                        }
                    }
                    //yellow timer


                }//end for
            }
            //balon pozisyon güncelleme

            if (gameSeconds <= 0) {

                if(player.getHittedBalloons(0)>=1 && player.getHittedBalloons(1)>=1 && player.getHittedBalloons(2)>=1 && player.getHittedBalloons(3)>=1 && player.getPoint()>=100) {

                    if (player.getLevel() != 3) {

                        player.setLevelPoints(player.getLevel()-1);
                        player.setLevel(player.getLevel() + 1);
                        player.setSumPoint(player.getPoint());
                        this.map = AssetLoader.bgSp[player.getLevel() - 1];
                        player.clearHittedBalloons();
                        player.getBalloons().clear();
                        balloons.clear();
                        createYellow();
                        AssetLoader.setVelocity(player.getLevel()*2+3);
                        timer = 0f;

                        levelChange=true;


                    }else{

                        player.setLevelPoints(player.getLevel()-1);
                        player.setSumPoint(player.getPoint());
                        player.clearHittedBalloons();
                        player.getBalloons().clear();
                        balloons.clear();
                        gameEnd=true;
                    }

                }else{
                    player.setLevelPoints(player.getLevel()-1);
                    player.setSumPoint(player.getPoint());
                    player.clearHittedBalloons();
                    player.getBalloons().clear();
                    balloons.clear();
                    gameOver = true;


                }

            }



            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[2], String.valueOf(gameSeconds));
            player.Update(delta);


            if (!balloons.isEmpty()) {

                for (int i = 0; i < balloons.size(); i++) {

                    if (!balloons.get(i).isState()) {
                        balloons.remove(i);
                    }

                }


            }


        }//PAUSE

    }//UPDATE

    @Override
    public void Draw(float delta) {



        batch.begin();





        map.draw(batch);



        if(!gameOver && !gameEnd && !levelChange) {
            AssetLoader.fontWhite[2].draw(batch, AssetLoader.timerLayout, (Gdx.graphics.getWidth() - AssetLoader.timerLayout.width) / 2, Gdx.graphics.getHeight() - AssetLoader.timerLayout.height);


        player.Draw(delta);


        if (!balloons.isEmpty()) {
            for (int i = 0; i < balloons.size(); i++) {

                    balloons.get(i).Draw(delta);
            }
        }





            if(!pause) {
                AssetLoader.pausePlaySp[0].draw(batch);

            }else{

                 AssetLoader.pausePlaySp[1].draw(batch);
                 AssetLoader.backSpPlay.draw(batch);
            }

        }// if gameover && gameEnd && levelChange

        if(gameOver){

            AssetLoader.bgSp[3].draw(batch);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[5], "kaybettiniz");
            AssetLoader.fontWhite[5].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "1.seviye : "+String.valueOf(player.getLevelPoints(0)));
            AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-4*AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "2.seviye : "+String.valueOf(player.getLevelPoints(1)));
            AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-5*AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "3.seviye : "+String.valueOf(player.getLevelPoints(2)));
            AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-6*AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[9], "yeniden oynamak icin tikla");
            AssetLoader.fontWhite[9].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-(gameHeight/5)*3);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[3], "PUAN : "+String.valueOf(player.getSumPoint()));
            AssetLoader.fontWhite[3].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-(gameHeight/5)*4);
/*
            if(!player.isFileState()) {
                player.writeToFile(String.valueOf(player.getSumPoint()));
                player.setFileState(true);
            }*/

        }else if (gameEnd){

            AssetLoader.bgSp[3].draw(batch);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[5], "kazandiniz");
            AssetLoader.fontWhite[5].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "1.seviye : "+String.valueOf(player.getLevelPoints(0)));
            AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-5*AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "2.seviye : "+String.valueOf(player.getLevelPoints(1)));
            AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-6*AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "3.seviye : "+String.valueOf(player.getLevelPoints(2)));
            AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-7*AssetLoader.timerLayout.height*sizeSetHeight);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[9], "yeniden oynamak icin tikla");
            AssetLoader.fontWhite[9].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-(gameHeight/5)*3);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[3], "puan : "+String.valueOf(player.getSumPoint()));
            AssetLoader.fontWhite[3].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-(gameHeight/5)*4);


            /*if(!player.isFileState()) {
                player.writeToFile(String.valueOf(player.getSumPoint()));
                player.setFileState(true);
            }*/

        }else if(levelChange){

            AssetLoader.bgSp[3].draw(batch);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], String.valueOf(player.getLevel())+". seviyeye gectin");
            AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-AssetLoader.timerLayout.height*sizeSetHeight);


           if(player.getLevel()==2) {
               AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "1.seviye : " + String.valueOf(player.getLevelPoints(0)));
               AssetLoader.fontWhite[7].draw(batch, AssetLoader.timerLayout, (gameWidth - AssetLoader.timerLayout.width) / 2, gameHeight - 4 * AssetLoader.timerLayout.height*sizeSetHeight);
           }else if(player.getLevel()==3){
               AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "1.seviye : " + String.valueOf(player.getLevelPoints(0)));
               AssetLoader.fontWhite[7].draw(batch, AssetLoader.timerLayout, (gameWidth - AssetLoader.timerLayout.width) / 2, gameHeight - 4 * AssetLoader.timerLayout.height*sizeSetHeight);

               AssetLoader.timerLayout.setText(AssetLoader.fontWhite[7], "2.seviye : "+String.valueOf(player.getLevelPoints(1)));
               AssetLoader.fontWhite[7].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-5*AssetLoader.timerLayout.height*sizeSetHeight);
           }


           /* AssetLoader.timerLayout.setText(AssetLoader.fontWhite[2], "3.L : "+String.valueOf(player.getLevelPoints(2)));
            AssetLoader.fontWhite[2].draw(batch,AssetLoader.timerLayout,(Gdx.graphics.getWidth() - AssetLoader.timerLayout.width)/2,Gdx.graphics.getHeight()-5*AssetLoader.timerLayout.height);*/

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[9], "devam etmek için tikla");
            AssetLoader.fontWhite[9].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-(gameHeight/5)*3);

            AssetLoader.timerLayout.setText(AssetLoader.fontWhite[3], "PUAN : "+String.valueOf(player.getSumPoint()));
            AssetLoader.fontWhite[3].draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2,gameHeight-(gameHeight/5)*4);
        }


        batch.end();
    }



    @Override
    public boolean isScreenState() {
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        input.x = screenX;
        input.y = Gdx.graphics.getHeight() - screenY;

        if(!pause && !gameOver && !gameEnd && !levelChange) {

        if (!balloons.isEmpty()) {
            for (int i = 0; i < balloons.size(); i++) {


                if (balloons.get(i).isState()) {
                    if (input.x > balloons.get(i).getPosX() && input.x < (balloons.get(i).getPosX() + balloons.get(i).getSprite().getWidth())) {

                        if (input.y > balloons.get(i).getPosY() && input.y < (balloons.get(i).getPosY() + balloons.get(i).getSprite().getHeight())) {

                            if(Menu.soundState){

                            }else {
                                AssetLoader.expBalloon.play();
                            }

                            balloons.get(i).setState(false);
                            player.addHittedBalloons(balloons.get(i).getType());
                            player.addBalloons(balloons.get(i));
                            //balloons.remove(i);
                            //AssetLoader.gameBg.stop();
                        }

                    }

                }

            }
        }




            if(!gameOver && !gameEnd && !levelChange) {
                if (input.x > AssetLoader.pausePlaySp[0].getX() && input.x < AssetLoader.pausePlaySp[0].getX() + AssetLoader.pausePlaySp[0].getWidth()) {
                    if (input.y > AssetLoader.pausePlaySp[0].getY() && input.y < AssetLoader.pausePlaySp[0].getY() + AssetLoader.pausePlaySp[0].getHeight()) {
                        pause = true;
                    }
                }

                if (input.x > AssetLoader.backSpPlay.getX() && input.x < AssetLoader.backSpPlay.getX() + AssetLoader.backSpPlay.getWidth()) {
                    if (input.y > AssetLoader.backSpPlay.getY() && input.y < AssetLoader.backSpPlay.getY() + AssetLoader.backSpPlay.getHeight()) {

                        AssetLoader.gameBg.stop();
                        AssetLoader.setVelocity(3);
                        setVisibleScreen("menu");
                    }
                }

            }

        }else{




            if(!pause && !levelChange) {

                player.setFileState(false);
                AssetLoader.gameBg.stop();
                AssetLoader.setVelocity(3);
                setVisibleScreen("menu");

            }



            if(!gameOver && !gameEnd && !levelChange) {
                if (input.x > AssetLoader.pausePlaySp[1].getX() && input.x < AssetLoader.pausePlaySp[1].getX() + AssetLoader.pausePlaySp[1].getWidth()) {
                    if (input.y > AssetLoader.pausePlaySp[1].getY() && input.y < AssetLoader.pausePlaySp[1].getY() + AssetLoader.pausePlaySp[1].getHeight()) {
                        pause = false;
                    }
                }


                if (input.x > AssetLoader.backSpPlay.getX() && input.x < AssetLoader.backSpPlay.getX() + AssetLoader.backSpPlay.getWidth()) {
                    if (input.y > AssetLoader.backSpPlay.getY() && input.y < AssetLoader.backSpPlay.getY() + AssetLoader.backSpPlay.getHeight()) {
                        AssetLoader.gameBg.stop();
                        AssetLoader.setVelocity(3);
                        setVisibleScreen("menu");
                    }
                }


            }


            if(levelChange){
                levelChange=!levelChange;
            }

        }


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}