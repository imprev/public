package com.imprev.zachsinterview;

public class Main {

    /**
     * Demonstrates how to connect to thirdPartyAPI and tests how the numerical key store may be used.
     */
    public static void main(String[] argv) throws NumberStoreException {
        
        String username = "moxiworks";
        String password = "fjdskal3kd!jdkdInterviewFun!!!";
        String apiUrl = "https://example.com/thirdPartyApiURL";
        
        NumberStore numberStore = new NumberStoreImpl(username, password, apiUrl , 80);
        NumberStoreAdditionalOperations keyFun = new NumberStoreAdditionalOperationsImpl(numberStore);
        
        System.out.println("Inserting 4 key/value pairs into data store");
        numberStore.put("abc", 1);
        numberStore.put("bcd", 2);
        numberStore.put("abcd", 3);
        numberStore.put("abcde", 4);

        // test getting value of a key
        Integer value = numberStore.get("abc");
        System.out.println("The value of abc should be 1, actual value: " + value);

        // test deleting a key
        numberStore.delete("abc");
        value = numberStore.get("abc");
        System.out.println("After deleting key: abc the value should now be null, actual value: " + value);

        // test getting the sum based on prefix
        Integer sum = keyFun.sumByPrefix("abc");
        System.out.println("Sum of prefix: abc should be 7, actual value: " + sum);

    }
}
