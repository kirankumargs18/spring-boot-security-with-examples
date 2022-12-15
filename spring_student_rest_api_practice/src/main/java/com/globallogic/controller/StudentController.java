package com.globallogic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dto.StudentDto;
import com.globallogic.service.StudentService;
import com.globallogic.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping
	public List<StudentDto> getAllStudents() {

		return studentService.getAllStudents();
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto) {

		return new ResponseEntity<StudentDto>(studentService.addStudent(studentDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable(value = AppConstants.ID) long id) {

		return ResponseEntity.ok(studentService.getStudentById(id));
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable(value = AppConstants.ID) long id,
			@Valid @RequestBody StudentDto studentDto) {

		return new ResponseEntity<StudentDto>(studentService.updateStudent(id, studentDto), HttpStatus.OK);
	}

	@PreAuthorize(value = "hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable(value = AppConstants.ID) long id) {

		studentService.deleteStudentById(id);

		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
	}

}
