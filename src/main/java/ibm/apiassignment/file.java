package ibm.apiassignment;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class file {

	@Test(enabled=true, dependsOnMethods={"putshobhit"})
	public void getshobhit()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		given().
			get("user/shobhit").
		then().
		statusCode(200).log().all();
	}
	
	@SuppressWarnings("unchecked")
	@Test(enabled=true,dataProvider="postData")
	public void postshobhit(String data)
	{
		RestAssured.baseURI="https://localhost:3000";
		given().
			contentType(ContentType.JSON)
		.body(data)
			.when()
			.post("user")
			.then()
			.statusCode(200).log().all();
	}
	
	@SuppressWarnings("unchecked")
	@DataProvider(name="postData")
	public Object[][] providerPost(){
		JSONObject j1 = new JSONObject();
		j1.put("username","shobhit1");
		j1.put("password", "123abc");
		j1.put("name", "shobhit");
		j1.put("phone", "9876543210");
		
		Object[][] postData = {
					{j1.toString()}
				};
				return postData;
				}

	@Test(enabled=true,dataProvider="putData",dependsOnMethods={"postshobhit"})
	public void putshobhit(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.contentType(ContentType.JSON)
        .body(data)
		.when()
		.put("user/shobhit1")
		.then()
		.statusCode(200).log().all();
		
	}
	
	@SuppressWarnings("unchecked")
	@DataProvider(name="putData")
    public Object[][] providerPUT(){
		Object j1 = new Object[2][2];
        j1.put("username", "shobhit1");
        j1.put("password", "123abc");
        j1.put("name","shobhit");
        j1.put("phone","9876543210");
        
        
        Object[][] putData = {
            {j1.toString()}
        };        
        return putData;
	}  
	@Test(enabled=true,dataProvider="deleteData",dependsOnMethods={"putshobhit"})
	public void deleteshobhit(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.delete("user/"+data)
		.then()
		.statusCode(200).log().all();
		
	}
	@DataProvider(name="deleteData")
    public Object[][] providerDELETE(){
        Object[][] deleteData = {{"shobhit1"}};
        return deleteData;
    }
	@Test(enabled=true,dataProvider="loginData",dependsOnMethods={"postshobhit"})
	public void loginshobhit(String username, String password)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.queryParam("username", username)
        .queryParam("password", password)
		.get("user/login")
		.then()
		.statusCode(200).log().all();
	}
    @DataProvider(name="loginData")
    public Object[][] providerLogin(){
        Object[][] loginData = {{"shobhit1", "123abc"}};
        return loginData;
    }
}
	
	
