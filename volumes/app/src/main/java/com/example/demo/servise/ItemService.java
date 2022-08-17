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
	//createメソッドの引数にitemformをいれる
	public void create(ItemForm itemForm, Long id) {
		//itemServiceはItemRepositoryに対してsaveメソッドを実行して
		//必要な値として変換メソッドとその引数としてitemFormとidを渡す
		itemrepository.save(convertFormToEntity(itemForm,id));
	}

	/**
	 * ユーザー情報全検索
	 */
	//searchAllメソッドの戻り値としてList<ItemForm>を指定しControllerで受け取る
	public List<ItemForm> searchAll() {
		//itemServiceはitemRepositoryの参照先であるItemRepositoryクラスに対してfindAllを実行
		List<ItemEntity> itemEntityList = itemrepository.findAll();
		//itemServiceはArrayListクラスをnewして必要な値としてItemFormクラスをいれる
		//結果をitemFormlistの中にItemForm型のリストとして返す
		List<ItemForm> itemFormlist = new ArrayList<ItemForm>();
		//itemRepositoryをfindAllしたitemEntityListの配列をItemEntity型のitemEntityという変数に
		//itemEntityListから1つずつ配列を取り出していれていき、要素を出力し、itemFormlistへいれる
		for(ItemEntity itemEntity: itemEntityList) {
			//itemFormlistに対してaddメソッドを実行し必要な値として
			//変換メソッドと引数にitemEntityをわたす
			itemFormlist.add(convertEntityToForm(itemEntity));
		}
		//呼び出し元のList<ItemForm>クラスをnewしたitemFormListに変換した値をいれて戻り値として返す
		return itemFormlist;
	}

	/**
	 * ユーザー情報編集
	 */
	//editメソッドの戻り値としてItemFormを指定しControllerで受け取る
	public ItemForm edit(Long id) {
		//itemserviceはitemrepositoryの参照先であるItemRepositoryクラスに対してfindByIdを実行
		ItemEntity itemEntity = itemrepository.findById(id).get();
		//itemServiceはItemFormクラスに対してconvertEntityToFormという振る舞いをお願いして
		//必要な値としてitemEntityをメソッドに渡し変換して帰ってきたitemFormを変数itemFormへいれる
		ItemForm itemForm = convertEntityToForm(itemEntity);
		return itemForm;
	}

	/**
	 * ユーザー情報更新
	 */	
	public void update(Long id, ItemForm itemForm) {
		//itemServiceはItemEntityクラスに対してconvertFormToEntityという振る舞いをお願いして
		//必要な値としてitemFormをメソッドに渡し変換して帰ってきたitemEntityを変数itemEntityへいれる
		ItemEntity itemEntity = convertFormToEntity(itemForm, id);
		//itemRepositoryに対してseveメソッドを実行し、
		//必要な値として変換した値がはいったitemEntityをわたしセーブする
		itemrepository.save(itemEntity);
	}

	/**
	 * ユーザー情報削除
	 */
	public void destroy(Long id) {
		itemrepository.deleteById(id);
	}
	/**
	 * convert
	 * @param id 
	 */
	//formからEntityへ変換
	//生成したitemEntityオブジェクトに対してsetNameメソッドを実行し必要な値としてitemForm
	//をgetNameした値を渡す。つまり、itemFormのNameをgetしてきてそれをsetしてitemEntityに代入し書き換えてる
	public ItemEntity convertFormToEntity(ItemForm itemForm, Long id) {
		ItemEntity itemEntity = new ItemEntity();
		if (null != id ) {
			itemEntity.setId(itemForm.getId());
		}
		itemEntity.setName(itemForm.getName());
		itemEntity.setPrice(itemForm.getPrice());
		itemEntity.setContent(itemForm.getContent());
		return itemEntity;
	}

	//formをentityからformへ変換
	//生成したformオブジェクトに対してsetIdメソッドを実行し必要な値としてitemEntity
	//をgetIdした値を渡す。つまり、itemEntityのidをgetしてきてそれをsetしてformに代入し書き換えてる
	public ItemForm convertEntityToForm(ItemEntity itemEntity) {
		ItemForm itemForm = new ItemForm();
		itemForm.setId(itemEntity.getId());
		itemForm.setName(itemEntity.getName());
		itemForm.setPrice(itemEntity.getPrice());
		itemForm.setContent(itemEntity.getContent());
		return itemForm;
	}
}
