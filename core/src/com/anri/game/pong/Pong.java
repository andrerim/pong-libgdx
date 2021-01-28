package com.anri.game.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;


public class Pong extends ApplicationAdapter {
	SpriteBatch batch;
	int width, height;
	Ball ball;
	ShapeRenderer shapeRenderer;
	int playerLScore, playerRScore;
	BitmapFont font;
	Paddle paddleLeft, paddleRight;

	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		this.ball = new Ball(randomInt(), randomInt(), width, height);
		this.paddleLeft = new Paddle(width, height, true);
		this.paddleRight = new Paddle(width, height, false);
		font = new BitmapFont();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.paddleRight.update();
		this.paddleLeft.update();

		ball.update();
		int scored = ball.pointScored();
		if (scored != 0){
			int t = scored > 0 ? playerLScore++ : playerRScore++;
		};


		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.circle(ball.xPos, ball.yPos, 50);
		shapeRenderer.end();


		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(paddleLeft.xPos, paddleLeft.yPos, paddleLeft.paddleWidth, paddleLeft.paddleHeight);
		shapeRenderer.rect(paddleRight.xPos, paddleRight.yPos, paddleRight.paddleWidth, paddleRight.paddleHeight);
		shapeRenderer.end();

		batch.begin();
		font.getData().setScale(2f);
		font.draw(batch, ""+playerLScore, 0, height);
		font.draw(batch, ""+playerRScore, width-100, height);

		batch.end();
		paddleCollision();

	}

	public static int randomInt(){
		Random random = new Random();
		return (random.nextInt(3) + 1) * 10;
	}

	public void paddleCollision(){
		if (ball.xVelocity > 0) {
			if (paddleRight.xPos < ball.xPos + 50 &&
					paddleRight.xPos + paddleRight.paddleWidth > ball.xPos - 50 &&
					paddleRight.yPos < ball.yPos + 50 &&
					paddleRight.yPos + paddleRight.paddleHeight > ball.yPos - 50)
			{
				ball.reverse();
			}
		} else if (ball.xVelocity < 0 ){
				if (paddleLeft.xPos < ball.xPos + 50 &&
						paddleLeft.xPos + paddleLeft.paddleWidth > ball.xPos - 50 &&
						paddleLeft.yPos < ball.yPos + 50 &&
						paddleLeft.yPos + paddleLeft.paddleHeight > ball.yPos - 50)
				{
					ball.reverse();
				}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}
}
