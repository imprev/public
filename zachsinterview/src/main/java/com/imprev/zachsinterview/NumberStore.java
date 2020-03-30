package com.imprev.zachsinterview;

import java.util.Iterator;

/**
 * Wraps the third party dictionary API calls and performs the conversions to and from an integer to a list of strings
 */
public interface NumberStore {

    void put(String key, Integer value) throws NumberStoreException;
    void delete(String key) throws NumberStoreException;
    Integer get(String key) throws NumberStoreException;
    Iterator<String> getKeysByPrefix(String prefix) throws NumberStoreException;
}
