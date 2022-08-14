package com.example.demo.repositries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.ItemEntity;

/**
 * ユーザー情報 Repository
 */
	@Repository
	public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
	}