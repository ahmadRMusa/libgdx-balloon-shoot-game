package com.yazlab.balonpatlatma.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yazlab.balonpatlatma.BalonPatlatma;
import com.yazlab.balonpatlatma.assets.AssetLoader;
import com.yazlab.balonpatlatma.objects.Player;

public class About extends GameScreen {

    private SpriteBatch batch;

    private Sprite bghighSp,backSp;
    private BitmapFont font;

    public About (BalonPatlatma balonPatlatma){
        super(balonPatlatma);
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);
        bghighSp = new Sprite();
        backSp = new Sprite();
        bghighSp = AssetLoader.bgSp[5];
        backSp = AssetLoader.backSp;

        font = new BitmapFont();

    }

    @Override
    public void Update(float delta) {

    }

    @Override
    public void Draw(float delta) {

        batch.begin();
        bghighSp.draw(batch);
        backSp.draw(batch);

        font.getData().setScale(2.0f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        AssetLoader.timerLayout.setText(font, "HAKKIMIZDA");
        font.draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2, 300*sizeSetHeight);
        AssetLoader.timerLayout.setText(font, "OKAN DEMIR 110201011");
        font.draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2, 250*sizeSetHeight);
        AssetLoader.timerLayout.setText(font, "Muhammed ÖZKURT 120201002");
        font.draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2, 225*sizeSetHeight);
        AssetLoader.timerLayout.setText(font, "Mustafa DINDAR 120201067");
        font.draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2, 200*sizeSetHeight);
        AssetLoader.timerLayout.setText(font, "Eray AYDIN 130201096");
        font.draw(batch,AssetLoader.timerLayout,(gameWidth - AssetLoader.timerLayout.width)/2, 175*sizeSetHeight);

/*
        AssetLoader.fontWhite[7].draw(batch,"Okan DEMİR       110201011",200*sizeSetWidht,250*sizeSetHeight);
        AssetLoader.fontWhite[7].draw(batch,"Muhammed ÖZKURT  120201002",200*sizeSetWidht,225*sizeSetHeight);
        AssetLoader.fontWhite[7].draw(batch,"Mustafa DINDAR   120201067",200*sizeSetWidht,200*sizeSetHeight);
        AssetLoader.fontWhite[7].draw(batch,"Eray AYDIN       130201096",200*sizeSetWidht,175*sizeSetHeight);*/
        batch.end();

    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int inputx=screenX,inputy=Gdx.graphics.getHeight()-screenY;

        if (inputx >= AssetLoader.backSp.getWidth() && inputx <= AssetLoader.backSp.getWidth()*2)
            if (inputy >= AssetLoader.backSp.getHeight()+20 && inputy <= AssetLoader.backSp.getHeight()*2+20)
                setVisibleScreen("menu");
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
