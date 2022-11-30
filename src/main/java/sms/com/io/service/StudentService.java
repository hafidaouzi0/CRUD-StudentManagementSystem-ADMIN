package sms.com.io.service;

import java.util.List;
import java.util.Optional;

import sms.com.io.entity.Student;

public interface StudentService {

	
	public List<Student> getAllStudents();
	
	public Student saveStudent(Student s);
	public Student findStudent(Long id);
	public Student updateStudent(Student s);
	public void deleteStudent(Long id);
}
