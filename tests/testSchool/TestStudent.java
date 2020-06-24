package testSchool;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import school.Student;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidGradeException;
import systemExceptions.InvalidStudentParametersException;

public class TestStudent {
	private Student s;
	
	@Test(expected = InvalidCodeException.class)
	public void testConstructorException() throws InvalidStudentParametersException, InvalidCodeException {
				Student s = new Student("Gabriel", 123);
				fail();			
	}
	
	@Before
	public void testConstructor() {
		try {
			s = new Student("Gabriel", 99999);
		} catch (InvalidStudentParametersException | InvalidCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetName() {
		String name = s.getName();
		assertEquals("Gabriel", name);
	}
	
	@Test
	public void testGetCode() {
		int code = s.getCode();
		assertEquals(99999, code);
	}
	
	@Test
	public void testGetGrades() {
		double gA = s.getGradeA();
		double gB = s.getGradeB();
		assertEquals(-1, gA);
		assertEquals(-1, gA);
	}
	
	@Test(expected = InvalidGradeException.class)
	public void testSetGradeException() throws InvalidGradeException {
		s.setGradeA(-1);
		//setGradeB is the same
	}
	
	@Test
	public void testSetGRades() {
		try {
			s.setGradeA(8);
		} catch (InvalidGradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAverageGrade0() throws InvalidGradeException {
		double fG = s.averageGrade();
		assertEquals(-1, fG);
	}
	
	@Test
	public void testAverageGradeA() throws InvalidGradeException {
		s.setGradeA(10);
		double fG = s.averageGrade();
		assertEquals(10, fG);
	}
	
	@Test
	public void testAverageGradeFull() throws InvalidGradeException {
		s.setGradeB(10);
		s.setGradeA(5);
		double fG = s.averageGrade();
		assertEquals(8.333333333333334, fG);
	}
	
	
}
