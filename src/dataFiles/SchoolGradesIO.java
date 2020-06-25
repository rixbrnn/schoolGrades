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
	public void loadDisciplines(SchoolGrades sG, File file)
			throws FileNotFoundException, IOException, IndexOutOfBoundsException {

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
	public void loadStudents(SchoolGrades sG, File file)
			throws NumberFormatException, IOException, InvalidStudentParametersException, InvalidCodeException,
			DisciplineIsEmptyException, IndexOutOfBoundsException, DisciplineNotFoundException, DuplicatedCodeException {

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

	@Override
	public void loadStudentsGrade(SchoolGrades sG, File file) throws FileNotFoundException, IOException,
			NumberFormatException, InvalidGradeException, StudentNotFoundException, DisciplineIsEmptyException,
			DisciplineNotFoundException, IndexOutOfBoundsException {

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
			bw.write("DisciplineName; DisciplineCode; NumberOfStudents\n");
			bw.newLine();

			for (Discipline d : sG.getDisciplines()) {
				bw.write(d.toString());
				bw.newLine();
			}
			bw.write("\n=======================================================");
		}
	}

	@Override
	public void saveStudents(SchoolGrades sG, int disCode, File file) throws IOException, DisciplineNotFoundException {
		String line = "=======================================================";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("\n\t\t\t\t\t");
			bw.write(sG.getDiscipline(disCode).getName());
			bw.write("'s Students List\n");
			bw.write(line + line + "\n");
			bw.write("StudentName; StudentCode; GradeA; GradeB; FinalGrade\n");
			bw.newLine();

			for (Student s : sG.getDiscipline(disCode).getStudents()) {
				bw.write(s.toString());
				bw.newLine();
			}
			bw.write("\n" + line + line);
		}
	}

	@Override
	public void saveAll(SchoolGrades sG, File file) throws IOException {
		String line = "=======================================================";
		String line2 = "-------------------------------------------------------";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("\n\t\t\t\t\tSchool's Full Info List\n");
			bw.write(line + line + "\n");
			bw.newLine();

			bw.write(line2 + line2 + "\n");
			for (Discipline d : sG.getDisciplines()) {
				bw.write(d.toString());
				bw.write("\n" + line2 + line2 + "\n");
				for (Student s : d.getStudents()) {
					bw.write("\t" + s.toString());
					bw.newLine();
				}
				bw.write("\n" + line2 + line2 + "\n");
			}
			bw.write(line + line);
		}
	}
}
