package com.yazlab.balonpatlatma.objects;


public class Button implements GameObjects {

    private int posX, posY, posXplus, posYplus, width, height;


    public Button(){

    }

    @Override
    public void Draw(float delta) {

    }

    @Override
    public void Update(float delta) {

    }

    @Override
    public void Dispose() {

    }



    public void setButton(int posX, int posY, int width, int height){
        setPosX(posX);
        setPosY(posY);
        setPosXplus(posX+width);
        setPosYplus(posY+height);
        setWidth(width);
        setHeight(height);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPosXplus() {
        return posXplus;
    }

    public void setPosXplus(int posXplus) {
        this.posXplus = posXplus;
    }

    public int getPosYplus() {
        return posYplus;
    }

    public void setPosYplus(int posYplus) {
        this.posYplus = posYplus;
    }
}
