package testSchool;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import school.Student;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidStudentParametersException;

public class TestStudent {
	Student s;
	
	@Test(expected = InvalidCodeException.class)
	public void testConstructorException() throws InvalidStudentParametersException, InvalidCodeException {
				Student s = new Student("Gabriel", 123);
				fail();			
	}
	
	@Test
	public void testConstructor() {
		try {
			s = new Student("Gabriel", 99999);
		} catch (InvalidStudentParametersException | InvalidCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
