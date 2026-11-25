package com.pocketpou.model.minigame.strategy;

/*
 * PATTERN: Strategy
 * Rol: Estrategia concreta.
 * Función: Premiar los aciertos y descontar monedas por los fallos.
 */
public final class AccuracyScoring implements ScoringStrategy {

    public int calculateScore(int correctAnswers, int attempts, double elapsedTimeSeconds) {
        int failedAttempts = attempts - correctAnswers;
        int reward = correctAnswers * 10 - failedAttempts * 2;

        return Math.max(0, reward);
    }
}
