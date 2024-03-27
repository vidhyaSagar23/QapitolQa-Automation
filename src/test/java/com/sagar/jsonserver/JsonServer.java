package com.sagar.jsonserver;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonServer {
    @Test
    public void createPosts(){
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body("{ \"id\": \"10\", \"title\": \"DSA\", \"views\": 250 }")
                .post("http://localhost:3000/posts");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void readAllPosts(){
        Response response=given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/posts");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void readPost(){
        Response response=given()
                .header("Content-Type", "application/json")
                .pathParam("userid",10)
                .when()
                .get("http://localhost:3000/posts/{userid}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updatePost(){
        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("userid",10)
                .body("{\n" +
                        "    \"id\":10,\n" +
                        "    \"title\":\"OSI MODEL\",\n" +
                        "    \"views\": 350\n" +
                        "}")
                .when()
                .put("http://localhost:3000/posts/{userid}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deletePost(){
        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("userId",10)
                .body("{\n" +
                        "    \"id\":3,\n" +
                        "    \"title\":\"computer networking\",\n" +
                        "    \"views\": 350\n" +
                        "}")
                .when()
                .delete("http://localhost:3000/posts/{userId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* =================================== COMMENTS START================================================*/

    @Test
    public void createComments(){
        Response response=given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "        \"id\": \"3\",\n" +
                        "        \"text\": \"fabulous\",\n" +
                        "        \"postId\": \"1\"\n" +
                        "}")
                .when()
                .post("http://localhost:3000/comments");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void readAllComments(){
        Response response=given()
                .header("Content-Type","application/json")
                .when()
                .get("http://localhost:3000/comments");
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    public void readComments(){
        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("commentId",3)
                .when()
                .get("http://localhost:3000/comments/{commentId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updateComments(){
        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("commentId",3)
                .body("{\n" +
                        "    \"id\": \"3\",\n" +
                        "    \"text\": \"wowww\",\n" +
                        "    \"postId\": \"1\"\n" +
                        "}")
                .when()
                .put("http://localhost:3000/comments/{commentId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deleteComments(){
        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("commentId",3)
                .when()
                .delete("http://localhost:3000/comments/{commentId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getProfile(){
        Response response=given()
                .header("Content-Type","application/json")
                .when()
                .get("http://localhost:3000/profile");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
