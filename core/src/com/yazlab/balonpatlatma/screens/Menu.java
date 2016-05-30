package com.yazlab.balonpatlatma.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.yazlab.balonpatlatma.BalonPatlatma;
import com.yazlab.balonpatlatma.assets.AssetLoader;
import com.yazlab.balonpatlatma.objects.Button;


public class Menu extends GameScreen {

    private SpriteBatch batch;
    private Button [] buttons;
    private Vector3 input;
    public static boolean soundState;

    public Menu ( BalonPatlatma balonPatlatma){
        super(balonPatlatma);
        Gdx.input.setInputProcessor(this);

        batch = new SpriteBatch();
        input = new Vector3();


        buttons = new Button[3];
        buttons[0] = new Button();
        buttons[1] = new Button();
        buttons[2] = new Button();


    }
    @Override
    public void Update(float delta) {

    }

    @Override
    public void Draw(float delta) {
        batch.begin();



        AssetLoader.bgSp[4].draw(batch);


        if(!soundState) {

            AssetLoader.soundOnOffSp[0].draw(batch);

        }else{

            AssetLoader.soundOnOffSp[1].draw(batch);

        }

        AssetLoader.timerLayout.setText(AssetLoader.fontWhite[5], "yeni oyun");
        buttons[0].setButton((int)((gameWidth - AssetLoader.timerLayout.width)/2),(int)(gameHeight - 14*40*sizeSetHeight),(int)AssetLoader.timerLayout.width,(int)(40*sizeSetHeight));
        AssetLoader.fontWhite[5].draw(batch, AssetLoader.timerLayout, buttons[0].getPosX(), buttons[0].getPosY()+40*sizeSetHeight);
        //System.out.println(buttons[0].getPosX()+" "+buttons[0].getPosY());

        AssetLoader.timerLayout.setText(AssetLoader.fontWhite[5], "hakkimizda");
        buttons[1].setButton((int)((gameWidth - AssetLoader.timerLayout.width)/2),(int)(gameHeight - 15*40*sizeSetHeight),(int)AssetLoader.timerLayout.width,(int)(40*sizeSetHeight));
        AssetLoader.fontWhite[5].draw(batch, AssetLoader.timerLayout, buttons[1].getPosX(), buttons[1].getPosY()+40*sizeSetHeight);

        AssetLoader.timerLayout.setText(AssetLoader.fontWhite[5], "cikis");
        buttons[2].setButton((int)((gameWidth - AssetLoader.timerLayout.width)/2),(int)(gameHeight - 16*40*sizeSetHeight),(int)AssetLoader.timerLayout.width,(int)(40*sizeSetHeight));
        AssetLoader.fontWhite[5].draw(batch, AssetLoader.timerLayout, buttons[2].getPosX(), buttons[2].getPosY()+40*sizeSetHeight);



        batch.end();

    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        input.x=screenX;
        input.y=gameHeight-screenY;
        //setVisibleScreen("play");
        //System.out.println(input.x+" "+input.y);

        if (input.x >= buttons[0].getPosX() && input.x <= buttons[0].getPosXplus())
            if (input.y >= buttons[0].getPosY() && input.y <= buttons[0].getPosYplus()){
                setVisibleScreen("play");
            }

        if (input.x >= buttons[1].getPosX() && input.x <= buttons[1].getPosXplus())
            if (input.y >= buttons[1].getPosY() && input.y <= buttons[1].getPosYplus()){
                setVisibleScreen("about");
            }

        if (input.x >= buttons[2].getPosX() && input.x <= buttons[2].getPosXplus())
            if (input.y >= buttons[2].getPosY() && input.y <= buttons[2].getPosYplus()){
                Gdx.app.exit();
                //System.exit(1);
            }


        if (input.x > AssetLoader.soundOnOffSp[0].getX() && input.x < AssetLoader.soundOnOffSp[0].getX() + AssetLoader.soundOnOffSp[0].getWidth()) {
            if (input.y > AssetLoader.soundOnOffSp[0].getY() && input.y < AssetLoader.soundOnOffSp[0].getY() + AssetLoader.soundOnOffSp[0].getHeight()) {
                soundState = !soundState;
            }
        }


        return true;
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
