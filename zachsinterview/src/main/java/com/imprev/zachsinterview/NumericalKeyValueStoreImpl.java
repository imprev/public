package com.imprev.zachsinterview;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyAPI.InvalidCredentialsException;
import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyAPI.TryAgainLaterException;
import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyAPIImpl;
import com.imprev.zachsinterview.thirdpartyapi.ThirdPartyAPI;

/**
 * This class wraps the httpClient that will communicate with our partners cloud based data store.  The third party api
 * stores values per key as a list of strings, so this will manage the transformation to and from an integer value
 * to a list of strings.  Additionally this class will add some retry logic in the event we encounter an exception 
 * with the third party API;
 *
 */
public class NumericalKeyValueStoreImpl implements NumericalKeyValueStore {

    private ThirdPartyAPI thirdPartyAPIClient;

    private int MAX_RETRIES = 3;

    public NumericalKeyValueStoreImpl (
            String username,
            String secretPassword,
            String host,
            int port) {

        this.thirdPartyAPIClient = new ThirdPartyAPIImpl(username, secretPassword, host, port);

        // lets ping the partner to verify our credentials work!
        try {
            this.thirdPartyAPIClient.verifyCredentials();
        } catch (IOException | TryAgainLaterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, Integer value) throws NumericalKeyValueException {

        int retries = 0;
        String errorMessage = null;
        while (retries < MAX_RETRIES) {
            try {
                // we will only store one value in the list data structure the third party API uses, so if a
                // value already exists we want to override it not append to the list.
                List<String> values = this.thirdPartyAPIClient.get(key);
                
                if (values.size() == 1) {
                    this.thirdPartyAPIClient.delete(key);
                }
                
                this.thirdPartyAPIClient.add(key, String.valueOf(value));
                
                return;

            } catch (IOException | TryAgainLaterException | InvalidCredentialsException e) {
                errorMessage = e.getMessage();
                retries++;
            }
        }
        throw new NumericalKeyValueException(errorMessage);
    }

    @Override
    public void delete(String key) throws NumericalKeyValueException {
        try {
            this.thirdPartyAPIClient.delete(key);
            
        } catch (IOException | TryAgainLaterException | InvalidCredentialsException e) {
            throw new NumericalKeyValueException(e.getMessage());
        }
    }

    @Override
    public Integer get(String key) throws NumericalKeyValueException {
        int retries = 0;
        String errorMessage = null;
        while (retries < MAX_RETRIES) {
            try {
                // we will only store one value in the list data structure the third party API uses
                return Integer.parseInt(this.thirdPartyAPIClient.get(key).get(0));

            } catch (Exception e) {
                errorMessage = e.getMessage();
                retries++;
            }
        }
        throw new NumericalKeyValueException(errorMessage);
    }

    @Override
    public Iterator<String> getKeysByPrefix(String prefix) throws NumericalKeyValueException {
        int retries = 0;
        String errorMessage = null;
        while (retries < MAX_RETRIES) {
            try {
                return this.thirdPartyAPIClient.getKeysByPrefix(prefix);

            } catch (Exception e) {
                errorMessage = e.getMessage();
                retries++;
            }
        }
        throw new NumericalKeyValueException(errorMessage);
    }

}
