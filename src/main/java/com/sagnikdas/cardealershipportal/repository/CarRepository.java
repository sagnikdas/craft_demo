package com.sagnikdas.cardealershipportal.repository;

import com.sagnikdas.cardealershipportal.entity.Car;
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

    @Query(value = "select * from cars_table where model_name = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarByModelName(String modelName);

    @Query(value = "select * from cars_table where mfg_name = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarByManufacturerName(String manufacturerName);

    @Query(value = "select * from cars_table where country_origin = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarByCountryOfOrigin(String countryOfOrigin);

    @Query(value = "select * from cars_table where engine_type = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarByEngineType(String engine_type);

    @Query(value = "select * from cars_table where seating_capacity = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarBySeatingCapacity(String seating_capacity);

    @Query(value = "select * from cars_table where car_colour = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarByCarColour(String car_colour);

    @Query(value = "select * from cars_table where price = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarByExShowRoomPrice(Double exShowRoomPrice);

    @Query(value = "select * from cars_table where date_of_release = ?1 order by date_of_release DESC limit 10", nativeQuery = true)
    List<Car> getCarByDateOfRelease(Date dateOfRelease);


}
