package main;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils <T> {
    final static Gson gson = new Gson();
    final static HttpClient client = HttpClient.newHttpClient();

    public static User postUser(URI uri, User user) throws IOException, InterruptedException {
        String userJson = gson.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userJson))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), User.class);
    }

    public static User putUser(URI uri, User user) throws IOException, InterruptedException {
        String userJson = gson.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(userJson))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), User.class);
    }

    public static int deleteUserById(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public static int deleteUserByUsername(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public static JsonArray getJsonArray(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return JsonParser.parseString(response.body()).getAsJsonArray();
    }
}
