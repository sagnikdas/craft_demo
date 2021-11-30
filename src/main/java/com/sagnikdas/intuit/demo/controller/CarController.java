package com.sagnikdas.intuit.demo.controller;


import com.sagnikdas.intuit.demo.entity.Car;
import com.sagnikdas.intuit.demo.entity.enumerations.CarVectorEnum;
import com.sagnikdas.intuit.demo.response.CarsResponse;
import com.sagnikdas.intuit.demo.response.CustomComparisonResponse;
import com.sagnikdas.intuit.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/car")
    public void addCar(@RequestBody Car car){
        carService.addCar(car);
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/{vinid}")
    public Car getCarByVin(@PathVariable("vinid") String vin){
        return carService.getCarByVin(vin);
    }

    @GetMapping("/cars/{vinid}/similarCars") //Make constants TODO
    public CarsResponse getSimilarCars(@PathVariable("vinid") String vin, @RequestParam("search_type") String searchType) throws Exception {

        CarVectorEnum vector = CarVectorEnum.valueOf(searchType);
        Car selectedCar = getCarByVin(vin); //TODO exception handling , in case the selected car is null

        switch (vector) {
            case MODEL_NAME:
                return new CarsResponse(selectedCar,carService.getCarByModelName(selectedCar.getModelName()));
            case MFG_NAME:
                return new CarsResponse(selectedCar,carService.getCarByManufacturerName(selectedCar.getManufacturerName()));
            case COUNTRY_ORIGIN:
                return new CarsResponse(selectedCar,carService.getCarByCountryOfOrigin(selectedCar.getCountryOfOrigin()));
            case ENGINE_TYPE:
                return new CarsResponse(selectedCar,carService.getCarByEngineType(selectedCar.getEngineType().name()));
            case SEATING_CAPACITY:
                return new CarsResponse(selectedCar,carService.getCarBySeatingCapacity(selectedCar.getSeatingCapacity().name()));
            case COLOUR:
                return new CarsResponse(selectedCar,carService.getCarByCarColour(selectedCar.getCarColour().name()));
            case PRICE:
                return new CarsResponse(selectedCar,carService.getCarByExShowRoomPrice(Double.parseDouble(selectedCar.getExShowRoomPrice().toString())));
            case RELEASE_DATE:
                return new CarsResponse(selectedCar,carService.getCarByDateOfRelease(selectedCar.getDateOfRelease()));
            default:
                throw new Exception("Invalid search type found");
        }

    }

    @GetMapping("/cars/compare")
    public CustomComparisonResponse getComparison(@RequestParam List<String> vinIds, @RequestParam boolean doShowDifference){

        Map<CarVectorEnum, ArrayList<String>> featureMetaDataMap = new HashMap<>();

        Map<String, ArrayList<String>> diffFeatureMetaDataMap = new HashMap<>();

        List<Car> cars = vinIds.stream().map(this::getCarByVin).collect(Collectors.toList());


        for(Car car: cars){

            //MODEL_NAME
            if(!featureMetaDataMap.containsKey(CarVectorEnum.MODEL_NAME)){
                featureMetaDataMap.put(CarVectorEnum.MODEL_NAME, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.MODEL_NAME).add(car.getModelName());

            //MFG_NAME
            if(!featureMetaDataMap.containsKey(CarVectorEnum.MFG_NAME)){
                featureMetaDataMap.put(CarVectorEnum.MFG_NAME, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.MFG_NAME).add(car.getManufacturerName());

            //COUNTRY_ORIGIN
            if(!featureMetaDataMap.containsKey(CarVectorEnum.COUNTRY_ORIGIN)){
                featureMetaDataMap.put(CarVectorEnum.COUNTRY_ORIGIN, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.COUNTRY_ORIGIN).add(car.getCountryOfOrigin());

            //ENGINE_TYPE
            if(!featureMetaDataMap.containsKey(CarVectorEnum.ENGINE_TYPE)){
                featureMetaDataMap.put(CarVectorEnum.ENGINE_TYPE, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.ENGINE_TYPE).add(car.getEngineType().name());

            //SEATING_CAPACITY
            if(!featureMetaDataMap.containsKey(CarVectorEnum.SEATING_CAPACITY)){
                featureMetaDataMap.put(CarVectorEnum.SEATING_CAPACITY, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.SEATING_CAPACITY).add(car.getSeatingCapacity().name());

            //COLOUR
            if(!featureMetaDataMap.containsKey(CarVectorEnum.COLOUR)){
                featureMetaDataMap.put(CarVectorEnum.COLOUR, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.COLOUR).add(car.getCarColour().name());

            //PRICE
            if(!featureMetaDataMap.containsKey(CarVectorEnum.PRICE)){
                featureMetaDataMap.put(CarVectorEnum.PRICE, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.PRICE).add((car.getExShowRoomPrice()!=null)?car.getExShowRoomPrice().toString(): "");

        }

        if(doShowDifference){
            for(Map.Entry<CarVectorEnum, ArrayList<String>> entry: featureMetaDataMap.entrySet()){
                //Populating differing features in a map
                if(!containsAllSimilarElements(entry.getValue())){
                    diffFeatureMetaDataMap.put(entry.getKey().name().replace("_","").toLowerCase(), entry.getValue());
                }
            }
        }

        return new CustomComparisonResponse(cars,diffFeatureMetaDataMap);
    }


    private boolean containsAllSimilarElements(ArrayList<String> list) {
        return list.stream()
                .distinct()
                .count() <= 1;
    }


}
