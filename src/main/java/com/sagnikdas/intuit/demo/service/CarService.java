package com.sagnikdas.intuit.demo.service;

import com.sagnikdas.intuit.demo.entity.Car;
import com.sagnikdas.intuit.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public void addCar(Car car){
        carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCarByVin(String vin){
        return carRepository.getCarByVin(vin);
    }

    public List<Car> getCarByModelName(String modelName){
        return carRepository.getCarByModelName(modelName);
    }

    public List<Car> getCarByManufacturerName(String manufacturerName){
        return carRepository.getCarByManufacturerName(manufacturerName);
    }

    public List<Car> getCarByCountryOfOrigin(String countryOfOrigin){
        return carRepository.getCarByCountryOfOrigin(countryOfOrigin);
    }


    public List<Car> getCarByEngineType(String engineType){
        return carRepository.getCarByEngineType(engineType);
    }

    public List<Car> getCarBySeatingCapacity(String seatingCapacity){
        return carRepository.getCarBySeatingCapacity(seatingCapacity);
    }

    public List<Car> getCarByCarColour(String carColour){
        return carRepository.getCarByCarColour(carColour);
    }


    public List<Car> getCarByExShowRoomPrice(Double exShowRoomPrice){
        return carRepository.getCarByExShowRoomPrice(exShowRoomPrice);
    }

    public List<Car> getCarByDateOfRelease(Date dateOfRelease){
        return carRepository.getCarByDateOfRelease(dateOfRelease);
    }

}
