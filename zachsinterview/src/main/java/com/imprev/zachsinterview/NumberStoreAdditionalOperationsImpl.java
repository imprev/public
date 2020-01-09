package com.imprev.zachsinterview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NumberStoreAdditionalOperationsImpl implements NumberStoreAdditionalOperations {
    
    private NumberStore numberStore;
    
    public NumberStoreAdditionalOperationsImpl(NumberStore numberStore) {
        this.numberStore = numberStore;
    }

    @Override
    public Integer sumByPrefix(String prefix) {
        
        int sum = 0;
        try {
            Iterator<String> iter = this.numberStore.getKeysByPrefix(prefix);
            List<String> allKeys = new ArrayList<String>();
            iter.forEachRemaining(allKeys::add);
            
            for (String key : allKeys) {
                sum += this.numberStore.get(key);
            }
            
        } catch (NumberStoreException e) {
            e.printStackTrace();
        }
        
        return sum;
    }

}
