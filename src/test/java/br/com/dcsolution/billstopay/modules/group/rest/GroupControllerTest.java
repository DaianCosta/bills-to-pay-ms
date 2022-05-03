package br.com.dcsolution.billstopay.modules.group.rest;

import br.com.dcsolution.billstopay.modules.group.dto.GroupDto;
import br.com.dcsolution.billstopay.modules.group.service.GroupService;
import br.com.dcsolution.billstopay.modules.group.stub.GroupServiceStub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GroupControllerTest {

    final HttpHeaders headers = new HttpHeaders();
    private String URL_BASE;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GroupService groupService;

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() throws MalformedURLException {

        groupService.create(GroupServiceStub.generateDto());
        URL_BASE = new URL("http://localhost:" + port + "/group").toString();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    void findAll() throws JsonProcessingException {

        final ResponseEntity<String> response = restTemplate.getForEntity(URL_BASE + "?page=0&size=10&searchTerm=free",
                String.class);

        final JsonNode jsonNode = objectMapper.readTree(response.getBody());
        final Integer totalElements = jsonNode.get("totalElements").asInt();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, totalElements);
    }


    @Test
    @Order(2)
    void findById() {

        final ResponseEntity<GroupDto> response = restTemplate.getForEntity(URL_BASE + "/1", GroupDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    @Order(3)
    void findByIdBadRequest() {

        final ResponseEntity<GroupDto> response = restTemplate.getForEntity(URL_BASE + "/10",
                GroupDto.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(4)
    void post() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(GroupServiceStub.generateDtoParameter(null, "banrisul")),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        final Page<GroupDto> groups = groupService.findAll(0, 10, "");
        final String groupCreated = groups.getContent().get(1).getName();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, groups.getTotalElements());
        Assertions.assertEquals("banrisul", groupCreated);
    }

    @Test
    @Order(5)
    void postBadRequest() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(GroupServiceStub.generateDtoParameter(null, null)),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(5)
    void postException() throws JsonProcessingException {

        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString("{\"id:\"99999999999999999,\"name:\"free\"}"),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @Order(6)
    void put() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(GroupServiceStub.generateDtoParameter(1, "santander")),
                headers);

        final ResponseEntity<Void> response = restTemplate.exchange(URL_BASE,
                HttpMethod.PUT, request, Void.class);

        final GroupDto group = groupService.findById(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("santander", group.getName());
    }

    @Test
    @Order(7)
    void putBadRequest() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(GroupServiceStub.generateDtoParameter(10, "santander")),
                headers);

        final ResponseEntity<Void> response = restTemplate.exchange(URL_BASE,
                HttpMethod.PUT, request, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(8)
    void delete() {
        final ResponseEntity<Void> response = restTemplate.exchange(URL_BASE + "/1",
                HttpMethod.DELETE, null, Void.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(9)
    void deleteBadRequest() {
        final ResponseEntity<Void> response = restTemplate.exchange(URL_BASE + "/10",
                HttpMethod.DELETE, null, Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}