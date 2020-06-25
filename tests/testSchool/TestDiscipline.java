package testSchool;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import school.Discipline;
import school.Student;
import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidGradeException;
import systemExceptions.InvalidStudentParametersException;
import systemExceptions.StudentNotFoundException;

class TestDiscipline {

	@Test
	void addStudentException() {

		Assertions.assertThrows(DuplicatedCodeException.class, () -> {
			Discipline d = new Discipline("Lab2", 1);
			Student s1 = new Student("Richard", 99999);
			d.addStudent(s1);
			Student s2 = new Student("Gabriel", 99999);
			d.addStudent(s2);
		});

	}

	@Test
	public void testGetName() {
		Discipline d = new Discipline("Lab2", 1);
		assertEquals("Lab2", d.getName());
	}

	@Test
	public void testGetCode() {
		Discipline d = new Discipline("Lab2", 1);
		assertEquals(1, d.getCode());
	}

	@Test
	public void testGetStudent() {
		try {
			Discipline d = new Discipline("Lab2", 1);
			Student s1 = new Student("Richard", 99999);
			d.addStudent(s1);
			Assertions.assertEquals(s1, d.getStudent(99999));
		} catch (StudentNotFoundException | InvalidStudentParametersException
				| InvalidCodeException | DuplicatedCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetStudentException() {

		Assertions.assertThrows(StudentNotFoundException.class, () -> {
			Discipline d = new Discipline("Lab2", 1);
			d.getStudent(12345);
		});

	}

	@Test
	void testGetNumStudents() {
		try {
			Discipline d = new Discipline("Lab2", 1);
			Student s1;
			s1 = new Student("Richard", 99999);
			d.addStudent(s1);
			assertEquals(1, d.getNumOfStudents());
		} catch (InvalidStudentParametersException | InvalidCodeException | DuplicatedCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testHasStudentFalse() {
		Discipline d = new Discipline("Lab2", 1);
		assertEquals(false, d.hasStudent(12345));
	}

	@Test
	void testHasStudentTrue() {
		try {
			Discipline d = new Discipline("Lab2", 1);
			Student s1;
			s1 = new Student("Richard", 99999);
			d.addStudent(s1);
			assertEquals(true, d.hasStudent(99999));
		} catch (InvalidStudentParametersException | InvalidCodeException | DuplicatedCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void averageGrade() {
		try {
			Discipline d = new Discipline("Lab2", 1);
			Student s1;
			s1 = new Student("Richard", 99999);
			s1.setGradeA(10);
			s1.setGradeB(10);
			d.addStudent(s1);
			assertEquals(10, d.averageGrade());
		} catch (InvalidStudentParametersException | InvalidCodeException | DuplicatedCodeException
				| InvalidGradeException | DisciplineIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
