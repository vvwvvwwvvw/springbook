package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@PostMapping("/save")
	public String save(@ModelAttribute BookDTO bookDTO) {
	System.out.println("BookDTO= " + bookDTO);
	bookService.save(bookDTO);
	// 단순하게 list.html만 요청
	//return "list"
	// list 출력을 위해 list 주소 요청
	// redirect: 컨트롤러의 메서드에서 다른 메서드이 주소를 요청하고자 할 떄
	return "redirect:/list";
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
	@GetMapping("/book/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		bookService.delete(id);
		return "redirect:/list";
	}
	@GetMapping("/book/update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		BookDTO bookDTO = bookService.findById(id);
		model.addAttribute("book",bookDTO);
		return "update";
	}
	@PostMapping("/update")
	public String update(BookDTO bookDTO) {
		System.out.println("BookDTO = " + bookDTO);
		bookService.update(bookDTO);
		return "redirect:/list";
	}
	
}
