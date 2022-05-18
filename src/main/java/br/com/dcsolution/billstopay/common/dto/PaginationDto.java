package br.com.dcsolution.billstopay.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"content", "totalItems", "totalPages", "currentPage", "size"})
public class PaginationDto<T> {

    @JsonProperty("totalItems")
    private Long totalItems = 0L;
    @JsonProperty("totalPages")
    private Integer totalPages = 0;
    @JsonProperty("currentPage")
    private Integer currentPage = 0;
    @JsonProperty("size")
    private Integer size = 0;
    @JsonProperty("content")
    private List<T> content = new ArrayList<>();
}