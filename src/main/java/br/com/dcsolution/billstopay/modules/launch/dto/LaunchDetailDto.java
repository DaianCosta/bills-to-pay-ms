package br.com.dcsolution.billstopay.modules.launch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LaunchDetailDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("startPosition")
    private Integer startPosition;

    @JsonProperty("endPosition")
    private Integer endPosition;

    @JsonProperty("paymentValue")
    private BigDecimal paymentValue;

    @JsonProperty("valuePaymentFormat")
    private String paymentValueFormat;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("paymentDateFormat")
    private String paymentDateFormat;

    @JsonProperty("nameCategory")
    private String nameCategory;

    @JsonProperty("tags")
    private List<String> tags = new ArrayList<>();

}
