package com.imprev.homes;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;

public class Main {

    /** To Run:
     * 
     *  note: replace the bracketed [REDACTED_HOMES_TOKEN] with your actual token in quotes
     * mvn compile exec:java -Dexec.mainClass="com.imprev.homes.Main" -Dexec.args="[REDACTED_HOMES_TOKEN]"
     * 
     * ex.
     * mvn compile exec:java -Dexec.mainClass="com.imprev.homes.Main" -Dexec.args="aFakeTokenIsHereklfjdlks/sfdksld"
     * 
     */
    public static void main(String[] argv) throws Exception {

        if (argv.length != 1) {
            System.err.println("Must pass the HOMES API TOKEN as a single argument");
            return;
        }
        
        String listingId = "64330368";
        String homesToken = argv[0];

        String requestUrl = String.format("https://api.homesconnect.com/api/listing/%s/virtualtour?token=%s",
                listingId,
                homesToken);

        HttpClient httpClient = getHttpClient();
        HttpGet getRequest = new HttpGet();
        getRequest.setURI(new URI(requestUrl));
        getRequest.setHeader("Content-Type", "application/json");

        try {
            httpClient.execute(getRequest);
        }
        catch (SSLHandshakeException e) {
            System.err.print(e.getMessage());
            e.printStackTrace();
        }
    }

    private static HttpClient getHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoTimeout(5000).build();
        builder.setDefaultSocketConfig(socketConfig);
        builder.setConnectionTimeToLive(5000, TimeUnit.MILLISECONDS);
        return builder.build();
    }
}
