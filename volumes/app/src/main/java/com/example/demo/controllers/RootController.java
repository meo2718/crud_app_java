package com.example.demo.controllers;

import java.util.List;
//import java.util.Optional;
//import java.util.Optional;

//import javax.xml.crypto.dsig.TransformService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

//import com.example.demo.ItemFormListParam.ItemFormListParam;
import com.example.demo.entities.ItemEntity;
//import com.example.demo.entities.ItemEntity;
import com.example.demo.forms.ItemForm;
import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;
import com.example.demo.models.ItemFormOld;
import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;
//import com.example.demo.repositries.ItemListRepository;
import com.example.demo.repositries.ItemRepository;
import com.example.demo.servise.ItemService;


@Controller
@RequestMapping("/")
public class RootController {
//InquiryRepositoryをnewしてrepositoryというタグ付けでセット
	@Autowired
	InquiryRepository repository;
	@Autowired
	InquiryRepository2 repository2;
	@Autowired
	ItemRepository repository3;


	@GetMapping
	public String index() {
		return "root/index";
	}

	@GetMapping("/form")
	public String form(InquiryForm inquiryForm) {
		return "root/form";
	}

	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form";
		}

		// RDBと連携できることを確認しておきます。
		repository.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form";
	}
	@GetMapping("/form2")
	public String form2(InquiryForm2 inquiryForm2) {
		return "root/form2";
	}

	@PostMapping("/form2")
	public String form2(@Validated InquiryForm2 inquiryForm2, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form2";
		}

		// RDBと連携できることを確認しておきます。
		repository2.saveAndFlush(inquiryForm2);
		inquiryForm2.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form2";
	}
	
	@GetMapping("/item")
	public String create(ItemForm itemForm) {
//Itemformクラスの参照先→ItemForm.javaの中のデータをitem.htmlの
//th:object="${itemForm}(↑の引数)で画面に出す。
		return "root/item";
	}
    @Autowired
    ItemService itemService;
	@PostMapping("/item")
	public String create(@Validated ItemForm itemForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/item";
		}
		itemService.create(itemForm);
		itemForm.clear();
		model.addAttribute("message", "商品を登録しました。");
		return "root/item";

	}
 refactoring
	@GetMapping("/list")
  public String list(Model model) { 
      ItemForm itemForm = itemService.searchAll();
      model.addAttribute("itemlists", itemForm);
//itemlistsというkeyをセットし、DBからfinaAllで全検索した値をdisplaylistにいれて画面に返してる
//viewでこのkeyを取り出してeachでitemEntityの数だけ回す
      return "root/list"; 
  }
	
//	@GetMapping("/list")
//    public String list(Model model) { 
//        List<ItemEntity> displaylist = itemService.searchAll();
//        model.addAttribute("itemlists", displaylist);
//        return "root/list"; 
//    }
//
//	
//	@GetMapping("/itemforms/{id}/edit")
//    public String edit(@PathVariable Long id, Model model) {
//        ItemEntity itemEntity = itemService.edit(id);
//        model.addAttribute("itemData", itemEntity);
//        return "itemforms/edit";
// //NG→/itemforms/edit
// //上記の場合は、resources/templates/itemforms/edit.htmlが存在する必要がある。
//    }
////	
//	@PostMapping("/itemforms/{id}")
//    public String update(@PathVariable Long id, Model model, @ModelAttribute ItemEntity itemEntity) {
//		itemService.update(id,itemEntity);
//        List<ItemEntity> displaylist = itemService.searchAll();
//        model.addAttribute("itemlists", displaylist);
//        return "root/list";
// //リダイレクトの場合は、先頭に「/」が必要
// //return "redirect:/customer/list";OK
// //return "redirect:customer/list";NG
//    }
//	
//	@PostMapping("/itemforms/{id}/delete")
//    public String destroy(@PathVariable Long id, Model model) {
//        itemService.destroy(id);
//        List<ItemEntity> displaylist = itemService.searchAll();
//        model.addAttribute("itemlists", displaylist);
//        return "root/list";
//    }
//	
//	@GetMapping("/itemforms/{id}/show")
//    public String show(@PathVariable Long id, Model model) { 
//        ItemEntity itemEntity = itemService.edit(id);
//        model.addAttribute("itemData", itemEntity);
//        return "itemforms/show";
//	}


}