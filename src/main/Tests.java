package main;

import java.io.IOException;
import java.net.URI;

public class Tests {
    final static String URI_USERS = "https://jsonplaceholder.typicode.com/users";
    final static User DEFAULT_USER = new User(1, "Tykhon Vilkhoviy", "ks1on", "ttt@gmail.com", "0123456789");

    public static void main(String[] args) throws IOException, InterruptedException {

        //Task 1

        //Post
//        User createdUser = API.postUser(URI.create(URI_USERS), DEFAULT_USER);
//        System.out.println(createdUser);
//
//        //Put
//        User userToRepresent = new User(12, "Tykhon Vilkhoviy", "ks1on", "pPp@gmail.com", "0123456789");
//        User updatedUser = API.putUser(URI.create(URI_USERS + "/" + "9"), userToRepresent);
//        System.out.println(updatedUser);

        //GET
        System.out.println(API.getUserById(8));
        System.out.println(API.getUsers());
        System.out.println(API.getUserByUsername("Bret"));


        //Delete
//        String statusCode = String.valueOf(API.deleteUserByIdId(9));
//        if (statusCode.matches("^2\\d*")) {
//            System.out.println("Deleted successfully using id");
//        } else
//            System.out.println(statusCode);
//
//        String statusCode1 = String.valueOf(API.deleteUserByUsername("Delphine"));
//        if (statusCode1.matches("^2\\d*")) {
//            System.out.println("Deleted successfully using username");
//        } else
//            System.out.println(statusCode1);
//
//        //Task 2
//
//        API.getCommentsByUserId(3);
//
//        //Task 3
//
//        System.out.println(API.getTodosOfUserById(2));
    }
}
