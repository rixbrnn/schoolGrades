package dataFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import school.*;
import systemExceptions.*;

public class SchoolGradesIO implements IFileIO {

	@Override
	public void loadDisciplines(SchoolGrades sG, File file) throws FileNotFoundException, IOException {

//		disciplineName;disciplineCode		

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] cols = line.split(";");

				Discipline discipline = new Discipline(cols[0], Integer.parseInt(cols[1]));

				sG.addDiscipline(discipline);
			}
		}
	}

	@Override
	public void loadStudents(SchoolGrades sG, File file) throws NumberFormatException, IOException,
			InvalidStudentParametersException, InvalidCodeException, DisciplineIsEmptyException {

// 		disciplineCode;studentName;studentCode

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] cols = line.split(";");

				Student student = new Student(cols[1], Integer.parseInt(cols[2]));

				sG.addStudentToDiscipline(student, Integer.parseInt(cols[0]));
			}
		}
	}

	public void loadStudentsGrade(SchoolGrades sG, File file)
			throws FileNotFoundException, IOException, NumberFormatException, InvalidGradeException,
			StudentNotFoundException, DisciplineIsEmptyException, DisciplineNotFoundException {

// 		disciplineCode;studentCode;gradeA;gradeB

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] cols = line.split(";");

				sG.getDiscipline(Integer.parseInt(cols[0])).getStudent(Integer.parseInt(cols[1]))
						.setGradeA(Double.parseDouble(cols[2]));
				sG.getDiscipline(Integer.parseInt(cols[0])).getStudent(Integer.parseInt(cols[1]))
						.setGradeB(Double.parseDouble(cols[3]));

			}
		}
	}

	@Override
	public void saveDisciplines(SchoolGrades sG, File file) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("\n\t\tDisciplines' List\n");
			bw.write("=======================================================\n");
			bw.newLine();

			for (Discipline d : sG.getDisciplines()) {
				bw.write(d.toString());
				bw.newLine();
			}
			bw.write("=======================================================\n");
		}
	}

	@Override
	public void saveStudents(SchoolGrades sG, int disCode, File file) throws IOException, DisciplineNotFoundException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C\\users\\gabid\\desktop\\DisciplinesOut.txt"))) {
			bw.write("\n\t\t");
			bw.write(sG.getDiscipline(disCode).getName());
			bw.write("'s Students List\n");
			bw.write("=======================================================\n");
			bw.newLine();

			for (Student s : sG.getDiscipline(disCode).getStudents()) {
				bw.write(s.toString());
				bw.newLine();
			}
			bw.write("=======================================================\n");
		}
	}

	@Override
	public void saveAll(SchoolGrades sG, File file) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C\\users\\gabid\\desktop\\DisciplinesOut.txt"))) {
			bw.write("\n\t\tSchool's Full Info List\n");
			bw.write("=======================================================\n");
			bw.newLine();

			for (Discipline d : sG.getDisciplines()) {
				bw.write(d.toString());
				bw.newLine();
				for (Student s : d.getStudents()) {
					bw.write(s.toString());
					bw.newLine();
				}
			}
			bw.write("=======================================================\n");
		}
	}
}
