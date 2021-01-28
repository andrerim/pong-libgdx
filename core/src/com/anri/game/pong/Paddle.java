package com.anri.game.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Paddle {
    public int xPos, yPos;
    private int yVelocity = 20;
    public int paddleWidth = 10;
    public int paddleHeight = 200;
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
        if (!this.isLeft) {
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


        }
    }

    void updateAi(int ballVelocityY, int ballY) {

        if (ballVelocityY > 0 && ballY > this.yPos) {
            if (this.yPos + this.paddleHeight <= screenHeight) {
                yPos += yVelocity;
            }
        }
        else if (ballVelocityY < 0 || ballY < this.yPos) {
            if (this.yPos >= 0 && ballVelocityY < 0) {
                yPos -= yVelocity;
            }
        }
    }
}
