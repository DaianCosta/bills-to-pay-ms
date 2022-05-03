package br.com.dcsolution.billstopay.modules.launch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaunchDto implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("description")
    @NotBlank
    private String description;

}
