package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);

//			readStudent(studentDAO);

//			queryForStudents (studentDAO);

//			queryForStudentsByLastName (studentDAO);

//			updateStudent(studentDAO);

//			deleteStudent(studentDAO);

			deleteAllStudents(studentDAO);
		};
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// Get list of students
		List<Student> students = studentDAO.findByLastName("Doe");

		//Display list of students
		for (Student student: students) {
			System.out.println(student);
		}
	}

	private void queryForStudents (StudentDAO studentDAO) {
		// Get list of students
		List<Student> students = studentDAO.findAll();

//		Display list of students
		for (Student student: students) {
			System.out.println(student);
		}
	}

	private void readStudent (StudentDAO studentDAO) {
		// Create a Student Object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student ("Daffy", "Duck", "daffy@gmail.com");

		// Save the Student
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// Display id of the save student
		int theId = tempStudent.getId();
		System.out.println("Saved student, generated id: " +theId);

		// Retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.find(theId);

		// Display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Malindu", "Nawarathne", "malindu@gmail.com");

		// save the student object
		studentDAO.save(tempStudent);

		// display id of the  saved student
		System.out.println("Saved student Generated id: " + tempStudent.getId());
	}

	private void updateStudent (StudentDAO studentDAO) {
		// Retrieve Student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student theStudent = studentDAO.find(studentId);

		// Change last name to "Nawarathne"
		System.out.println("Updating student ...");
		theStudent.setLastName("Nawarathne");

		// Update the student
		studentDAO.update(theStudent);

		// display the updated student
		System.out.println("Updated student: " + theStudent);
	}

	public void deleteStudent (StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	public void deleteAllStudents (StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted Rows Count: " + numRowsDeleted);
	}
}
