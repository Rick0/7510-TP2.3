package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.Context;

import java.util.HashMap;
import java.util.Map;

public class ContextImpl implements Context {

    private Map<String, Object> values;

    public ContextImpl() {
        values = new HashMap<>();
    }

    private ContextImpl(Map<String, Object> values) {
        this.values = values;
    }

    /**
     * @param name Nombre de la variable a obtener
     * @param <T> tipo de dato esperado
     * @return la variable con el nombre deseado, o null si no existe la variable o si el tipo esperado no es correcto
     */
    @Override
    public <T> T getProperty(String name) {
        try {
            T value = (T) values.get(name);
            return value;
        } catch (ClassCastException ex) {
            return null;
        }
    }

    @Override
    public <T> void setProperty(String name, T value) {
        values.put(name, value);
    }

    @Override
    public Context duplicar() {
        return new ContextImpl(new HashMap<>(values));
    }
}
