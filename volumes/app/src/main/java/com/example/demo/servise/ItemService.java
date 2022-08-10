package com.example.demo.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ItemEntity;
//import com.example.demo.forms.ItemForm;
//import com.example.demo.models.ItemFormOld;
import com.example.demo.repositries.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository repository3;
	public void formservice(ItemEntity itemEntity) {
		repository3.saveAndFlush(itemEntity);
	
	}
	
	public List<ItemEntity> list() {
		return repository3.findAll();
	}
	
	public ItemEntity edit(Long id) {
		return repository3.findById(id).get();
	}
	
	public void update(Long id, ItemEntity itemEntity) {
		repository3.save(itemEntity).setId(id);
	}
	
	public void destroy(Long id) {
		repository3.deleteById(id);
	}

}
