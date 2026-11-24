package com.pocketpou.model.minigame.strategy;

/*
 * PATTERN: Strategy
 * Rol: Estrategia concreta.
 * Función: Representar la estrategia de recompensa
 * basada en los aciertos y los intentos del jugador.
 */
public final class AccuracyScoring implements ScoringStrategy {

    public int calculateScore(int correctAnswers, int attempts, long elapsedTimeMillis) {
        return 0;
    }
}
