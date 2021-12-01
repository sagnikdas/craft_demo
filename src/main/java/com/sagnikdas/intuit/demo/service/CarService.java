package com.sagnikdas.intuit.demo.service;

import com.sagnikdas.intuit.demo.entity.Car;
import com.sagnikdas.intuit.demo.entity.enumerations.CarVectorEnum;
import com.sagnikdas.intuit.demo.error.VinNotFoundException;
import com.sagnikdas.intuit.demo.repository.CarRepository;
import com.sagnikdas.intuit.demo.response.CarsResponse;
import com.sagnikdas.intuit.demo.response.CustomComparisonResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarByVin(String vin) throws VinNotFoundException {
        Optional<Car> car = carRepository.getCarByVin(vin);
        if (!car.isPresent()) {
            throw new VinNotFoundException("Vin Not Found");
        }
        return car.get();
    }

    public List<Car> getCarByModelName(String modelName) {
        return carRepository.getCarByModelName(modelName);
    }

    @SneakyThrows
    public CarsResponse getSimilarCarsBySearchType(String vin, String searchType) {
        Car selectedCar = getCarByVin(vin);
        CarVectorEnum type = CarVectorEnum.fromString(searchType);

        switch (type) {
            case MODEL_NAME:
                return new CarsResponse(selectedCar, getCarByModelName(selectedCar.getModelName()));
            case MFG_NAME:
                return new CarsResponse(selectedCar, getCarByManufacturerName(selectedCar.getManufacturerName()));
            case COUNTRY_ORIGIN:
                return new CarsResponse(selectedCar, getCarByCountryOfOrigin(selectedCar.getCountryOfOrigin()));
            case ENGINE_TYPE:
                return new CarsResponse(selectedCar, getCarByEngineType(selectedCar.getEngineType().name()));
            case SEATING_CAPACITY:
                return new CarsResponse(selectedCar, getCarBySeatingCapacity(selectedCar.getSeatingCapacity().name()));
            case COLOUR:
                return new CarsResponse(selectedCar, getCarByCarColour(selectedCar.getCarColour().name()));
            case PRICE:
                return new CarsResponse(selectedCar, getCarByExShowRoomPrice(Double.parseDouble(selectedCar.getExShowRoomPrice().toString())));
            case RELEASE_DATE:
                return new CarsResponse(selectedCar, getCarByDateOfRelease(selectedCar.getDateOfRelease()));
        }

        return CarsResponse.builder().build();
    }

    public CustomComparisonResponse getCustomComparisonResponse(List<String> vinIds,
                                                                boolean doShowDifference,
                                                                Map<CarVectorEnum, ArrayList<String>> featureMetaDataMap, Map<String, ArrayList<String>> diffFeatureMetaDataMap) throws VinNotFoundException {
        List<Car> cars = new ArrayList<>();
        for (String vinId : vinIds) {
            Car carByVin = getCarByVin(vinId);
            cars.add(carByVin);
        }

        for (Car car : cars) {

            //MODEL_NAME
            if (!featureMetaDataMap.containsKey(CarVectorEnum.MODEL_NAME)) {
                featureMetaDataMap.put(CarVectorEnum.MODEL_NAME, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.MODEL_NAME).add(car.getModelName());

            //MFG_NAME
            if (!featureMetaDataMap.containsKey(CarVectorEnum.MFG_NAME)) {
                featureMetaDataMap.put(CarVectorEnum.MFG_NAME, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.MFG_NAME).add(car.getManufacturerName());

            //COUNTRY_ORIGIN
            if (!featureMetaDataMap.containsKey(CarVectorEnum.COUNTRY_ORIGIN)) {
                featureMetaDataMap.put(CarVectorEnum.COUNTRY_ORIGIN, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.COUNTRY_ORIGIN).add(car.getCountryOfOrigin());

            //ENGINE_TYPE
            if (!featureMetaDataMap.containsKey(CarVectorEnum.ENGINE_TYPE)) {
                featureMetaDataMap.put(CarVectorEnum.ENGINE_TYPE, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.ENGINE_TYPE).add(car.getEngineType().name());

            //SEATING_CAPACITY
            if (!featureMetaDataMap.containsKey(CarVectorEnum.SEATING_CAPACITY)) {
                featureMetaDataMap.put(CarVectorEnum.SEATING_CAPACITY, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.SEATING_CAPACITY).add(car.getSeatingCapacity().name());

            //COLOUR
            if (!featureMetaDataMap.containsKey(CarVectorEnum.COLOUR)) {
                featureMetaDataMap.put(CarVectorEnum.COLOUR, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.COLOUR).add(car.getCarColour().name());

            //PRICE
            if (!featureMetaDataMap.containsKey(CarVectorEnum.PRICE)) {
                featureMetaDataMap.put(CarVectorEnum.PRICE, new ArrayList<>());
            }
            featureMetaDataMap.get(CarVectorEnum.PRICE).add((car.getExShowRoomPrice() != null) ? car.getExShowRoomPrice().toString() : "");

        }

        if (doShowDifference) {
            for (Map.Entry<CarVectorEnum, ArrayList<String>> entry : featureMetaDataMap.entrySet()) {
                //Populating differing features in a map
                if (!containsAllSimilarElements(entry.getValue())) {
                    diffFeatureMetaDataMap.put(entry.getKey().name().replace("_", "").toLowerCase(), entry.getValue());
                }
            }
        }

        return new CustomComparisonResponse(cars, diffFeatureMetaDataMap);
    }

    private boolean containsAllSimilarElements(ArrayList<String> list) {
        return list.stream()
                .distinct()
                .count() <= 1;
    }

    public List<Car> getCarByManufacturerName(String manufacturerName) {
        return carRepository.getCarByManufacturerName(manufacturerName);
    }

    public List<Car> getCarByCountryOfOrigin(String countryOfOrigin) {
        return carRepository.getCarByCountryOfOrigin(countryOfOrigin);
    }


    public List<Car> getCarByEngineType(String engineType) {
        return carRepository.getCarByEngineType(engineType);
    }

    public List<Car> getCarBySeatingCapacity(String seatingCapacity) {
        return carRepository.getCarBySeatingCapacity(seatingCapacity);
    }

    public List<Car> getCarByCarColour(String carColour) {
        return carRepository.getCarByCarColour(carColour);
    }


    public List<Car> getCarByExShowRoomPrice(Double exShowRoomPrice) {
        return carRepository.getCarByExShowRoomPrice(exShowRoomPrice);
    }

    public List<Car> getCarByDateOfRelease(Date dateOfRelease) {
        return carRepository.getCarByDateOfRelease(dateOfRelease);
    }

}
