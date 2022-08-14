package com.example.demo.servise;

import java.util.ArrayList;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.demo.ItemFormListParam.ItemFormListParam;
//import com.example.demo.ItemFormListParam.ItemFormListParam;
//import com.example.demo.controllers.Itemform;
import com.example.demo.entities.ItemEntity;
import com.example.demo.forms.ItemForm;
//import com.example.demo.entities.ItemEntity;
//import com.example.demo.forms.ItemForm;
//import com.example.demo.models.ItemFormOld;
import com.example.demo.repositries.ItemRepository;

/**
 * ユーザー情報　サービス
 */
@Service
public class ItemService {
	/**
	 * ユーザー情報 リポジトリ
	 */
	@Autowired
	ItemRepository itemrepository;
	/**
	 * ユーザー情報　新規登録
	 * @params itemEntity ユーザー情報
	 */
	public void create(ItemForm itemForm) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setName(itemForm.getName());
		itemEntity.setPrice(itemForm.getPrice());
		itemEntity.setContent(itemForm.getContent());
		itemrepository.save(itemEntity);
	}
	/**
	 * ユーザー情報全検索
	 */
	//searchAll
	public List<ItemForm> searchAll() {
		
		List<ItemEntity> itemEntityList = itemrepository.findAll();
//itemServiceクラスはItemFormクラスに対してnewをお願いします
		ItemForm itemForm = new ItemForm();
//itemServiceはlistをItemForm型の配列として扱うと決めてArrayListクラスをnewした
//結果をlistの中にItemFormのリストとして返す
		List<ItemForm> itemFormlist = new ArrayList<ItemForm>();
//repository3をfindAllしたdisplayListの配列をItemEntity型のitemEntityという変数に
//displayListから1つずつ配列を取り出していれていき、要素を取り出して出力し、listへいれる
		for(ItemEntity itemEntity: itemEntityList) {
//itemServiceはItemFormクラスに対してnewして、formオブジェクトを生成する
			ItemForm form = new ItemForm();
//生成したformオブジェクトに対してsetIdメソッドを実行し必要な値としてitemEntity
//をgetIdした値を渡す。つまり、itemEntityのidをgetしてきてそれをsetしてformに代入し書き換えてる
			form.setId(itemEntity.getId());
			form.setName(itemEntity.getName());
			form.setPrice(itemEntity.getPrice());
			form.setContent(itemEntity.getContent());
			itemFormlist.add(form);
		}
		itemForm.setList(itemFormlist);
//呼び出し元のList<ItemForm>クラスをnewしたitemFormListに変換した値をいれて戻り値として返す
		return itemFormlist;
	}
	/**
	 * ユーザー情報編集
	 */
	public ItemForm edit(Long id) {
//itemserviceはitemrepositoryの参照先であるItemRepositoryクラスに対してfindByIdを実行
		ItemEntity itemEntity = itemrepository.findById(id).get();
		ItemForm itemForm = new ItemForm();
		itemForm.setId(itemEntity.getId());
		itemForm.setName(itemEntity.getName());
		itemForm.setPrice(itemEntity.getPrice());
		itemForm.setContent(itemEntity.getContent());
		return itemForm;
	}
	
	/**
	 * ユーザー情報更新
	 */	
	public void update(Long id, ItemForm itemForm) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(itemForm.getId());
		itemEntity.setName(itemForm.getName());
		itemEntity.setPrice(itemForm.getPrice());
		itemEntity.setContent(itemForm.getContent());
		itemrepository.save(itemEntity);
	}
	/**
	 * ユーザー情報削除
	 */
	public void destroy(Long id) {
		itemrepository.deleteById(id);
	}
}
