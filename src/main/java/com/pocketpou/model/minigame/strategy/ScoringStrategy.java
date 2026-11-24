package com.pocketpou.model.minigame.strategy;

/*
 * PATTERN: Strategy
 * Rol: Interfaz de la estrategia.
 * Función: Definir el contrato para calcular
 * la puntuación de un minijuego.
 */
public interface ScoringStrategy {

    int calculateScore(int performanceValue);
}
