package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.forms.ItemForm;
import com.example.demo.servise.ItemService;


/**
 * ユーザー情報 Controller
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	ItemService itemService;
	/**
	 * ユーザー登録画面を表示
	 */
	@GetMapping("/create")
	public String create(ItemForm itemForm) {
		//th:object="${itemForm}(↑の引数)で画面に出す。
		return "root/item";
	}
	/**
	 * ユーザー情報を登録
	 */
	@PostMapping("/create")
	public String create(Long id, @Validated ItemForm itemForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/item";
		}
		itemService.create(itemForm,id);
		itemForm.clear();
		model.addAttribute("message", "商品を登録しました。");
		return "root/item";

	}
	/**
	 * ユーザー一覧画面を表示
	 */
	@GetMapping("/list")
	//戻り値をStringとしmodelOBJを引数にわたす
	public String list(Model model) { 
		//itemControllerはitemServiceの参照先である
		//itemServiceクラスに対してsearchAllメソッドを実行
		List<ItemForm> itemForm = itemService.searchAll();
		//itemlistsというkeyにitemFormの参照先であるItemFormリストオブジェクトをいれる
		model.addAttribute("itemlists", itemForm);
		return "root/list"; 
	}
	/**
	 * ユーザー編集画面を表示
	 */
	@GetMapping("/itemforms/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		//itemControllerはitemServiceの参照先である
		//ItemServiceクラスに対してeditメソッドを実行し必要な値としてidを渡す
		ItemForm itemForm = itemService.edit(id);
		model.addAttribute("itemData", itemForm);
		return "itemforms/edit";
		//NG→/itemforms/edit
		//上記の場合は、resources/templates/itemforms/edit.htmlが存在する必要がある。
	}
	/**
	 * ユーザー情報更新と一覧表示
	 */
	@PostMapping("/itemforms/{id}")
	public String update(@PathVariable Long id, Model model, @ModelAttribute ItemForm itemForm) {
		itemService.update(id,itemForm);
		List<ItemForm> itemData = itemService.searchAll();
		model.addAttribute("itemlists", itemData);
		return "root/list";
		//リダイレクトの場合は、先頭に「/」が必要
		//return "redirect:/customer/list";OK
		//return "redirect:customer/list";NG
	}
	/**
	 * ユーザー情報削除と一覧表示
	 */
	@PostMapping("/itemforms/{id}/delete")
	public String destroy(@PathVariable Long id, Model model) {
		itemService.destroy(id);
		List<ItemForm> itemData = itemService.searchAll();
		model.addAttribute("itemlists", itemData);
		return "root/list";
	}
	/**
	 * ユーザー情報詳細を表示
	 */
	@GetMapping("/itemforms/{id}/show")
	public String show(@PathVariable Long id, Model model) { 
		ItemForm itemForm = itemService.edit(id);
		model.addAttribute("itemData", itemForm);
		return "itemforms/show";
	}
}