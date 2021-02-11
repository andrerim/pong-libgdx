package com.anri.game.pong;

import java.util.Random;

public class Ball {
    public int xPos, yPos;
    public int xVelocity, yVelocity;

    private Ball(){
        reset();
    }


    private static class SingletonHolder {
        private static final Ball ball = new Ball();
    }

    public static Ball getInstance() {
        return SingletonHolder.ball;
    }

    void update(){
        // check for collision
        if (this.yPos >= Pong.screenHeight || this.yPos <= 0) {
            yVelocity = -yVelocity;
        }
        this.xPos += xVelocity;
        this.yPos += yVelocity;
        System.out.println(Pong.screenWidth);
    }

    public int pointScored(){
        if (this.xPos >= Pong.screenWidth){
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

    public void reset(){
        this.xVelocity = randomXSpeed();
        this.yVelocity = randomYSpeed();
        this.xPos = Pong.screenWidth / 2;
        this.yPos = Pong.screenHeight / 2;
    }

    public void resetPos(){
        xPos = Pong.screenWidth / 2;
        yPos = Pong.screenHeight / 2;

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

    private int randomXSpeed(){
        Random random = new Random();
        return (random.nextInt(3) + 1) * 5;
    }

    private int randomYSpeed(){
        Random random = new Random();
        int speed = (random.nextInt(3) + 1) * 3;
        if (speed <= 6){
            speed *= -1;
        }
        return speed;
    }
}
