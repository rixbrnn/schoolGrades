package testSchool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import school.Discipline;
import school.SchoolGrades;
import school.Student;
import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.DisciplineNotFoundException;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidGradeException;
import systemExceptions.InvalidStudentParametersException;

class TestSchoolGrades {
	private static SchoolGrades sG = new SchoolGrades();

	@Test
	void testGetDisciplineException() {

		Assertions.assertThrows(DisciplineNotFoundException.class, () -> {
			sG.getDiscipline(10);
		});

	}

	@Test
	void testHasDiscipline() {
		Discipline d = new Discipline("Lab2", 1);
		Discipline d2 = new Discipline("Prog2", 1);
		sG.addDiscipline(d);
		sG.addDiscipline(d2);
		assertEquals(1, sG.getNumOfDisciplines());
	}

	@Test
	void testRemoveDisciplineFalse() {
		Assertions.assertThrows(DisciplineNotFoundException.class, () -> {
			sG.removeDiscipline(10);
		});
	}

	@Test
	void testRemoveDisciplineTrue() {
		try {
			assertEquals(true, sG.removeDiscipline(1));
		} catch (DisciplineNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetAverageOfAllDisciplines() {
		sG.addDiscipline((new Discipline("Prog2", 99)));
		Student std;
		try {
			std = new Student("Gabriel", 99999);
			std.setGradeA(10);
			std.setGradeB(10);
			sG.addStudentToDiscipline(std, 99);

			assertEquals(10, sG.getAverageOfAllDisciplines());
		} catch (InvalidStudentParametersException | InvalidCodeException | DisciplineNotFoundException
				| DuplicatedCodeException | InvalidGradeException | DisciplineIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
