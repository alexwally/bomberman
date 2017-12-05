package ru.atom.matchmaker;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost";
    private static final String PORT = ":8080";

    @Test
    public void connect() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Request request = new Request.Builder()
                .post(RequestBody.create(mediaType, "name=Alex1991"))
                .url(PROTOCOL + HOST + PORT + "/matchmaker/join")
                .build();

        Response response = client.newCall(request).execute();

        assertEquals("2", response.body().string());
    }
}