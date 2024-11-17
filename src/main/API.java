package main;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class API {

    final static String URI_USERS = "https://jsonplaceholder.typicode.com/users";
    final static User DEFAULT_USER = new User(1, "Tykhon Vilkhoviy", "ks1on", "ttt@gmail.com", "0123456789");
    final static Gson gson = new Gson();

    public static User postUser(URI uri, User user) throws IOException, InterruptedException {
       return HttpUtils.postUser(uri, user);
    }

    public static User putUser(URI uri, User user) throws IOException, InterruptedException {
        return HttpUtils.putUser(uri, user);
    }

    public static int deleteUserByIdId(int id) throws IOException, InterruptedException {
        return HttpUtils.deleteUserById(URI.create(URI_USERS + "/" + id));
    }

    public static int deleteUserByUsername(String username) throws IOException, InterruptedException {
        return HttpUtils.deleteUserByUsername(URI.create(URI_USERS + "?username=" + username));
    }

    // Task 2

    public static void getCommentsByUserId(int userId) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + userId + "/comments";
        JsonArray array = HttpUtils.getJsonArray(URI.create(uri));
        JsonArray array1 = new JsonArray();
        int max = getMaxPostId(array);

        for (JsonElement json: array) {
            if (json.getAsJsonObject().get("postId").getAsInt() == max) {
                array1.add(json);
            }
        }

        String fileName = "user-" + userId + "-post-" + max + "-comments.json";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(gson.toJson(array1));
        }
    }

    private static int getMaxPostId(JsonArray array) {
        int max = array.get(0).getAsJsonObject().get("postId").getAsInt();
        for (int i = 0; i < array.asList().size(); i++) {
            if (array.get(i).getAsJsonObject().get("postId").getAsInt() >= max) {
                max = array.get(i).getAsJsonObject().get("postId").getAsInt();
            }
        }
        return max;
    }

    // Task 3

    public static ArrayList<String> getTodosOfUserById(int userId) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        JsonArray array = HttpUtils.getJsonArray(URI.create(uri));
        ArrayList<String> todos = new ArrayList<>();
        int counter = 1;
        for (JsonElement todo: array) {
            if (todo.getAsJsonObject().get("completed").getAsString().equals("true")) {
                todos.add(counter + " : " + todo.getAsJsonObject().get("title").getAsString());
                counter++;
            }
        }
        return todos;
    }
}
