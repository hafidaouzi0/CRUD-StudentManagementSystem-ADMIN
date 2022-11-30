package sms.com.io.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import sms.com.io.entity.Student;
import sms.com.io.service.StudentService;

//  @RestController we work with postman for returning json
//@controller we work with view and model
@Controller
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	
	
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("homePage");
		return mv;
	}
	
	
	
	
	
	
	
	
	//get all students
	
	@GetMapping("/students")
	public ModelAndView getAll() {
				List<Student> students= studentService.getAllStudents();
				ModelAndView mv =new ModelAndView();
				mv.addObject("students", students);
				mv.setViewName("students");
				return mv;
	}
	
	
	
	//create a student ,loading form first
	@GetMapping("/students/new")
	public ModelAndView createStudentForm() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("studentform");
		Student s=new Student();
		mv.addObject("student", s);
		return mv;
		
	}
	
	
	//save a student
	
	@PostMapping("/saved")
	public ModelAndView saveStudent(@ModelAttribute("student")  Student s) {
		ModelAndView mv=new ModelAndView();
		studentService.saveStudent(s);
		mv.setViewName("redirect:/students");
		return mv;
	}

	//update a student's data
	@GetMapping("/students/edit/{id}")
	public ModelAndView editStudentForm(@PathVariable("id") Long id) {
		Student s=studentService.findStudent(id);
		ModelAndView mv=new ModelAndView();
		mv.addObject("student", s);
		mv.setViewName("editstudentform");
		return mv;
	}
	
	//update a student
	//1s way :
//	@PostMapping("/updated")
//	public ModelAndView updateStudent(@ModelAttribute("student") Student s) {
//		
//		studentService.updateStudent(s);
//		ModelAndView mv=new ModelAndView();
//		mv.setViewName("redirect:/students");
//		
//		return mv;
//	}
	
	//2nd way
	
	@PostMapping("/updated/{id}")
	public ModelAndView updateStudent(@PathVariable("id") Long id,
			@ModelAttribute("student") Student s) {
		//get student from data base by id
		Student existingStudent=studentService.findStudent(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(s.getFirstName());
		existingStudent.setLastName(s.getLastName());
		existingStudent.setEmail(s.getEmail());
		//save the opdated student object
		studentService.updateStudent(existingStudent);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/students");
		
		return mv;
	}
	

	//delete Student
	
	@GetMapping("/students/delete/{id}")
	public ModelAndView deleteStudent(@PathVariable("id")Long id) {
		studentService.deleteStudent(id);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/students");
		return mv;
		
	}
	
	
}
