package com.ushan.dev.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ushan.dev.exception.CarNotFoundException;
import com.ushan.dev.model.Car;
import com.ushan.dev.repository.CarRepository;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceUnitTest {
	
	@Mock
	CarRepository carRepository;
	
	@InjectMocks
	CarService carService;
	
	List<String> list;
	
	@Test
	public void testGetCarDetails() throws Exception {
		when(carRepository.findByName("KICKS")).thenReturn(Optional.of(new Car("KICKS","SUV")));
		
		Car car = carService.getCarDetails("KICKS");
		
		assertThat(car.getType(),is("SUV"));
		verify(carRepository,times(1)).findByName("KICKS");
		
	}
	
	@Test(expected=CarNotFoundException.class)
	public void testCarNotFound() throws Exception {
		//Given
		given(carRepository.findByName("NOTHING")).willReturn(Optional.empty());
		
		//When
		Car car = carService.getCarDetails("NOTHING");
	}
	
	@Test
	public void testSaveOrUpdate() {
		//Given
		Car returnedCar = new Car("TRIBER", "MPV");
		returnedCar.setId(1001L);
		given(carRepository.save(any())).willReturn(returnedCar);
		
		//When
		Car car = carService.saveOrUpdate(new Car("TRIBER", "MPV"));
		
		//Then
		assertThat(car.getId(),is(returnedCar.getId()));
		then(carRepository).should().save(any());
		then(carRepository).should(atLeastOnce()).save(any());
	}
	
	@Test
	public void testSaveListOfCars() {
		//Given
		given(carRepository.save(any(Car.class))).willReturn(new Car("Dummy1","Type1"));
		
		//When
		carService.saveOrUpdate(new Car("Name1","Type1"));
		carService.saveOrUpdate(new Car("Name2","Type2"));
		
		//Then
		then(carRepository).should(times(2)).save(any(Car.class));

	}

}
