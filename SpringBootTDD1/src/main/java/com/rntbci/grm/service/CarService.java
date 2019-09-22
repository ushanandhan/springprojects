package com.rntbci.grm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rntbci.grm.exception.CarNotFoundException;
import com.rntbci.grm.model.Car;
import com.rntbci.grm.repository.CarRepository;

@Service("carService")
public class CarService {

	@Autowired
	CarRepository carRepository;

	public Car getCarDetails(String name) throws Exception{
		Car car = null;
		Optional<Car> optionalCar =  carRepository.findByName(name);
		if(optionalCar.isPresent()) {
			car = optionalCar.get();
		}else {
			throw new CarNotFoundException();
		}
		return car;
	}

	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	public Car saveOrUpdate(Car car) {
		return carRepository.save(car);
	}
	
	
}
