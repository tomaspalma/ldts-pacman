package org.ldts.pacman.controllers;

// O controller vai processar e efetuar mudanças num modelo
public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }
}
