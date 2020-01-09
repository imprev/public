package com.imprev.zachsinterview;

/**
 * Provides some additional methods we may want to extend our NumericalKeyValueStore library with
 */
public interface NumberStoreAdditionalOperations {
    
    /**
     *  Sum the value of all keys sharing the given prefix
     */
    public Integer sumByPrefix(String prefix);

}
