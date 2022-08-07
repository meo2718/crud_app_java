package com.example.demo.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.demo.models.InquiryForm;
//Kanato0331
//import com.example.demo.models.InquiryForm2;
import com.example.demo.models.ItemFormOld;

	@Repository
	public interface ItemRepository extends JpaRepository<ItemFormOld, Long>{
		Optional<ItemFormOld> findById(Long id);
		List<ItemFormOld> findAll();
		
		
	}