package com.pocketpou.model.minigame;

import com.pocketpou.model.minigame.strategy.ScoringStrategy;

/*
 * PATTERN: Strategy
 * Rol: Contexto.
 * Función: Delegar el cálculo de monedas en la estrategia activa
 * para poder cambiar el cálculo sin modificar el minijuego.
 */
public abstract class MiniGame {

    private ScoringStrategy scoringStrategy;

    protected MiniGame(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    public void setScoringStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    public int calculateReward(int correctAnswers, int attempts, double elapsedTimeSeconds) {
        return scoringStrategy.calculateScore(correctAnswers, attempts, elapsedTimeSeconds);
    }

    public abstract String getName();
}
