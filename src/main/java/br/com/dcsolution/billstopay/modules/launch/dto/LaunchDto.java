package br.com.dcsolution.billstopay.modules.launch.dto;

import br.com.dcsolution.billstopay.modules.launch.enums.LaunchStatusEnum;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LaunchDto implements Serializable {

    @JsonProperty(value = "id")
    private Integer id = 0;

    @JsonProperty("description")
    @NotBlank
    private String description;

    @JsonProperty("startPosition")
    @NotNull
    private Integer startPosition;

    @JsonProperty("endPosition")
    @NotNull
    private Integer endPosition;

    @JsonProperty("paymentValue")
    @NotNull
    private BigDecimal paymentValue;

    @JsonProperty("type")
    @NotNull
    private LaunchTypeEnum type;

    @JsonProperty("status")
    @NotNull
    private LaunchStatusEnum status;

    @JsonProperty("paymentDate")
    @NotNull
    private LocalDate paymentDate;

    @JsonProperty("categoryId")
    @NotNull
    @Digits(integer = 9, fraction = 0)
    private Integer categoryId;

    @JsonProperty("tags")
    @NotNull
    private List<Integer> tags = new ArrayList<>();

}
