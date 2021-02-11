package com.anri.game.pong;

public class Ball {
    public int xPos, yPos;
    public int xVelocity, yVelocity;
    private int screenWidth, screenHeight;

    public Ball(int xVelocity, int yVelocity, int width, int height){
        this.xVelocity = xVelocity;
        this.yVelocity = 1;
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

        xVelocity = xVelocity > 0 ? xVelocity + 1 : xVelocity - 1;
        yVelocity = Pong.randomInt();
        if (Pong.randomInt() < 11){
            yVelocity *= -1;
        }
        System.out.println(xVelocity);
    }




    public void reverse(){
        if (yVelocity < 6 && yVelocity > -6){
            yVelocity = yVelocity > 0 ? yVelocity + 1 : yVelocity - 1;
        }
        xVelocity = -xVelocity;
    }
}
