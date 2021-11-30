package com.sagnikdas.intuit.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sagnikdas.intuit.demo.entity.enumerations.CarVectorEnum;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FilteredCarResponse {


    @JsonProperty("vin")
    String vin;

    @JsonProperty("diffFeatureMap")
    Map<CarVectorEnum, List<String>> diffFeatureList;




}
