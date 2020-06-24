package userInterfaceMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import school.*;
import systemExceptions.*;

public interface MenuUI {
	int showMenu() throws InvalidOptionException;

	void registerStudent(SchoolGrades sG)
			throws InvalidStudentParametersException, DuplicatedCodeException, DisciplineNotFoundException,
			InvalidCodeException, InvalidGradeException, DisciplineIsEmptyException, InputMismatchException;

	void registerDiscipline(SchoolGrades sG);

	void registerGrade(SchoolGrades sG) throws InvalidGradeException, StudentNotFoundException,
			DisciplineIsEmptyException, DisciplineNotFoundException, InputMismatchException;

	void showStudentGrades(SchoolGrades sG) throws DisciplineNotFoundException, StudentNotFoundException,
			DisciplineIsEmptyException, InputMismatchException;

	void showDisciplineFinalGrades(SchoolGrades sG) throws DisciplineNotFoundException, InputMismatchException;

	void studentListFromDiscipline(SchoolGrades sG) throws DisciplineNotFoundException, InputMismatchException;

	void studentListFromSchool(SchoolGrades sG);

	void loadDisciplines(SchoolGrades sG, File file) throws NumberFormatException, IOException;

	void loadStudents(SchoolGrades sG, File file) throws NumberFormatException, IOException,
			InvalidStudentParametersException, InvalidCodeException, DisciplineIsEmptyException;

	void loadStudentsGrades(SchoolGrades sG, File file)
			throws NumberFormatException, FileNotFoundException, IOException, InvalidGradeException,
			StudentNotFoundException, DisciplineIsEmptyException, DisciplineNotFoundException;

	void saveDisciplinesToFile(SchoolGrades sG, File file) throws IOException;
	
	void saveStudentsToFile(SchoolGrades sG, File file) throws IOException, DisciplineNotFoundException;
	
	void saveAllToFile(SchoolGrades sG, File file) throws IOException;
}
