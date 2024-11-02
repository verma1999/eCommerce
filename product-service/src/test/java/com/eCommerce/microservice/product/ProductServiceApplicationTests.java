package com.eCommerce.microservice.product;

import com.eCommerce.microservice.product.dto.ProductRequest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import java.math.BigDecimal;

@Import(com.eCommerce.microservice.product.TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.7");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.defaultParser = Parser.JSON;
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"name": "Macbook Air M3",
				    "description": "An apple macbook air laptop with 16 gigs of ram",
				    "price":134000
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.log().all()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Macbook Air M3"))
				.body("description", Matchers.equalTo("An apple macbook air laptop with 16 gigs of ram"))
				.body("price", Matchers.is(134000));
	}

//	private ProductRequest getProductRequest() {
//		return new ProductRequest("Macbook Air M3", "An apple macbook air laptop with 16 gigs of ram",
//				BigDecimal.valueOf(134000));
//	}
}
