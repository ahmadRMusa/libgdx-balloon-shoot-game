package com.yazlab.balonpatlatma.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yazlab.balonpatlatma.assets.AssetLoader;
import com.yazlab.balonpatlatma.screens.GameScreen;

import java.util.ArrayList;


public class Player implements GameObjects{


    private SpriteBatch batch;

    private FileHandle scoreFile;
    private String [] score;
    private Boolean fileControl;

    private int point;
    private ArrayList<Balloon> balloons;
    private int [] hittedBalloons;
    private boolean fileState;
    private int level;
    private int [] levelPoints;
    private int sumPoint;

    public Player(SpriteBatch sb){


        levelPoints = new int[3];
        hittedBalloons = new int[4];


        //scoreFile = Gdx.files.local("Score.txt");

        setBatch(sb);
        balloons = new ArrayList<Balloon>();
        setSumPoint(0);
        point = 0;
        level = 1;
        fileState=false;
        //readFromFile();
    }

    @Override
    public void Draw(float delta) {
        //AssetLoader.fontWhite.draw(getBatch(), String.valueOf(getPoint()), 40, gameHeight);
        AssetLoader.fontWhite[2].draw(batch,AssetLoader.pointLayout, gameWidth - AssetLoader.pointLayout.width,AssetLoader.pointLayout.height+10*sizeSetHeight);


        for(int i=0;i<4;i++) {
            AssetLoader.balloonScoreSp[i].draw(getBatch());
            AssetLoader.fontWhite[0].draw(batch,"X"+String.valueOf(this.hittedBalloons[i]),AssetLoader.balloonScoreSp[i].getX()+16*sizeSetWidht, AssetLoader.balloonScoreSp[i].getY()+30*sizeSetHeight);
        }

    }

    @Override
    public void Update(float delta) {


        calPoint();

        AssetLoader.pointLayout.setText(AssetLoader.fontWhite[2], String.valueOf(getSumPoint()+getPoint()));
    }

    @Override
    public void Dispose() {

    }


    public void writeToFile(String str ){

        readFromFile();
        String temporary="";
        String temporary1="";
        scoreFile.writeString("",false);
        if (fileControl==true){
            for (int i=0; i < score.length; i++){
                if (Integer.parseInt(str) > Integer.parseInt(score[i].trim()) ) {
                    temporary=score[i];
                    score[i]=str;
                    for (int k=i+1; k < score.length; k++){
                        temporary1=score[k];
                        score[k]=temporary;
                        temporary=temporary1;

                    }
                    break;
                }
            }
        }
        else {
            scoreFile.writeString(str+"\n",true);
        }

        for (int i=0;i<score.length;i++) scoreFile.writeString(score[i]+"\n",true);

    }

    public void readFromFile(){

        score = scoreFile.readString().split("\n");
        if (score.length != 0){
            if (Integer.parseInt(score[0].trim()) != 0){

                for (int i = 0; i < score.length; i++) score[i] = score[i].trim();
                fileControl=true;
            }
            else {
                System.out.println("dosyada değer yok");
                fileControl=false;
            }


        }


    }
    public String getScore(int id){

        return score[id];
    }


    public ArrayList<Balloon> getBalloons() {
        return balloons;
    }

    public void setBalloons(ArrayList<Balloon> balloons) {
        this.balloons = balloons;
    }

    public void addBalloons(Balloon balloon){
        getBalloons().add(balloon);
    }


    public void addHittedBalloons(int id){
        this.hittedBalloons[id]++;
    }

    public int getHittedBalloons(int id){
        return this.hittedBalloons[id];
    }

    public void clearHittedBalloons(){
        this.hittedBalloons[0]=0;
        this.hittedBalloons[1]=0;
        this.hittedBalloons[2]=0;
        this.hittedBalloons[3]=0;
    }

    public void calPoint(){
        //puan hesapla
        int totalPoint=0;
        for(int i =0 ; i<getBalloons().size();i++) {
            totalPoint+=getBalloons().get(i).getPoint();

        }
        System.out.println(totalPoint);
        setPoint(totalPoint);
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isFileState() {
        return fileState;
    }

    public void setFileState(boolean fileState) {
        this.fileState = fileState;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public int getLevelPoints(int id) {
        return levelPoints[id];
    }

    public void setLevelPoints(int id) {
        this.levelPoints[id] = getPoint();
    }


    public void setSumPoint(int sumPoint){
        this.sumPoint+=sumPoint;
    }

    public int getSumPoint(){
        return sumPoint;
    }
}
