package com.pocketpou.model.minigame.strategy;

/*
 * PATTERN: Strategy
 * Rol: Estrategia concreta.
 * Función: Premiar los aciertos y bonificar las partidas rápidas.
 */
public final class SpeedScoring implements ScoringStrategy {

    public int calculateScore(int correctAnswers, int attempts, double elapsedTimeSeconds) {
        int reward = correctAnswers * 10;

        if (correctAnswers > 0) {
            if (elapsedTimeSeconds < 5) {
                reward += 20;
            } else if (elapsedTimeSeconds <= 10) {
                reward += 10;
            }
        }

        return reward;
    }
}
