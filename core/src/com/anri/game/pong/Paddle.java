package com.anri.game.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Paddle {
    public int xPos, yPos;
    private int yVelocity = 10;
    public int paddleWidth = 10;
    public int paddleHeight = 300;
    private int screenWidth, screenHeight;
    private boolean isLeft;


    public Paddle(int width, int height, boolean left) {
        if (left) {
            this.xPos = paddleWidth;
        } else {
            this.xPos = width - paddleWidth;
        }
        this.yPos = (height / 2) - (paddleHeight / 2);
        this.screenWidth = width;
        this.screenHeight = height;
        this.isLeft = left;
    }


    void update() {
        if (this.isLeft) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if (this.yPos + this.paddleHeight <= screenHeight) {
                    yPos += yVelocity;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if (this.yPos >= 0) {
                    yPos -= yVelocity;
                }
            }
        } else {
            if (this.yPos + this.paddleHeight <= screenHeight) {
                yPos += yVelocity;
            }
            if (this.yPos >= 0) {
                yPos -= yVelocity;
            }

        }

    }

    public int pointScored() {
        if (this.xPos >= screenWidth) {
            resetPos();
            return -1;
        } else if (this.xPos <= 0) {
            resetPos();
            return 1;
        } else {
            return 0;
        }
    }

    public void resetPos() {
        xPos = screenWidth / 2;
        yPos = screenHeight / 2;
    }

    boolean collision() {
        return true;
    }

    void draw() {

    }

}
