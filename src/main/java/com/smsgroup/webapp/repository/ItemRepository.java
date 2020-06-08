package com.smsgroup.webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository; 

import com.smsgroup.webapp.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
