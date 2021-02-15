package com.anri.game.pong.states;

import com.anri.game.pong.Pong;
import com.badlogic.gdx.Gdx;

public class InfoState implements GameState {

    @Override
    public GameState doState(Pong p) {
        p.batch.begin();
        p.font.getData().setScale(3f);
        p.font.draw(p.batch, "Move the paddle using keyboard: Up and Down", p.screenWidth / 2 - 400, p.screenHeight / 2);
        p.font.draw(p.batch, "Click to play", p.screenWidth / 2 - 50, p.screenHeight / 2 - 100);
        p.batch.end();
        if (Gdx.input.justTouched()) {
            return p.playState;
        }
        return p.currentState;
    }
}
