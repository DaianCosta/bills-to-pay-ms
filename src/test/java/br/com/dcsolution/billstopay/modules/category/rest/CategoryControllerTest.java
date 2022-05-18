package br.com.dcsolution.billstopay.modules.category.rest;

import br.com.dcsolution.billstopay.common.dto.PaginationDto;
import br.com.dcsolution.billstopay.modules.category.dto.CategoryDto;
import br.com.dcsolution.billstopay.modules.category.service.CategoryService;
import br.com.dcsolution.billstopay.modules.category.stub.CategoryServiceStub;
import br.com.dcsolution.billstopay.modules.launch.repository.LaunchRepository;
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
class CategoryControllerTest {

    final HttpHeaders headers = new HttpHeaders();
    private String URL_BASE;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LaunchRepository launchRepository;

    @Autowired
    CategoryService categoryService;

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() throws MalformedURLException {

        categoryService.create(CategoryServiceStub.generateDto());
        URL_BASE = new URL("http://localhost:" + port + "/category").toString();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    void findAll() throws JsonProcessingException {

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

        final ResponseEntity<CategoryDto> response = restTemplate.getForEntity(URL_BASE + "/1", CategoryDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    @Order(3)
    void findByIdBadRequest() {

        final ResponseEntity<CategoryDto> response = restTemplate.getForEntity(URL_BASE + "/10",
                CategoryDto.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(4)
    void post() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(CategoryServiceStub.generateDtoParameter(null, "cartao")),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        final PaginationDto<CategoryDto> categories = categoryService.findAll(0, 10, "");
        final String groupCreated = categories.getContent().get(1).getName();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, categories.getTotalItems());
        Assertions.assertEquals("cartao", groupCreated);
    }

    @Test
    @Order(5)
    void postBadRequest() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(CategoryServiceStub.generateDtoParameter(null, null)),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(5)
    void postException() throws JsonProcessingException {

        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString("{\"id:\"99999999999999999,\"name:\"bebida\"}"),
                headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity(URL_BASE,
                request, Void.class);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @Order(6)
    void put() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(CategoryServiceStub.generateDtoParameter(1, "vestuario")),
                headers);

        final ResponseEntity<Void> response = restTemplate.exchange(URL_BASE,
                HttpMethod.PUT, request, Void.class);

        final CategoryDto group = categoryService.findById(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("vestuario", group.getName());
    }

    @Test
    @Order(7)
    void putBadRequest() throws JsonProcessingException {
        final HttpEntity<String> request = new HttpEntity<>(objectMapper
                .writeValueAsString(CategoryServiceStub.generateDtoParameter(10, "vestuario")),
                headers);

        final ResponseEntity<Void> response = restTemplate.exchange(URL_BASE,
                HttpMethod.PUT, request, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(8)
    void delete() {
        launchRepository.deleteAll();
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