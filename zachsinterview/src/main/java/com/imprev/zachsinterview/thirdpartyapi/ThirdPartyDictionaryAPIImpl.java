package com.imprev.zachsinterview.thirdpartyapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * This should be treated as a black box as it is internal to our third-party partner, ignore for purposes of review,
 * the interface should provide sufficient information as to how this operates.
 *
 */
public class ThirdPartyDictionaryAPIImpl implements ThirdPartyDictionaryAPI {

    public ThirdPartyDictionaryAPIImpl(String username, String secretPassword, String host, int port) {
        // hidden
    }

    @Override
    public boolean verifyCredentials() {
        // hidden
        return false;
    }

    @Override
    public void add(String key, String value) throws IOException {
        // hidden
    }

    @Override
    public void delete(String key) throws IOException {
        // hidden
    }

    @Override
    public List<String> get(String key) throws IOException {
        // hidden
        return null;
    }

    @Override
    public Iterator<String> getKeysByPrefix(String prefix) {
        // hidden
        return null;
    }

}
