package dataFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import school.*;
import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidStudentParametersException;

public class SchoolGradesIO {

	final static String DATA_FILE_NAME = "school_data.txt";

	public static boolean saveSchoolGrades(SchoolGrades school) {
		ArrayList<Discipline> dis = school.getDisciplines();
		File data = new File(SchoolGradesIO.DATA_FILE_NAME);
		try {
			if (data.exists())
				data.delete();
			data.createNewFile();

			FileWriter fw = new FileWriter(data);
			/**
			 * MARKS: @ DISCIPLINE NAME : DISCIPLINE CODE $ STUDENT NAME # STUDENT CODE &
			 * STUDENT GRADE A % STUDENT GRADE B NEXT DISCIPLINE
			 */
			for (Discipline i : dis) {
				fw.write(i.getName() + "@\n" + i.getCode() + ":\n");
				for (Student j : i.getStudents()) {
					fw.write(j.getName() + "$\n" + j.getCode() + "#\n" + j.getGradeA() + "&\n" + j.getGradeB() + "%\n");
				}
				fw.write("*");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static SchoolGrades readSchoolGrades()
			throws IOException, InvalidStudentParametersException, InvalidCodeException, DisciplineIsEmptyException {
		{
			InputStream is = new FileInputStream(SchoolGradesIO.DATA_FILE_NAME);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String s = br.readLine();
			SchoolGrades school = new SchoolGrades();

			String disName = null;
			int disCode = 0;
			String stName = null;
			int stCode = 0;
			Double gA = 0.0;
			Double gB = 0.0;
			/**
			 * MARKS: @ DISCIPLINE NAME : DISCIPLINE CODE $ STUDENT NAME # STUDENT CODE &
			 * STUDENT GRADE A % STUDENT GRADE B NEXT DISCIPLINE
			 */

			while (s != null) {
				if (s.contains("@")) {
					disName = s.replace("@", "");
				}
				if (s.contains(":")) {
					disCode = Integer.parseInt(s.replace(":", ""));
					school.addDiscipline(new Discipline(disName, disCode));
				}
				if (s.contains("$")) {
					stName = s.replace("$", "");
				}
				if (s.contains("#")) {
					stCode = Integer.parseInt(s.replace("#", ""));
				}
				if (s.contains("&")) {
					gA = Double.parseDouble(s.replace("&", ""));
				}
				if (s.contains("%")) {
					gB = Double.parseDouble(s.replace("%", ""));
					System.out.println(stName);
					System.out.println(stCode);
					System.out.println(gA);
					System.out.println(gB);
					Student st = new Student(stName, stCode);
					school.addStudentToDiscipline(st, disCode);
				}
				s = br.readLine();
			}
			if (school.getDisciplines().isEmpty()) {
				br.close();
				throw new IOException();
			}

			br.close();
			return school;
		}
	}

}
