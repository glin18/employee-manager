package com.gary.thymeleaf_demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gary.thymeleaf_demo.entity.Employee;
import com.gary.thymeleaf_demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping({"/showEmployees", "/", "/list"})
	public ModelAndView showEmployees() {
		ModelAndView mav = new ModelAndView("list-employees");
		List<Employee> list = employeeRepository.findAll();
		mav.addObject("employees", list);
		return mav;
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee newEmployee = new Employee();
		mav.addObject("employee", newEmployee);
		return mav;
	}
	
	@PostMapping("/saveEmployee")
	public void saveEmployee(Employee employee, HttpServletResponse response) throws IOException {
		employeeRepository.save(employee);
		response.sendRedirect("/list");
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView updateEmployeeForm(@RequestParam Long employeeId) {
		ModelAndView mav = new ModelAndView("update-employee-form");
		Employee employee = employeeRepository.findById(employeeId).get();
		mav.addObject("employee", employee);
		return mav;
	}
	
	@PostMapping("/updateEmployee")
	public void updateEmployee(Employee employee, HttpServletResponse response) throws IOException {
		Employee oldEmployee = employeeRepository.findById(employee.getId()).get();
		oldEmployee.setName(employee.getName());
		oldEmployee.setDepartment(employee.getDepartment());
		oldEmployee.setEmail(employee.getEmail());
		employeeRepository.save(oldEmployee);
		response.sendRedirect("/list");
	}
	
	@GetMapping("/deleteEmployee")
	public void deleteEmployee(@RequestParam Long employeeId, HttpServletResponse response) throws IOException {
		Employee employee = employeeRepository.findById(employeeId).get();
		employeeRepository.delete(employee);
		response.sendRedirect("/list");
	}
	
}
