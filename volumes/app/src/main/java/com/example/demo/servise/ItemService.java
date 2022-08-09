package com.example.demo.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ItemFormOld;
import com.example.demo.repositries.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository repository3;
	public void formservice(ItemFormOld itemForm) {
		repository3.saveAndFlush(itemForm);
	
	}
	
	public List<ItemFormOld> list() {
		return repository3.findAll();
	}
	
	public ItemFormOld edit(Long id) {
		return repository3.findById(id).get();
	}
	
	public void update(Long id, ItemFormOld itemform) {
		repository3.save(itemform).setId(id);
	}
	
	public void destroy(Long id) {
		repository3.deleteById(id);
	}

}
