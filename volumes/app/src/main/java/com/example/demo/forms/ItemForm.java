package com.example.demo.forms;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import lombok.Data;


@Data
public class ItemForm implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 10)
	private String name;

	@NotBlank
	private String price;
	
	@NotBlank
	@Size(max = 400)
	private String content;
	
	public void clear() {
		name = null;
		price = null;
		content = null;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
