package com.imprev.zachsinterview;

public class Main {

    /**
     * Demonstrates how to connect to thirdPartyAPI and tests how the numerical key store may be used.
     */
    public static void main(String[] argv) throws NumericalKeyValueException {
        
        String username = "moxiworks";
        String password = "fjdskal3kd!jdkdInterviewFun!!!";
        String apiUrl = "https://example.com/thirdPartyApiURL";
        
        NumericalKeyValueStore keyStore = new NumericalKeyValueStoreImpl(username, password, apiUrl , 80);
        NumericalKeyValueFun keyFun = new NumericalKeyValueFunImpl(keyStore);
        
        System.out.println("Inserting 4 key/value pairs into data store");
        keyStore.put("abc", 1);
        keyStore.put("bcd", 2);
        keyStore.put("abcd", 3);
        keyStore.put("abcde", 4);

        // test getting value of a key
        Integer value = keyStore.get("abc");
        System.out.println("The value of abc should be 1, actual value: " + value);

        // test deleting a key
        keyStore.delete("abc");
        value = keyStore.get("abc");
        System.out.println("After deleting key: abc the value should now be null, actual value: " + value);

        // test getting the sum based on prefix
        Integer sum = keyFun.sumByPrefix("abc");
        System.out.println("Sum of prefix: abc should be 7, actual value: " + sum);

    }
}
