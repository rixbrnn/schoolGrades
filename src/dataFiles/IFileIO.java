package dataFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import school.*;
import systemExceptions.*;

public interface IFileIO {
	void loadDisciplines(SchoolGrades sG, File file) throws FileNotFoundException, IOException;

	void loadStudents(SchoolGrades sG, File file) throws NumberFormatException, IOException,
			InvalidStudentParametersException, InvalidCodeException, DisciplineIsEmptyException;

	void loadStudentsGrade(SchoolGrades sG, File file) throws FileNotFoundException, IOException, NumberFormatException,
			InvalidGradeException, StudentNotFoundException, DisciplineIsEmptyException, DisciplineNotFoundException;

	void saveDisciplines(SchoolGrades sG, File file) throws IOException;

	void saveStudents(SchoolGrades sG, int disCode, File file) throws IOException, DisciplineNotFoundException;

	void saveAll(SchoolGrades sG, File file) throws IOException;
}
