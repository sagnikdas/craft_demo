package com.sagnikdas.intuit.demo.controller;


import com.sagnikdas.intuit.demo.entity.Car;
import com.sagnikdas.intuit.demo.entity.enumerations.CarVectorEnum;
import com.sagnikdas.intuit.demo.error.EmptyVinIdsException;
import com.sagnikdas.intuit.demo.error.VinNotFoundException;
import com.sagnikdas.intuit.demo.response.CarsResponse;
import com.sagnikdas.intuit.demo.response.CustomComparisonResponse;
import com.sagnikdas.intuit.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sagnikdas.intuit.demo.utils.CarConstants.*;

@RestController
public class CarController {

    @Autowired
    CarService carService;

    //Adding a car to the database
    @PostMapping(CAR)
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    //Getting all the cars in the repository
    @GetMapping(CARS)
    public List<Car> getCars() {
        return carService.getAllCars();

    }

    //Get a Car by its VIN id
    @GetMapping(CARS_VINID)
    public Car getCarByVin(@PathVariable("vinid") String vin) throws VinNotFoundException {
        return carService.getCarByVin(vin);
    }

    //Get similar cars by feature
    @GetMapping(CARS_VINID_SIMILAR_CARS)
    public CarsResponse getSimilarCars(@PathVariable("vinid") String vin,
                                       @RequestParam("search_type") String searchType) {
        return carService.getSimilarCarsBySearchType(vin, searchType);
    }


    //Compare multiple cars
    @GetMapping(CARS_COMPARE)
    public CustomComparisonResponse getComparison(@RequestParam List<String> vinIds,
                                                  @RequestParam boolean doShowDifference) throws VinNotFoundException, EmptyVinIdsException {

        Map<CarVectorEnum, ArrayList<String>> featureMetaDataMap = new HashMap<>();

        Map<String, ArrayList<String>> diffFeatureMetaDataMap = new HashMap<>();

        if (vinIds.isEmpty()) {
            throw new EmptyVinIdsException("Invalid VinIds list or empty");
        }


        return carService.getCustomComparisonResponse(vinIds, doShowDifference, featureMetaDataMap, diffFeatureMetaDataMap);
    }

}
