package com.spring.technician.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.technician.entity.WOrder;
import com.spring.technician.exception.CustomErrorType;
import com.spring.technician.services.WOrderService;

@RestController
@RequestMapping("/service")
public class TechnicianRestService {

	@Autowired
	WOrderService orderService;
	
	@RequestMapping(value="/worder/",method = RequestMethod.POST)
	public WOrder createOrder(@RequestBody WOrder wOrder){
		return orderService.createWorkOrder(wOrder);
	}
	
	@RequestMapping(value="/worder/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getOrder(@PathVariable("id") String id,@RequestParam("name") String name) {
		WOrder order = orderService.getWorkOrder(id);
		if(order!=null) {
			return new ResponseEntity<WOrder>(order, HttpStatus.OK);
		}
		return new ResponseEntity(new CustomErrorType("Unable to get. order with id " + id + " not found."),
                HttpStatus.NOT_FOUND);
	}
}
