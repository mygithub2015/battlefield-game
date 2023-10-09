package com.battlefield.game.strategy;

import com.battlefield.model.Pair;

public interface GameStrategy {
    Pair getNextCoordinates(int N, int M);
}
