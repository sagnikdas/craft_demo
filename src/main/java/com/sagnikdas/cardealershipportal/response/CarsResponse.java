package com.sagnikdas.cardealershipportal.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sagnikdas.cardealershipportal.entity.Car;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CarsResponse {

    @JsonProperty("selectedCar")
    Car car;

    @JsonProperty("similarCars")
    List<Car> similarCars;

}
