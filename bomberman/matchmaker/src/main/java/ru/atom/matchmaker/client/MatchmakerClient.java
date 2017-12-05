package ru.atom.matchmaker.client;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class MatchmakerClient {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String PROTOCOL = "http://";
    private static final String HOST = "localhost";
    private static final String PORT = ":8090";

    public static Response create(int playerCount) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Request request = new Request.Builder()
                .post(RequestBody.create(mediaType, "playerCount=" + playerCount))
                .url(PROTOCOL + HOST + PORT + "/game/create")
                .build();

        return client.newCall(request).execute();
    }

    public static Response start(Integer gameId) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        Request request = new Request.Builder()
                .post(RequestBody.create(mediaType, "gameId={" + gameId + "}"))
                .url(PROTOCOL + HOST + PORT + "/game/start")
                .build();

        return client.newCall(request).execute();
    }
}
