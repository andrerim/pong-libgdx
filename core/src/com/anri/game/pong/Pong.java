package com.anri.game.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;


public class Pong extends ApplicationAdapter {
    SpriteBatch batch;
    Ball ball;
    ShapeRenderer shapeRenderer;
    int playerLScore, playerRScore = 0;
    BitmapFont font;
    Paddle paddleLeft, paddleRight;
    boolean infoMessage = true;
    public static int screenWidth, screenHeight;


    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        // screenWidth and screenHeight must be set before initializing ball and paddle
        screenWidth = Gdx.graphics.getWidth();;
        screenHeight = Gdx.graphics.getHeight();
        this.ball = Ball.getInstance();
        this.paddleLeft = new Paddle(screenWidth, screenHeight, true);
        this.paddleRight = new Paddle(screenWidth, screenHeight, false);
        font = new BitmapFont();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        if (playerRScore >= 21 || playerLScore >= 21) {
            batch.begin();
            font.getData().setScale(4f);
            font.draw(batch, "WINNER!!! Click to play again", screenWidth / 2 - 200, screenHeight / 2);
            batch.end();

            if (Gdx.input.justTouched()) {
                this.ball.reset();
                paddleLeft.resetPosition();
                paddleRight.resetPosition();
                playerLScore = 0;
                playerRScore = 0;
            }
        } else {
            if (infoMessage){
                batch.begin();
                font.getData().setScale(3f);
                font.draw(batch, "Move the paddle using keyboard: Up and Down", screenWidth / 2 - 400, screenHeight / 2);
                font.draw(batch, "Click to play", screenWidth / 2 - 50, screenHeight / 2 - 100);
                batch.end();

                if (Gdx.input.justTouched()) {
                    infoMessage = false;
                } else {
                    return;
                }

            }

            this.paddleRight.update();
            this.paddleLeft.updateAi(ball.yVelocity, ball.yPos);

            ball.update();
            int scored = ball.pointScored();
            if (scored != 0) {
                 if (scored > 0) {
                     playerLScore++;
                 } else if (scored < 0){
                     playerRScore++;
                 }
            }

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(ball.xPos, ball.yPos, 50);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(paddleLeft.getxPos(), paddleLeft.getyPos(), paddleLeft.getPaddleWidth(), paddleLeft.getPaddleHeight());
            shapeRenderer.rect(paddleRight.getxPos(), paddleRight.getyPos(), paddleRight.getPaddleWidth(), paddleRight.getPaddleHeight());
            shapeRenderer.end();

            batch.begin();
            font.getData().setScale(4f);
            font.draw(batch, "" + playerLScore, 40, screenHeight - 10);
            font.draw(batch, "" + playerRScore, screenWidth - 60, screenHeight - 10);

            batch.end();
            paddleCollision();
        }
    }

    public static int randomInt() {
        Random random = new Random();
        return (random.nextInt(3) + 1) * 5;
    }

    public void paddleCollision() {
        int paddleRX = paddleRight.getxPos();
        int paddleRY = paddleRight.getyPos();
        int paddleLX = paddleLeft.getxPos();
        int paddleLY = paddleLeft.getyPos();

        if (ball.xVelocity > 0) {
            if (paddleRX < ball.xPos + 50 &&
                    paddleRX + paddleRight.getPaddleWidth() > ball.xPos - 50 &&
                    paddleRY < ball.yPos + 50 &&
                    paddleRY + paddleRight.getPaddleHeight() > ball.yPos - 50) {
                ball.reverse();
            }
        } else if (ball.xVelocity < 0) {
            if (paddleLX < ball.xPos + 50 &&
                    paddleLX + paddleLeft.getPaddleWidth() > ball.xPos - 50 &&
                    paddleLY < ball.yPos + 50 &&
                    paddleLY + paddleLeft.getPaddleHeight() > ball.yPos - 50) {
                ball.reverse();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
