package com.sagnikdas.intuit.demo.repository;

import com.sagnikdas.intuit.demo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "select * from cars_table where vin_id = ?1", nativeQuery = true)
    Optional<Car> getCarByVin(String vin);

    @Query(value = "select * from cars_table where model_name = ?1 order by model_name DESC limit 10", nativeQuery = true)
    List<Car> getCarByModelName(String modelName);

    @Query(value = "select * from cars_table where manufacturerName = ?1 order by manufacturerName DESC limit 10", nativeQuery = true)
    List<Car> getCarByManufacturerName(String manufacturerName);

    @Query(value = "select * from cars_table where countryOfOrigin = ?1 order by countryOfOrigin DESC limit 10", nativeQuery = true)
    List<Car> getCarByCountryOfOrigin(String countryOfOrigin);

    @Query(value = "select * from cars_table where engine_type = ?1 order by engine_type DESC limit 10", nativeQuery = true)
    List<Car> getCarByEngineType(String engine_type);

    @Query(value = "select * from cars_table where seating_capacity = ?1 order by seating_capacity DESC limit 10", nativeQuery = true)
    List<Car> getCarBySeatingCapacity(String seating_capacity);

    @Query(value = "select * from cars_table where car_colour = ?1 order by car_colour DESC limit 10", nativeQuery = true)
    List<Car> getCarByCarColour(String car_colour);

    @Query(value = "select * from cars_table where exShowRoomPrice = ?1 order by exShowRoomPrice DESC limit 10", nativeQuery = true)
    List<Car> getCarByExShowRoomPrice(Double exShowRoomPrice);

    @Query(value = "select * from cars_table where dateOfRelease = ?1 order by dateOfRelease DESC limit 10", nativeQuery = true)
    List<Car> getCarByDateOfRelease(Date dateOfRelease);


}
