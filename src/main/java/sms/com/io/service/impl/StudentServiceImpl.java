package sms.com.io.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.com.io.entity.Student;
import sms.com.io.repository.StudentRepository;
import sms.com.io.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	//get all students
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	//save student

	@Override
	public Student saveStudent(Student s) {
		Student s1=studentRepository.save(s);
		return s1;
	}

	@Override
	public Student findStudent(Long id) {
		Student s=studentRepository.findById(id).get(); 
		return s;
	}

	@Override
	public Student updateStudent(Student s) {
		
		Student s1=studentRepository.save(s);
		
		return s1;
	}

	@Override
	public void deleteStudent(Long id) {
studentRepository.deleteById(id);		
	}
	
 
}
