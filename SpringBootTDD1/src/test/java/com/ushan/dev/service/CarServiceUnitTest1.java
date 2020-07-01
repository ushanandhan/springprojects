package com.ushan.dev.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
public class CarServiceUnitTest1 {

	@Mock
	CarRepository carRepository;
	
	@InjectMocks
	CarService carService;
	
	@Test
	public void testGetCarDetails() throws Exception {
		//When
		when(carRepository.findByName("KICKS")).thenReturn(Optional.of(new Car("KICKS","SUV")));
		
		//Act
		Car testedCar = carService.getCarDetails("KICKS");
		
		//Verify
		assertEquals("Type is not equal", "SUV",testedCar.getType());
		assertNull(testedCar.getId());
		
		verify(carRepository).findByName(anyString());
	}

	@Test(expected=CarNotFoundException.class)
	public void testCarNotFound() throws Exception {
		//when
		when(carRepository.findByName(anyString())).thenReturn(Optional.empty());
		
		//act
		carService.getCarDetails("KICKS");
		
		//verify
		//Nothing to verify here. 
	}
//
//	@Test
//	public void testSaveOrUpdate() {
//		fail("Not yet implemented");
//	}

}
