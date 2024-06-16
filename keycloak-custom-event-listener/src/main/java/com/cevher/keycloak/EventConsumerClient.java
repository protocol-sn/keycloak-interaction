package com.cevher.keycloak;

import org.jboss.logging.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class EventConsumerClient {

    private static final Logger log = Logger.getLogger(EventConsumerClient.class);

    private EventConsumerClient() {}

    public static void postService(String data, List<String> targetUrls) {
        try {
            List<String> errors = new ArrayList<>();
            for (String url : targetUrls) {
                HttpURLConnection conn = makeCall(data, url);
                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    errors.add("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
                conn.disconnect();
            }

            if (!errors.isEmpty()) {
                errors.forEach(log::error);
            }
        } catch (IOException | URISyntaxException e) {
            log.error(e);
        }

    }

    private static @NotNull HttpURLConnection makeCall(String data, String urlString) throws IOException, URISyntaxException {
        URI url = new URI(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.toURL().openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");

        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        return conn;
    }

}