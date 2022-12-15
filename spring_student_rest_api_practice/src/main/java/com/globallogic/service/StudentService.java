package com.globallogic.service;

import java.util.List;

import com.globallogic.dto.StudentDto;

public interface StudentService {

	List<StudentDto> getAllStudents();

	StudentDto addStudent(StudentDto student);

	StudentDto getStudentById(long id);

	StudentDto updateStudent(long id, StudentDto student);

	void deleteStudentById(long id);

}
