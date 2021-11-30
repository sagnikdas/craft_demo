package com.sagnikdas.intuit.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sagnikdas.intuit.demo.entity.Car;
import com.sagnikdas.intuit.demo.entity.enumerations.CarColour;
import com.sagnikdas.intuit.demo.entity.enumerations.EngineType;
import com.sagnikdas.intuit.demo.entity.enumerations.SeatingCapacity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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





