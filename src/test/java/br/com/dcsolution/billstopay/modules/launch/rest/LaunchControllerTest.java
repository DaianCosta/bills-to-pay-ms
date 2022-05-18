package br.com.dcsolution.billstopay.modules.launch.rest;

import br.com.dcsolution.billstopay.modules.category.service.CategoryService;
import br.com.dcsolution.billstopay.modules.category.stub.CategoryServiceStub;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDetailDto;
import br.com.dcsolution.billstopay.modules.launch.service.LaunchService;
import br.com.dcsolution.billstopay.modules.launch.stub.LaunchServiceStub;
import br.com.dcsolution.billstopay.modules.tag.service.TagService;
import br.com.dcsolution.billstopay.modules.tag.stub.TagServiceStub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LaunchControllerTest {

    final HttpHeaders headers = new HttpHeaders();
    private String URL_BASE;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LaunchService launchService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() throws MalformedURLException {
        URL_BASE = new URL("http://localhost:" + port + "/launch").toString();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    void findAll() throws JsonProcessingException {
        loadData();

        final ResponseEntity<String> response = restTemplate
                .getForEntity(URL_BASE + "?page=0&size=10&searchTerm=comida",
                        String.class);

        final JsonNode jsonNode = objectMapper.readTree(response.getBody());
        final Integer totalElements = jsonNode.get("totalItems").asInt();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, totalElements);
    }

    @Test
    @Order(2)
    void findById() {

        final ResponseEntity<LaunchDetailDto> response = restTemplate
                .getForEntity(URL_BASE + "/1", LaunchDetailDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    @Order(3)
    void findByIdBadRequest() {

        final ResponseEntity<LaunchDetailDto> response = restTemplate.getForEntity(URL_BASE + "/10",
                LaunchDetailDto.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(4)
    void post() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(LaunchServiceStub.generateDto(null, "compras-bebidas")),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        final LaunchDetailDto created = launchService.findById(2);

        Assertions.assertEquals("compras-bebidas", created.getDescription());
    }

    @Test
    @Order(5)
    void postBadRequest() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(LaunchServiceStub.generateDto(null, null)),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    @Order(5)
    void postException() throws JsonProcessingException {

        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString("{" +
                        "\"id\":2," +
                        "\"description\":null," +
                        "\"categoryId\":1," +
                        "\"startPosition\":1," +
                        "\"endPosition\":1," +
                        "  \"paymentValue\":10.50," +
                        "\"type\":\"IN\"," +
                        "\"status\":\"PAID\"," +
                        "\"paymentDate\":\"2022-01-01\"," +
                        "\"tags\":[1,2]" +
                        "}"),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @Order(6)
    void put() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(LaunchServiceStub.generateDto(1, "compras - update")),
                headers);

        final ResponseEntity<Void> response = restTemplate.exchange(URL_BASE,
                HttpMethod.PUT, request, Void.class);

        final LaunchDetailDto launch = launchService.findById(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("compras - update", launch.getDescription());
    }

    @Test
    @Order(7)
    void putBadRequest() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(LaunchServiceStub.generateDto(10, "vestuario")),
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

    private void loadData() {
        tagService.create(TagServiceStub.generateDtoParameter(1, "contas-papai"));
        categoryService.create(CategoryServiceStub.generateDtoParameter(1, "refeicao"));
        launchService.create(LaunchServiceStub.generateDto(null, LaunchServiceStub.DESCRIPTION));
    }

}