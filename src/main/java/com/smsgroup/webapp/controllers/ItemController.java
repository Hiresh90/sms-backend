package com.smsgroup.webapp.controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.smsgroup.webapp.model.Item; 
import com.smsgroup.webapp.services.ItemServiceImpl;

@RestController
public class ItemController {
	
	@Autowired
	private ItemServiceImpl itemService;
	
	@GetMapping("/")
	public String helloWord() {
		return "Hello World";
	}
	
	@GetMapping("/appName")
	public String getAppName() {
		return "Welcome to Codemania";
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getItems() throws ParseException{ 
		
		List<Item> item = itemService.findAll();
		
		return new ResponseEntity<List<Item>>(item,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") int id) throws ParseException{ 
		
		Item item = itemService.findItemById(id);
		
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/items")
	public ResponseEntity<Item> createItem(@RequestBody Item item){
		item = itemService.saveOrUpdate(item);
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/items")
	public ResponseEntity<Item> updateItem(@RequestBody Item item){
		item = itemService.saveOrUpdate(item);
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/items/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") int id){
		itemService.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/searchDate")
	public ResponseEntity<List<Item>> searchDateRage(@RequestParam("startDate")  @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) throws ParseException{ 
		
		List<Item> item = itemService.getItemBetweenDatRange(startDate, endDate);
		
		return new ResponseEntity<List<Item>>(item,HttpStatus.OK);
	}
	
}
