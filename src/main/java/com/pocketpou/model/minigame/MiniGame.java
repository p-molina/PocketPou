package com.pocketpou.model.minigame;

import com.pocketpou.model.minigame.strategy.ScoringStrategy;

import java.util.Objects;

/*
 * PATTERN: Strategy
 * Rol: Contexto.
 * Función: Mantener una estrategia intercambiable y delegar
 * en ella el cálculo de la recompensa en monedas.
 */
public abstract class MiniGame {

    private ScoringStrategy scoringStrategy;

    protected MiniGame(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = Objects.requireNonNull(scoringStrategy, "scoringStrategy must not be null");
    }

    public ScoringStrategy getScoringStrategy() {
        return scoringStrategy;
    }

    public void setScoringStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = Objects.requireNonNull(scoringStrategy, "scoringStrategy must not be null");
    }

    public int calculateReward(int correctAnswers, int attempts, long elapsedTimeMillis) {
        return scoringStrategy.calculateScore(correctAnswers, attempts, elapsedTimeMillis);
    }

    public abstract String getName();
}
