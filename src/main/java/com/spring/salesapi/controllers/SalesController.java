package com.spring.salesapi.controllers;
import com.spring.salesapi.models.Sales;
import com.spring.salesapi.repositories.SalesRepository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(value="onlinesales", description="All CRUD opertaining to sales")
public class SalesController {

    @Autowired
    SalesRepository salesRepository;

    @ApiOperation(value = "View a list of all sales",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    	}
    )    
    @RequestMapping(method=RequestMethod.GET, value="/sales", produces = "application/json")
    public Iterable<Sales> sales() {
        return salesRepository.findAll();
    }

    @ApiOperation(value = "Create a new sales record",response = Sales.class)
    @RequestMapping(method=RequestMethod.POST, value="/sales", produces = "application/json")
    public String save(@RequestBody Sales sales) {
        salesRepository.save(sales);

        return sales.getId();
    }

    @ApiOperation(value = "Search a sales record with an id",response = Sales.class)
    @RequestMapping(method=RequestMethod.GET, value="/sales/{id}", produces = "application/json")
    public Sales show(@PathVariable String id) {
    	Optional<Sales> mysale = salesRepository.findById(id);
        if (mysale.isPresent())
        	return mysale.get();
    	else
    		return new Sales();
    }

    @ApiOperation(value = "Update a sales record with an id",response = Sales.class)
    @RequestMapping(method=RequestMethod.PUT, value="/sales/{id}", produces = "application/json")
    public Sales update(@PathVariable String id, @RequestBody Sales sales) {
        Optional<Sales> mysale = salesRepository.findById(id);
        if (mysale.isPresent()) {
        	Sales sale = mysale.get();
	        if(sales.getPersonId() != null)
	        	sale.setPersonId(sales.getPersonId());
	        if(sales.getClientId() > 0)
	        	sale.setClientId(sales.getClientId());
	        if(sales.getInventoryId() > 0)
	        	sale.setInventoryId(sales.getInventoryId());
	        sale.setPrice(sales.getPrice());
	        sale.setDiscount(sales.getDiscount());
	        sale.setTax(sales.getTax());
	        if(sales.getQuantity() > 0)
	        	sale.setQuantity(sales.getQuantity());
	        if(sales.getCreated() != null)
	        	sale.setCreated(sales.getCreated());
	        if(sales.getUpdated() != null)
	        	sale.setUpdated(sales.getUpdated());	        
	        salesRepository.save(sale);
	        return sale;
        }
        else
        	return new Sales();
    }

    @ApiOperation(value = "Delete a sales record with an id",response = Sales.class)
    @RequestMapping(method=RequestMethod.DELETE, value="/sales/{id}", produces = "application/json")
    public String delete(@PathVariable String id) {
    	Optional<Sales> sales = salesRepository.findById(id);
    	if (sales.isPresent()) {
    		salesRepository.delete(sales.get());
    		return "sales deleted";
    	}
    	else
    		return "sale ID not found";
    }	
}
