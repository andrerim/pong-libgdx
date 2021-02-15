package com.anri.game.pong.states;

import com.anri.game.pong.Pong;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayState implements GameState {
    @Override
    public GameState doState(Pong p) {

        p.paddleRight.update();
        p.paddleLeft.updateAi(p.ball.yVelocity, p.ball.yPos);

        p.ball.update();
        int scored = p.ball.pointScored();
        if (scored != 0) {
            if (scored > 0) {
                p.playerLScore++;
            } else if (scored < 0) {
                p.playerRScore++;
            }
        }

        p.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        p.shapeRenderer.circle(p.ball.xPos, p.ball.yPos, 50);
        p.shapeRenderer.end();

        p.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        p.shapeRenderer.rect(p.paddleLeft.getxPos(), p.paddleLeft.getyPos(), p.paddleLeft.getPaddleWidth(), p.paddleLeft.getPaddleHeight());
        p.shapeRenderer.rect(p.paddleRight.getxPos(), p.paddleRight.getyPos(), p.paddleRight.getPaddleWidth(), p.paddleRight.getPaddleHeight());
        p.shapeRenderer.end();

        p.batch.begin();
        p.font.getData().setScale(4f);
        p.font.draw(p.batch, "" + p.playerLScore, 40, p.screenHeight - 10);
        p.font.draw(p.batch, "" + p.playerRScore, p.screenWidth - 60, p.screenHeight - 10);

        p.batch.end();
        p.paddleCollision();

        if (p.playerRScore >= 21 || p.playerLScore >= 21) {
            return p.gameOverState;
        }
        return p.currentState;
    }
}
