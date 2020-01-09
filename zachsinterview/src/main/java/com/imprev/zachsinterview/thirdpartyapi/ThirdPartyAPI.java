package com.imprev.zachsinterview.thirdpartyapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * This is a third part library we are committed to using for internal storage, the relevant interface with some
 * defined Exception types are available to understand how it is used.  This file should not be included in your review,
 * it is only here for reference on how this API operates.
 *
 */
public interface ThirdPartyAPI {
    
    /**
     * Should be called before making any other call to the API to ensure credentials are valid, otherwise an
     * InvalidCredentialsException will be thrown in subsequent method calls.
     */
    boolean verifyCredentials() throws IOException, TryAgainLaterException;
    
    /**
     * Adds a value to a list based on the key
     */
    void add(String key, String value) throws IOException, TryAgainLaterException, InvalidCredentialsException;
    
    /**
     * Deletes all values for a key
     */
    void delete(String key) throws IOException, TryAgainLaterException, InvalidCredentialsException;
    
    /**
     * Get all values for a given key
     */
    List<String> get(String key) throws IOException, TryAgainLaterException, InvalidCredentialsException;
    
    /**
     * Get all keys that start with the passed in prefix, returns an iterator because number of keys could be very large
     */
    Iterator<String> getKeysByPrefix(String prefix);
    
    //
    // Exception types for reference
    //
    /**
     * Implies the service can be communicated with over http but the interal data store is not operational for a short
     * time but is expected to be available again within a few minutes.
     */
    public class TryAgainLaterException extends Exception {}
    
    /**
     * Attempting to request a resource with invalid credentials
     */
    public class InvalidCredentialsException extends Exception {}
}
