package com.globallogic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.dto.StudentDto;
import com.globallogic.entity.Student;
import com.globallogic.exception.ResourceNotFoundException;
import com.globallogic.repository.StudentRepository;
import com.globallogic.service.StudentService;
import com.globallogic.utils.AppConstants;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<StudentDto> getAllStudents() {

		List<Student> students = studentRepository.findAll();

		return students.stream().map((student) -> mapToDto(student)).collect(Collectors.toList());
	}

	@Override
	public StudentDto addStudent(StudentDto studentDto) {

		Student student = mapToEntity(studentDto);

		Student newStudent = studentRepository.save(student);

		return mapToDto(newStudent);
	}

	@Override
	public StudentDto getStudentById(long id) {

		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND_MESSAGE, id));
		;

		return mapToDto(student);
	}

	@Override
	public StudentDto updateStudent(long id, StudentDto studentDto) {

		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND_MESSAGE, id));

		if (studentDto.getName() != null) {
			student.setName(studentDto.getName());
		}

		if (studentDto.getFees() != 0) {

			student.setFees(studentDto.getFees());
		}

		Student updatedStudent = studentRepository.save(student);

		return mapToDto(updatedStudent);
	}

	@Override
	public void deleteStudentById(long id) {

		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND_MESSAGE, id));
		studentRepository.delete(student);

	}

	private StudentDto mapToDto(Student student) {

		StudentDto studentDto = new StudentDto();

		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setFees(student.getFees());

		return studentDto;

	}

	private Student mapToEntity(StudentDto studentDto) {

		Student student = new Student();

		student.setId(studentDto.getId());
		student.setName(studentDto.getName());
		student.setFees(studentDto.getFees());

		return student;

	}

}
