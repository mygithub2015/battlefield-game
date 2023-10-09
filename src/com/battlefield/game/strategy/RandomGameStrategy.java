package com.battlefield.game.strategy;

import java.util.Random;

import com.battlefield.model.Pair;

public class RandomGameStrategy implements GameStrategy {

    private Random random;

    public RandomGameStrategy() {
        this.random = new Random();
    }
    @Override
    public Pair getNextCoordinates(int N, int M) {
        int x = this.random.nextInt(N);
        int y = this.random.nextInt(N / 2) + M;
        return new Pair(x, y);
    }
    
}
