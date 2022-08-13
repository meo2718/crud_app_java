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

/*
 * ユーザー情報　サービス
 */
@Service
public class ItemService {
	/*
	 * ユーザー情報 リポジトリ
	 */
	@Autowired
	ItemRepository repository3;
	/*
	 * ユーザー情報　新規登録
	 * @params itemEntity ユーザー情報
	 */
	public void create(ItemForm itemForm) {
//itemFormをitemEntityに格納し直してリポジトリクラスを呼びsaveメソッドを使うことで
//itemEntityの内容をdbへ登録できる。
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setName(itemForm.getName());
		itemEntity.setPrice(itemForm.getPrice());
		itemEntity.setContent(itemForm.getContent());
		repository3.save(itemEntity);
		//saveはsimpleJPArepositoryで実装
	}
	//	public void formservice(ItemForm itemForm, ItemEntity itemEntity) {
	//		repository3.saveAndFlush(itemEntity);
	//
	//	}
	/*
	 * ユーザー情報全検索
	 */
	public ItemForm searchAll() {
		//searchAllの型としてItemFormをかえす
		List<ItemEntity> displayList = repository3.findAll();
		ItemForm itemForm = new ItemForm();
//itemServiceクラスはItemFormクラスに対してnewをお願いします
		List<ItemForm> list = new ArrayList<ItemForm>();
//itemServiceはlistをItemForm型の配列として扱うと決めてArrayListクラスをnewした
//結果をlistの中にItemFormのリストとして返す
		for(ItemEntity itemEntity: displayList) {
//repository3をfindAllしたdisplayListの配列をItemEntity型のitemEntityという変数に
//displayListから1つずつ配列を取り出していれていき、要素を取り出して出力し、listへいれる
			ItemForm form = new ItemForm();
//itemServiceはItemFormクラスに対してnewして、formオブジェクトを生成する
			form.setId(itemEntity.getId());
//生成したformオブジェクトに対してsetIdメソッドを実行し必要な値としてitemEntity
//をgetIdした値を渡す。つまり、itemEntityのidをgetしてきてそれをsetしてformに代入し書き換えてる
			form.setName(itemEntity.getName());
			form.setPrice(itemEntity.getPrice());
			form.setContent(itemEntity.getContent());
			list.add(form);
			//itemForm = list.add(form);
//itemFormのlistに書き換えた値がすべてはいる
		}
		//temForm = list.set(0,itemForm);
		itemForm.setList(list);
		//itemForm = list.(form);
		//ItemForm.setItemFormList(list);
		return itemForm;
//呼び出し元のItemFormクラスをnewしたitemFormに変換した値をいれたlistを返したい
	}
//	public List<ItemEntity> searchAll() {
//		return repository3.findAll();
//	}
	//リポジトリを使いDBから全検索、その結果をItemEntityにいれる


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
