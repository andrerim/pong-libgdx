package com.anri.game.pong.states;

import com.anri.game.pong.Pong;

public interface GameState {
    GameState doState(Pong p);
}
