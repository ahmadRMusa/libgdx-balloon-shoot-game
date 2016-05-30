package com.yazlab.balonpatlatma.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yazlab.balonpatlatma.assets.AssetLoader;

import java.util.Random;


public class Balloon implements GameObjects{

    //lifeTime
    //direction
    //point
    //color
    //

    private SpriteBatch batch;


    private int posX;
    private int posY;
    private int type;
    private int lifeTime;
    private boolean state;
    private float instantTime;
    private String direction;
    private int point;
    private String color;
    private Sprite sprite;
    private Random rnd;
    private int posxV, posyV;



    public Balloon(int type, float instantTime, SpriteBatch sb){

        setInstantTime(instantTime);
        setBatch(sb);
        rnd = new Random();


        setType(type);
        setState(true);

        switch (type){
            case 0:
                setColor("green");
                setDirection("up");
                setSprite(AssetLoader.balloonsSp[0]);
                setLifeTime(0);
                setPosX((rnd.nextInt((int)(gameWidth-getSprite().getWidth()))));
                setPosY(-(int)getSprite().getHeight());
                setPoint(5);
                setPosxV(AssetLoader.directionStates[1][0]);
                setPosyV(AssetLoader.directionStates[1][1]);
                break;//yeşil
            case 1:
                setColor("red");
                setDirection("xy");
                setSprite(AssetLoader.balloonsSp[1]);
                setLifeTime(0);
                //setPosX((rnd.nextInt(405)+25));
                //setPosY(rnd.nextInt(725));
                setPosX((rnd.nextInt((int)(gameWidth-getSprite().getWidth()))));
                setPosY((rnd.nextInt((int)(gameHeight-getSprite().getHeight()))));
                setPoint(10);

                int rndDir=rnd.nextInt(8);
                setPosxV(AssetLoader.directionStates[rndDir][0]);
                setPosyV(AssetLoader.directionStates[rndDir][1]);
                break;//kırmızı
            case 2:
                setColor("yellow");
                setDirection("random");
                setSprite(AssetLoader.balloonsSp[2]);
                setLifeTime(1);
                setPosX((rnd.nextInt((int)(gameWidth-getSprite().getWidth()))));
                setPosY(rnd.nextInt((int)(gameHeight-getSprite().getHeight())));
                setPoint(20);
                setPosxV(AssetLoader.directionStates[8][0]);
                setPosyV(AssetLoader.directionStates[8][1]);
                break;//sarı
            case 3:
                setColor("black");
                setDirection("up");
                setSprite(AssetLoader.balloonsSp[3]);
                setLifeTime(0);
                setPosX((rnd.nextInt((int)(gameWidth-getSprite().getWidth()))));
                setPosY(-50);
                setPoint(-10);
                setPosxV(AssetLoader.directionStates[1][0]);
                setPosyV(AssetLoader.directionStates[1][1]);
                break;//siyah

        }

    }//constructor



    @Override
    public void Draw(float delta) {

        if(isState()) {
            getSprite().setPosition(getPosX(), getPosY());
            getSprite().draw(getBatch());
        }
    }

    @Override
    public void Update(float delta) {

        if (getPosY() > gameHeight || getPosY() < -getSprite().getHeight() || getPosX() > gameWidth || getPosX() < 0) {
            setState(false);
        } else {
            setPosX(getPosX() + getPosxV());
            setPosY(getPosY() +getPosyV());
        }//end posY control

    }

    @Override
    public void Dispose() {

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public int getPosxV() {
        return posxV;
    }

    public void setPosxV(int posxV) {
        this.posxV = posxV;
    }

    public int getPosyV() {
        return posyV;
    }

    public void setPosyV(int posyV) {
        this.posyV = posyV;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public float getInstantTime() {
        return instantTime;
    }

    public void setInstantTime(float instantTime) {
        this.instantTime = instantTime;
    }


    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
