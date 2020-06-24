package testSchool;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import school.Discipline;
import school.Student;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidStudentParametersException;

class TestDiscipline {
	private Discipline d = new Discipline("Lab2", 1);

	@Test(expected = DuplicatedCodeException.class)
	public void addStudentE() throws InvalidStudentParametersException, InvalidCodeException, DuplicatedCodeException {
		Student s = new Student("Gabriel", 99999);
		Student s1 = new Student("Richard", 99999);
		d.addStudent(s);
		d.addStudent(s1);
		fail();
	}

}
