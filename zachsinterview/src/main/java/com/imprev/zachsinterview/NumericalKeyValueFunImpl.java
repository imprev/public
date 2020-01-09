package com.imprev.zachsinterview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NumericalKeyValueFunImpl implements NumericalKeyValueFun {
    
    private NumericalKeyValueStore keyValueStore;
    
    public NumericalKeyValueFunImpl(NumericalKeyValueStore keyValueStore) {
        this.keyValueStore = keyValueStore;
    }

    @Override
    public Integer sumByPrefix(String prefix) {
        
        int sum = 0;
        try {
            Iterator<String> iter = this.keyValueStore.getKeysByPrefix(prefix);
            List<String> allKeys = new ArrayList<String>();
            iter.forEachRemaining(allKeys::add);
            
            for (String key : allKeys) {
                sum += this.keyValueStore.get(key);
            }
            
        } catch (NumericalKeyValueException e) {
            e.printStackTrace();
        }
        
        return sum;
    }

}
