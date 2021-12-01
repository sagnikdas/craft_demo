package com.sagnikdas.intuit.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sagnikdas.intuit.demo.entity.Car;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomComparisonResponse{


    @JsonProperty("cars")
    List<Car> carList;

    @JsonProperty("diffFeatureMap")
    Map<String, ArrayList<String>> diffFeatureMetaDataMap;


}





