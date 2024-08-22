package com.jt.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
	private StudentRepository sr;
	@Autowired
	public StudentController(StudentRepository sr) {
		this.sr = sr;
	}
	@RequestMapping("/")
	String home(Model m)
	{
		m.addAttribute("studs", sr.findAll());
		return "index";
	}
	@RequestMapping("add")
	String addStud(Model m)
	{
		m.addAttribute("stud", new Student());
		return "form";
	}
	@PostMapping("formSubmit")
	String submitPage(@ModelAttribute Student s)
	{
		sr.save(s);
		return "redirect:/";
	}
	
	@GetMapping("update")
	String updateData(@RequestParam("id")int id, Model m)
	{
		Optional<Student> os = sr.findById(id);
		m.addAttribute("stud", os);
		return "form";
	}
	
	@GetMapping("delete")
	String deleteData(@RequestParam("id")int id)
	{
		sr.deleteById(id);
		return "redirect:/";
	}
	
}
