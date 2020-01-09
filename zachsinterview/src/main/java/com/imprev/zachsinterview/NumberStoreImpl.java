package com.imprev.zachsinterview;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyDictionaryAPI.InvalidCredentialsException;
import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyDictionaryAPI.TryAgainLaterException;
import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyDictionaryAPIImpl;
import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyDictionaryAPI;

/**
 * This class wraps the httpClient that will communicate with our partners third party dictionary store.  The third party api
 * stores values per key as a list of strings, so this will manage the transformation to and from an integer value
 * to a list of strings.  Additionally this class will add some retry logic in the event we encounter an exception 
 * with the third party API;
 *
 */
public class NumberStoreImpl implements NumberStore {

    private ThirdPartyDictionaryAPI thirdPartyDictionaryAPIClient;

    private int MAX_RETRIES = 3;

    public NumberStoreImpl (
            String username,
            String secretPassword,
            String host,
            int port) {

        this.thirdPartyDictionaryAPIClient = new ThirdPartyDictionaryAPIImpl(username, secretPassword, host, port);

        // lets ping the partner to verify our credentials work!
        try {
            this.thirdPartyDictionaryAPIClient.verifyCredentials();
        } catch (IOException | TryAgainLaterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, Integer value) throws NumberStoreException {

        int retries = 0;
        String errorMessage = null;
        while (retries < MAX_RETRIES) {
            try {
                // we will only store one value in the list data structure the third party API uses, so if a
                // value already exists we want to override it not append to the list.
                List<String> values = this.thirdPartyDictionaryAPIClient.get(key);
                
                if (values.size() == 1) {
                    this.thirdPartyDictionaryAPIClient.delete(key);
                }
                
                this.thirdPartyDictionaryAPIClient.add(key, String.valueOf(value));
                
                return;

            } catch (IOException | TryAgainLaterException | InvalidCredentialsException e) {
                errorMessage = e.getMessage();
                retries++;
            }
        }
        throw new NumberStoreException(errorMessage);
    }

    @Override
    public void delete(String key) throws NumberStoreException {
        try {
            this.thirdPartyDictionaryAPIClient.delete(key);
            
        } catch (IOException | TryAgainLaterException | InvalidCredentialsException e) {
            throw new NumberStoreException(e.getMessage());
        }
    }

    @Override
    public Integer get(String key) throws NumberStoreException {
        int retries = 0;
        String errorMessage = null;
        while (retries < MAX_RETRIES) {
            try {
                // we will only store one value in the list data structure the third party API uses
                return Integer.parseInt(this.thirdPartyDictionaryAPIClient.get(key).get(0));

            } catch (Exception e) {
                errorMessage = e.getMessage();
                retries++;
            }
        }
        throw new NumberStoreException(errorMessage);
    }

    @Override
    public Iterator<String> getKeysByPrefix(String prefix) throws NumberStoreException {
        int retries = 0;
        String errorMessage = null;
        while (retries < MAX_RETRIES) {
            try {
                return this.thirdPartyDictionaryAPIClient.getKeysByPrefix(prefix);

            } catch (Exception e) {
                errorMessage = e.getMessage();
                retries++;
            }
        }
        throw new NumberStoreException(errorMessage);
    }

}
