package com.anri.game.pong;

import com.anri.game.pong.states.GameOverState;
import com.anri.game.pong.states.GameState;
import com.anri.game.pong.states.InfoState;
import com.anri.game.pong.states.PlayState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;


public class Pong extends ApplicationAdapter {
    public SpriteBatch batch;
    public Ball ball;
    public ShapeRenderer shapeRenderer;
    public int playerLScore, playerRScore = 0;
    public BitmapFont font;
    public Paddle paddleLeft, paddleRight;
    public static int screenWidth, screenHeight;
    public final InfoState infoState = new InfoState();
    public final PlayState playState = new PlayState();
    public final GameOverState gameOverState = new GameOverState();
    public GameState currentState = infoState;


    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        // screenWidth and screenHeight must be set before initializing ball and paddle
        screenWidth = Gdx.graphics.getWidth();
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
        currentState = currentState.doState(this);
    }


    public void reset() {
        ball.reset();
        paddleLeft.resetPosition();
        paddleRight.resetPosition();
        playerLScore = 0;
        playerRScore = 0;
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
