package main;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    public static User getUserById(int id) throws IOException, InterruptedException {
        String url = URI_USERS + "/" + id;
        return HttpUtils.getUser(URI.create(url));
    }

    public static User getUserByUsername(String username) throws IOException, InterruptedException {
        String uri = URI_USERS + "?username=" + username;
        JsonObject user = HttpUtils.getJsonArray(URI.create(uri)).get(0).getAsJsonObject();
        return gson.fromJson(user, User.class);
    }

    public static List<User> getUsers() throws IOException, InterruptedException {
        JsonArray array = HttpUtils.getJsonArray(URI.create(URI_USERS));
        List<User> users = new ArrayList<>();
        for (JsonElement user: array) {
            users.add(gson.fromJson(user, User.class));
        }
        return users;
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

    public static List<String> getTodosOfUserById(int userId) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        JsonArray array = HttpUtils.getJsonArray(URI.create(uri));
        List<String> todos = new ArrayList<>();
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
