package com.anri.game.pong.states;

import com.anri.game.pong.Pong;
import com.badlogic.gdx.Gdx;

public class GameOverState implements GameState {


    @Override
    public GameState doState(Pong p) {
        p.batch.begin();
        p.font.getData().setScale(4f);
        p.font.draw(p.batch, "WINNER!!! Click to play again", p.screenWidth / 2 - 200, p.screenHeight / 2);
        p.batch.end();

        if (Gdx.input.justTouched()) {
            p.reset();
            return p.playState;

        }
        return p.currentState;
    }
}
