package userInterfaceMenu;

import java.io.File;

import school.*;
import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.DisciplineNotFoundException;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidGradeException;
import systemExceptions.InvalidOptionException;
import systemExceptions.InvalidStudentParametersException;
import systemExceptions.StudentNotFoundException;

public interface MenuUI {
	int showMenu() throws InvalidOptionException;

	void registerStudent(SchoolGrades sG) throws InvalidStudentParametersException, DuplicatedCodeException,
			DisciplineNotFoundException, InvalidCodeException, InvalidGradeException, DisciplineIsEmptyException;

	void registerDiscipline(SchoolGrades sG);

	void registerGrade(SchoolGrades sG) throws InvalidGradeException, StudentNotFoundException,
			DisciplineIsEmptyException, DisciplineNotFoundException;

	void showStudentFinalGrade(SchoolGrades sG) throws DisciplineNotFoundException, StudentNotFoundException, DisciplineIsEmptyException;

	void showDisciplineFinalGrades(SchoolGrades sG) throws DisciplineNotFoundException;

	void studentListFromDiscipline(SchoolGrades sG) throws DisciplineNotFoundException;

	void studentListFromSchool(SchoolGrades sG);
	
	void registerFromFile(File file);
	
	void saveToFile(File file);
}
