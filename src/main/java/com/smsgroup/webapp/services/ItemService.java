package com.smsgroup.webapp.services;

import java.text.ParseException;
import java.util.List;

import com.smsgroup.webapp.model.Item;

public interface ItemService {
	public List<Item> findAll() throws ParseException;
	public Item findItemById(long id);
	public Item saveOrUpdate(Item item);
	public void deleteItem(long id);
}
