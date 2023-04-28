package com.javarush.maximov.catalog.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    static {
        postgres.start();
    }

    @BeforeAll
    void uploadData() {
        String url = "http://localhost:" + port + "/upload";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void deleteAllData() {
        String url = "http://localhost:" + port + "/delete";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);

        String listComputersUrl = "http://localhost:" + port + "/computer";
        ResponseEntity<String> listComputersResponse = restTemplate.getForEntity(listComputersUrl, String.class);
        assertThat(listComputersResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listComputersResponse.getBody()).doesNotContain("PC #1");

        uploadData();
        listComputersResponse = restTemplate.getForEntity(listComputersUrl, String.class);
        assertThat(listComputersResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listComputersResponse.getBody()).contains("PC #1");
    }

    @Test
    void listComputersTest() {
        String url = "http://localhost:" + port + "/computer";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("PC #1");
    }

    @Test
    void searchComputersTest() {
        String url = "http://localhost:" + port + "/computer/search?name=PC";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("PC #1");
    }
}