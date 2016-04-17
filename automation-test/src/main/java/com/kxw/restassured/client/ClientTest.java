package com.kxw.restassured.client;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.config.JsonConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;

import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;

import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import com.jayway.restassured.response.ValidatableResponse;

public class ClientTest {

	public static void main(String[] args) {

		//工作wiki地址的代码
		// InputStream jsonschema =
		// Thread.currentThread().getContextClassLoader()
		// .getResourceAsStream(".\\schema-file.json");
		//
		// RestAssured.given().relaxedHTTPSValidation().params("", "").when()
		// .get("https://mapi.abc.com/abcs-mobile/router.do").then()
		// .statusCode(200)
		// .body(JsonSchemaValidator.matchesJsonSchema(jsonschema));

		//官方文档的代码
		// 1.
		// Assume that the GET request (to http://localhost:8080/lotto) returns
		// JSON as:
		/*
		 * { "lotto":{ "lottoId":5, "winning-numbers":[2,45,34,23,7,5,3],
		 * "winners":[{ "winnerId":23, "numbers":[2,45,34,23,3,5] },{
		 * "winnerId":54, "numbers":[52,3,12,11,18,22] }] } }
		 */

		// REST assured can then help you to easily make the GET request and
		// verify the response. E.g. if you want to verify that lottoId is equal
		// to 5 you can do like this:

		RestAssured.registerParser("text/plain", Parser.JSON);
		ValidatableResponse response = RestAssured
				.get("/rest-assured_server/test/lotto").then()
				.body("lotto.lottoId", Matchers.equalTo(5));
		System.out.println(response);
		// or perhaps you want to check that the winnerId's are 23 and 54:
		RestAssured.get("/rest-assured_server/test/lotto").then()
				.body("lotto.winners.winnerId", Matchers.hasItems(23, 54));
		
		
		// Example with JsonPath
				String json = RestAssured.get("/rest-assured_server/test/lotto")
						.asString();
				List<String> winnderIds = JsonPath.from(json).get(
						"lotto.winners.winnerId");
				for (int i = 0; i < winnderIds.size(); i++)
					System.out.println(String.valueOf(winnderIds.get(i)));

				// Example with XmlPath
				// String xml = post("/shopping").andReturn().body().asString();
				// Node category = from(xml).get("shopping.category[0]");
		

		// 2.
		// Returning floats and doubles as BigDecimal
		// You can configure Rest Assured and JsonPath to return BigDecimal's
		// instead of float and double for Json Numbers. For example consider
		// the following JSON document:
		/*
		 * {
		 * 
		 * "price":12.12
		 * 
		 * }
		 */
		// By default you validate that price is equal to 12.12 as a float like
		// this:

		RestAssured.get("/rest-assured_server/test/price").then()
				.body("price", Matchers.is(12.12f));
		// but if you like you can configure REST Assured to use a JsonConfig
		// that returns all Json numbers as BigDecimal:

		RestAssured
				.given()
				.config(RestAssuredConfig.newConfig().jsonConfig(
						JsonConfig.jsonConfig().numberReturnType(
								NumberReturnType.BIG_DECIMAL))).when()
				.get("/rest-assured_server/test/price").then()
				.body("price", Matchers.is(new BigDecimal(12.12)));

		System.out.println(12.12f);

		// 3.
		// JSON Schema validation
		// From version 2.1.0 REST Assured has support for Json Schema
		// validation. For example given the following schema located in the
		// classpath as products-schema.json:
		RestAssured
				.get("/rest-assured_server/test/product")
				.then()
				.assertThat()
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("products-schema.json"));
		//只是判断数据格式，并不是全部数据
		
		
		/*
		 * Json Schema Validation without REST Assured
		You can also use the json-schema-validator module without depending on REST Assured. As long as you have a JSON document represented as a String you can do like this:

import org.junit.Test;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
 
public class JsonSchemaValidatorWithoutRestAssuredTest {
 
 
    @Test public void
    validates_schema_in_classpath() {
        // Given
        String json = ... // Greeting response
 
        // Then
        assertThat(json, matchesJsonSchemaInClasspath("greeting-schema.json"));
    }
}
		 */

	}

}
