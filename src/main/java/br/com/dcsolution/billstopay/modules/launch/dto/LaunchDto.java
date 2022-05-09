package br.com.dcsolution.billstopay.modules.launch.dto;

import br.com.dcsolution.billstopay.modules.launch.enums.LaunchStatusEnum;
import br.com.dcsolution.billstopay.modules.launch.enums.LaunchTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaunchDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("description")
    @NotBlank
    private String description;

    @JsonProperty("start_position")
    @NotNull
    private Integer positionStart;

    @JsonProperty("end_position")
    @NotNull
    private Integer positionEnd;

    @JsonProperty("type")
    @NotBlank
    private LaunchTypeEnum type;

    @JsonProperty("status")
    @NotBlank
    private LaunchStatusEnum status;

    @JsonProperty("type")
    @NotBlank
    private LocalDate date;

    @JsonProperty("category_id")
    @NotNull
    @Digits(integer = 9, fraction = 0)
    private Integer categoryId;

    @JsonProperty("tags")
    @NotNull
    private List<Integer> tags = new ArrayList<>();

}
