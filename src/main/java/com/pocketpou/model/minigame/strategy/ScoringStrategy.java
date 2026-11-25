package com.pocketpou.model.minigame.strategy;

/*
 * PATTERN: Strategy
 * Rol: Interfaz de la estrategia.
 * Función: Definir el contrato para calcular la recompensa en monedas
 * a partir de los aciertos, los intentos y el tiempo en segundos.
 */
public interface ScoringStrategy {

    int calculateScore(int correctAnswers, int attempts, double elapsedTimeSeconds);
}
