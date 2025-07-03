package com.spring.springbootdataapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.springbootdataapp.entity.Employee;
import com.spring.springbootdataapp.repository.EmployeeRepository;
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("employees",repo.findAll());
		return "index";
		
	}
	@GetMapping("/new")
	  public String newForm(Model model) {
	      model.addAttribute("employee", new Employee());
	      return "form";
	  }

	  @PostMapping("/save")
	  public String save(@ModelAttribute Employee employee) {
	      repo.save(employee);
	      return "redirect:/";
	  }

	  @GetMapping("/edit/{id}")
	  public String showEditForm(@PathVariable Long id, Model model) {
	      Employee employee = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
	      model.addAttribute("employee", employee);
	      return "Edit-Emp";
	  }

	  @PostMapping("/update/{id}")
	  public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee updated) {
	      Employee existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
	      existing.setName(updated.getName());
	      existing.setDepartment(updated.getDepartment());
	      existing.setEmail(updated.getEmail());
	      repo.save(existing);
	      return "redirect:/";
	  }



	  @GetMapping("/delete/{id}") // ✅ Matches form's method="post"
	  public String deleteEmployee(@PathVariable Long id) {
	      repo.deleteById(id);
	      return "redirect:/"; // Redirect to employee list page
	  }

}
