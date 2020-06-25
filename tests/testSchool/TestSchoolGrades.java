package testSchool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import school.Discipline;
import school.SchoolGrades;
import systemExceptions.DisciplineNotFoundException;

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
	
	
	
}
