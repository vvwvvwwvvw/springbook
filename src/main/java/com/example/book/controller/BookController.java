package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	//도서등록 화면 출력
	@GetMapping("/save")
	public String save() {
		return "save";
	}
	
	@GetMapping("/list")
	public String findAll(Model model) {
		// DB에서 목록 데이터를 가져옴
		List<BookDTO> bookDTOs = bookService.findAll();
		//리스트 데이터를 model에 담음
		model.addAttribute("bookList",bookDTOs);
		//list.html로 이동
		return "list";
	}
	
	@GetMapping("/book/{id}")
	public String findById(@PathVariable("id") Long id ,Model model) {
		System.out.println("id=" + id);
		BookDTO bookDTO = bookService.findById(id);
		model.addAttribute("book",bookDTO);
		return "detail";
	}
}
