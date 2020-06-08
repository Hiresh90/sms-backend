package com.smsgroup.webapp.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smsgroup.webapp.exceptions.ItemNotFoundException;
import com.smsgroup.webapp.model.Item;
import com.smsgroup.webapp.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemRepository repository;
	
	private EntityManager entityManager;
	
	public ItemServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public List<Item> findAll() throws ParseException {
		List<Item> items = repository.findAll();
		
		if(items.size()>0) {
			return items;
		}else {
			return new ArrayList<Item>();
		}
	}

	@Override
	@Transactional
	public Item findItemById(long id) {
		Optional<Item> item = repository.findById(id);
		
		if(item.isPresent()) {
			return item.get();
		}else {
			throw new ItemNotFoundException("Item not found");
		}
	}

	@Override
	@Transactional
	public Item saveOrUpdate(Item resource) {
		
		if(resource.getId() != 0) {
			Optional<Item> item = repository.findById(resource.getId());
			
			if(item.isPresent()) {
				System.out.println("Present");
				Item newItem = item.get();
				newItem.setCity(resource.getCity());
				newItem.setStartDate(resource.getStartDate());
				newItem.setEndDate(resource.getEndDate());
				newItem.setPrice(resource.getPrice());
				newItem.setColor(resource.getColor());
				newItem.setStatus(resource.getStatus());
				newItem = repository.save(newItem);
				return newItem;
			}else {
				System.out.println("Inner else");
				resource = repository.save(resource);
				return resource;
			}
		}else {
			System.out.println("Outer else");
			resource = repository.save(resource);			
			return resource;
		} 
	}

	@Override
	public void deleteItem(long id) {
		Optional<Item> item = repository.findById(id);	
		
		if(item.isPresent()) {
			repository.deleteById(id);
		}else {
			
		}
	}
	
	@Transactional
	public List<Item> getItemBetweenDatRange(Date startDate, Date endDate){
	
		Session currentSession = entityManager.unwrap(Session.class); 
		Query theQuery =  currentSession.createQuery( "from Item where start_date>=:startDate and end_date<=:endDate");
		theQuery.setParameter("startDate", startDate);
		theQuery.setParameter("endDate", endDate);
		List<Item> item = theQuery.getResultList(); 
	
		return item;
		
	}
	
}
