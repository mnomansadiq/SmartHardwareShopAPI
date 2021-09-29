package com.smart.hardware.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    public String name;
    public String description;
    public BigDecimal price;

    @Builder(toBuilder = true)
    public ProductDTO(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") BigDecimal price
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
