package com.yodlee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yodlee.entity.Employee;
import com.yodlee.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/list")
	public String listEmployee(Model theModel)
	{

		List<Employee> theEmployees = employeeService.findAll();
		
		
		theModel.addAttribute("employee",theEmployees);
		return "employee/list-employees";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		
		return "employee/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee)
	{
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		Employee theEmployee = employeeService.findById(theId);
		theModel.addAttribute("employee",theEmployee);
		
		return "employee/employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}
	
	
	
	
}
