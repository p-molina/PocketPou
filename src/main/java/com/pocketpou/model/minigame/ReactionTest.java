package com.pocketpou.model.minigame;

import com.pocketpou.model.minigame.strategy.SpeedScoring;

import java.util.Random;

/* Genera la espera de una ronda y mide el tiempo desde la señal. */
public final class ReactionTest extends MiniGame {

    private final int delayMillis;
    private long signalTime;
    private boolean signalShown;

    public ReactionTest(Random random) {
        super(new SpeedScoring());
        delayMillis = 1000 + random.nextInt(2001);
    }

    public String getName() {
        return "Prueba de reacción";
    }

    public int getDelayMillis() {
        return delayMillis;
    }

    public void markSignal(long time) {
        signalTime = time;
        signalShown = true;
    }

    public long getReactionNanos(long answerTime) {
        long elapsed = answerTime - signalTime;
        return signalShown && elapsed >= 0 ? elapsed : -1;
    }
}
