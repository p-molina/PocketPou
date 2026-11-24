package com.pocketpou.model.minigame.strategy;

/*
 * PATTERN: Strategy
 * Rol: Estrategia concreta.
 * Función: Representar la estrategia de recompensa
 * basada en el tiempo empleado por el jugador.
 */
public final class SpeedScoring implements ScoringStrategy {

    public int calculateScore(int correctAnswers, int attempts, long elapsedTimeMillis) {
        return 0;
    }
}
