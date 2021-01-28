package com.anri.game.pong;

public class Ball {
    public int xPos, yPos;
    public int xVelocity, yVelocity;
    private int screenWidth, screenHeight;

    public Ball(int xVelocity, int yVelocity, int width, int height){
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xPos = width / 2;
        this.yPos = height / 2;
        this.screenWidth = width;
        this.screenHeight = height;
    }

    void update(){
        // check for collision
        if (this.yPos >= screenHeight || this.yPos <= 0) {
            yVelocity = -yVelocity;
        }

        this.xPos += xVelocity;
        this.yPos += yVelocity;
    }

    public int pointScored(){
        if (this.xPos >= screenWidth){
            resetPos();
            return -1;
        }
        else if (this.xPos <= 0) {
            resetPos();
            return 1;
        } else {
            return 0;
        }
    }

    public void resetPos(){
        xPos = screenWidth / 2;
        yPos = screenHeight / 2;
        xVelocity = Pong.randomInt();
        yVelocity = Pong.randomInt();
    }

    public void reverse(){
        xVelocity = -xVelocity;
    }

    boolean collision(){
        return true;
    }

    void draw(){

    }

}
