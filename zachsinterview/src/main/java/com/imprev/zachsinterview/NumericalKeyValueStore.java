package com.imprev.zachsinterview;

import java.util.Iterator;

public interface NumericalKeyValueStore {

    void put(String key, Integer value) throws NumericalKeyValueException;
    void delete(String key) throws NumericalKeyValueException;
    Integer get(String key) throws NumericalKeyValueException;
    Iterator<String> getKeysByPrefix(String prefix) throws NumericalKeyValueException;
}
