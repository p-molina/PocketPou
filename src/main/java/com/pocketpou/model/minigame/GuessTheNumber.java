package com.pocketpou.model.minigame;

import com.pocketpou.model.minigame.strategy.AccuracyScoring;

import java.util.Random;

/* Genera el número secreto y controla los intentos de una ronda. */
public final class GuessTheNumber extends MiniGame {

    public static final int MAX_NUMBER = 20;
    public static final int MAX_ATTEMPTS = 5;

    private final int answer;
    private int attempts;
    private boolean won;

    public GuessTheNumber(Random random) {
        super(new AccuracyScoring());
        answer = random.nextInt(MAX_NUMBER) + 1;
    }

    public String getName() {
        return "Adivina el número";
    }

    public int guess(int number) {
        attempts++;
        won = answer == number;
        return Integer.compare(answer, number);
    }

    public boolean isFinished() {
        return won || attempts >= MAX_ATTEMPTS;
    }

    public int getAnswer() {
        return answer;
    }

    public int getReward(double elapsedTimeSeconds) {
        return calculateReward(won ? 1 : 0, attempts, elapsedTimeSeconds);
    }
}
