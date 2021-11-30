package com.sagnikdas.intuit.demo.entity;

import com.sagnikdas.intuit.demo.entity.enumerations.CarColour;
import com.sagnikdas.intuit.demo.entity.enumerations.EngineType;
import com.sagnikdas.intuit.demo.entity.enumerations.SeatingCapacity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "cars_table")
@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "vin_id", nullable = false, unique = true)
    private String vin;

    @Column(name = "model_name", nullable = false)
    String modelName;

    @Column(name = "mfg_name", nullable = false)
    String manufacturerName;

    @Column(name = "country_origin", nullable = false)
    String countryOfOrigin;

    @Column(name = "engine_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    EngineType engineType;

    @Column(name = "seating_capacity", nullable = false)
    @Enumerated(value = EnumType.STRING)
    SeatingCapacity seatingCapacity;

    @Column(name = "car_colour", nullable = false)
    @Enumerated(value = EnumType.STRING)
    CarColour carColour;

    @Column(name = "price", nullable = false)
    Double exShowRoomPrice;

    @CreationTimestamp
    Date dateOfRelease;

}
