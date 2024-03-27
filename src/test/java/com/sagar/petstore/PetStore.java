package com.sagar.petstore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetStore {
    @Test
    public void addNewPet(){
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1001,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 2001,\n" +
                        "    \"name\": \"Dog\"\n" +
                        "  },\n" +
                        "  \"name\": \"dilli\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void uploadAnImage(){
        File file=new File("C:\\Users\\sagar\\Desktop\\SELENIUM TESTING\\Rest Assured\\" +
                "Rest Assured\\src\\test\\java\\com\\sagar\\petstore\\dog.webp");
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","multipart/form-data")
                .multiPart("file",file)
                .multiPart("additionalMetadata","dog")
                .pathParam("petid",1001)
                .when()
                .post("https://petstore.swagger.io/v2/pet/{petid}/uploadImage");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updateAnExistingPet(){
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1000,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 5000,\n" +
                        "    \"name\": \"cat\"\n" +
                        "  },\n" +
                        "  \"name\": \"ben\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void findPetsByStatus(){
        Response response=given()
                .header("accept","application/json")
//                .queryParam("status","available")
                .queryParam("status","pending")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void findPetsById(){
        Response response=given()
                .header("accept","application/json")
                .pathParam("petId",1001)
                .when()
                .get("https://petstore.swagger.io/v2/pet/{petId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updatePetsInStoreFormData(){
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/x-www-form-urlencoded")
                .queryParam("name","rolex")
                .queryParam("status","available")
                .pathParam("petId",1001)
                .when()
                .post("https://petstore.swagger.io/v2/pet/{petId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deletePet(){
        Response response=given()
                .header("accept","application/json")
                .pathParam("petId",1001)
                .when()
                .delete("https://petstore.swagger.io/v2/pet/{petId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /*================================  STORE STARTS ==================================*/

    @Test
    public void placeOrderForPet(){
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"petId\": 1001,\n" +
                        "  \"quantity\": 2,\n" +
                        "  \"shipDate\": \"2024-03-25T17:59:49.950Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void findPurchaseOrderById(){
        Response response=given()
                .header("accept","application/json")
                .pathParam("orderId",1)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/{orderId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deletePurchaseOrderById(){
        Response response=given()
                .header("accept","application/json")
                .pathParam("orderId",1)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/{orderId}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void returnsPetInventoriesByStatus(){
        Response response=given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* ===============================User starts ================================*/

    @Test
    public void createWithArray(){
        Response respons= given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"username\": \"sagar\",\n" +
                        "    \"firstName\": \"vidhya\",\n" +
                        "    \"lastName\": \"sagar\",\n" +
                        "    \"email\": \"sagar@gmail.com\",\n" +
                        "    \"password\": \"sagar123\",\n" +
                        "    \"phone\": \"9876543212\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  },\n" +
                        "{\n" +
                        "    \"id\": 2,\n" +
                        "    \"username\": \"sagar\",\n" +
                        "    \"firstName\": \"vidhya\",\n" +
                        "    \"lastName\": \"sagar\",\n" +
                        "    \"email\": \"sagar@gmail.com\",\n" +
                        "    \"password\": \"sagar123\",\n" +
                        "    \"phone\": \"9876543212\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithArray");
        respons.prettyPrint();
        respons.then().statusCode(200);
    }

    @Test
    public void getUserByUserName(){
        Response response=given()
                .header("accept", "application/json")
                .pathParam("username","sagar")
                .when()
                .get("https://petstore.swagger.io/v2/user/{username}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updatedUser(){
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"username\": \"sagar\",\n" +
                        "  \"firstName\": \"vidhyasagar\",\n" +
                        "  \"lastName\": \"vp\",\n" +
                        "  \"email\": \"vp@gmail.com\",\n" +
                        "  \"password\": \"vp123\",\n" +
                        "  \"phone\": \"9876543212\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .pathParam("username","sagar")
                .when()
                .put("https://petstore.swagger.io/v2/user/{username}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deleteUser(){
        Response response=given()
                .header("accept","application/json")
                .pathParam("username","sagar")
                .when()
                .delete("https://petstore.swagger.io/v2/user/{username}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void login(){
        Response response=given()
                .header("accept","application/json")
                .queryParam("username","sagar")
                .queryParam("password","sagar123")
                .when()
                .get("https://petstore.swagger.io/v2/user/login");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void logout(){
        Response response=given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/logout");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void createUser(){
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 5,\n" +
                        "  \"username\": \"sagar\",\n" +
                        "  \"firstName\": \"sagar\",\n" +
                        "  \"lastName\": \"vp\",\n" +
                        "  \"email\": \"sagar@gmail.com\",\n" +
                        "  \"password\": \"sagar123\",\n" +
                        "  \"phone\": \"9876543212\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/user");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
