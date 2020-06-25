package userInterfaceMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dataFiles.SchoolGradesIO;
import school.*;
import systemExceptions.*;

public class Menu implements MenuUI {
	private static final Scanner SCAN = new Scanner(System.in);
	private static final int difOPTION = 2, difFILE = 10;

	@Override
	public int showMenu() throws InvalidOptionException {
		System.out.println("=============================");
		System.out.printf("%15S%n", "menu");
		System.out.println("=============================");
		System.out.println("1 - Register");
		System.out.println("2 - Show");
		System.out.println("3 - Load/Save");
		System.out.println("0 - Exit");
		System.out.println("=============================");
		System.out.print("Enter your option: ");

		int option;

		try {
			option = SCAN.nextInt();
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InvalidOptionException();
		}

		if (option < 0 || option > 3)
			throw new InvalidOptionException();

		return option;
	}

	public int selectOption(int op) throws InvalidOptionException, InputMismatchException {
		switch (op) {
		case 1:
			System.out.println("\n1 - Register Discipline");
			System.out.println("2 - Register Student");
			System.out.println("3 - Register Student's Grades");
			System.out.print("Enter your option: ");

			int option;

			try {
				option = SCAN.nextInt();
			} catch (InputMismatchException e) {
				SCAN.next();
				throw new InvalidOptionException();
			}
			if (option < 1 || option > 3)
				throw new InvalidOptionException();

			return option + op;

		case 2:
			System.out.println("\n1 - Show Student's Info");
			System.out.println("2 - Show Discipline's Info");
			System.out.println("3 - Show Discipline's Grades");
			System.out.println("4 - Show Discipline's Students");
			System.out.println("5 - Show All School's Students");
			System.out.print("Enter your option: ");

			int option2;

			try {
				option2 = SCAN.nextInt();
			} catch (InputMismatchException e) {
				SCAN.next();
				throw new InvalidOptionException();
			}

			if (option2 < 1 || option2 > 5)
				throw new InvalidOptionException();

			return option2 + op + difOPTION;

		case 3:
			System.out.println("\n1 - Register Disciplines From File");
			System.out.println("2 - Register Students From File");
			System.out.println("3 - Register Students Grades From File");
			System.out.println("4 - Save Disciplines To File");
			System.out.println("5 - Save Students From Discipline To File");
			System.out.println("6 - Save All to File");
			System.out.print("Enter your option: ");

			int option3;

			try {
				option3 = SCAN.nextInt();
			} catch (InputMismatchException e) {
				SCAN.next();
				throw new InvalidOptionException();
			}

			if (option3 < 1 || option3 > 6)
				throw new InvalidOptionException();

			return option3 + op + difFILE;

		case 0:
			System.out.println("\n1 - Confirm Exit");
			System.out.println("0 - Cancel");
			System.out.print("Enter your option: ");

			int option0;

			try {
				option0 = SCAN.nextInt();
			} catch (InputMismatchException e) {
				SCAN.next();
				throw new InvalidOptionException();
			}

			if (option0 < 0 || option0 > 1)
				throw new InvalidOptionException();

			if (option0 == 1)
				System.out.println("System closed.");

			return option0 + op;

		default:
			throw new InvalidOptionException();
		}
	}

	@Override
	public void registerDiscipline(SchoolGrades sG) throws InputMismatchException {
		int code;
		String name;

		try {
			System.out.print("Enter discipline's name: ");
			name = SCAN.next();
			System.out.print("Enter discipline's code: ");
			code = SCAN.nextInt();

			Discipline d = new Discipline(name, code);

			sG.addDiscipline(d);
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	@Override
	public void registerStudent(SchoolGrades sG)
			throws InvalidStudentParametersException, DuplicatedCodeException, DisciplineNotFoundException,
			InvalidCodeException, InvalidGradeException, DisciplineIsEmptyException, InputMismatchException {

		try {
			System.out.print("Enter student's name: ");
			String name = SCAN.next();
			System.out.print("Enter student's code: ");
			int code = SCAN.nextInt();

			Student s = new Student(name, code);

			System.out.println("Enter the discipline's code: ");
			int dCode = SCAN.nextInt();

			sG.getDiscipline(dCode).addStudent(s);

			registerGradesNewStudent(s);
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	private void registerGradesNewStudent(Student s) throws InvalidGradeException, InputMismatchException {
		try {
			System.out.println("Do you wish to register " + s.getName().toUpperCase() + " grades?");
			System.out.println("Enter 1 to Register or 0 to Exit");
			System.out.print("Your option: ");
			int option = SCAN.nextInt();

			if (option == 1) {
				registerGrade(s);
			}
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	private void registerGrade(Student s) throws InvalidGradeException, InputMismatchException {
		try {
			System.out.print("Enter Grade A: ");
			double gA = SCAN.nextDouble();
			System.out.print("Enter Grade B: ");
			double gB = SCAN.nextDouble();

			s.setGradeA(gA);
			s.setGradeB(gB);
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	@Override
	public void registerGrade(SchoolGrades sG) throws InvalidGradeException, StudentNotFoundException,
			DisciplineIsEmptyException, DisciplineNotFoundException, InputMismatchException {
		try {
			System.out.print("Enter Student's discipline code: ");
			int disciplineCode = SCAN.nextInt();
			Discipline result = sG.getDiscipline(disciplineCode);

			System.out.print("Enter Student's code: ");
			int studentCode = SCAN.nextInt();
			Student resultS = result.getStudent(studentCode);

			System.out.print("Enter Grade A: ");
			double gA = SCAN.nextDouble();
			System.out.print("Enter Grade B: ");
			double gB = SCAN.nextDouble();

			resultS.setGradeA(gA);
			resultS.setGradeB(gB);
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	@Override
	public void showDisciplines(SchoolGrades sG) {
		System.out.println("=============================");
		System.out.printf("%26s%n", "Disciplines List");
		for (Discipline i : sG.getDisciplines()) {
			System.out.println(i + "\n");
		}
		System.out.println("=============================");
	}

	@Override
	public void showStudent(SchoolGrades sG) throws DisciplineNotFoundException, StudentNotFoundException,
			DisciplineIsEmptyException, InputMismatchException {
		try {
			System.out.print("Enter the discipline's code: ");
			int disciplineCode = SCAN.nextInt();
			Discipline discipline = sG.getDiscipline(disciplineCode);

			System.out.print("Enter the student's code: ");
			int studentCode = SCAN.nextInt();

			System.out.println("=============================");
			System.out.println(discipline.getStudent(studentCode));
			System.out.println("=============================");
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	@Override
	public void studentListFromDiscipline(SchoolGrades sG) throws DisciplineNotFoundException, InputMismatchException {
		try {
			System.out.print("Enter the discipline's code: ");
			int disciplineCode = SCAN.nextInt();
			Discipline discipline = sG.getDiscipline(disciplineCode);

			ArrayList<Student> result = discipline.getStudents();

			System.out.println("=============================");
			System.out.printf("%20S", "All " + discipline.getName() + " Students List");
			System.out.println("\n=============================");
			for (Student i : result) {
				System.out.println(i);
			}
			System.out.println("=============================");
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	@Override
	public void showDisciplineFinalGrades(SchoolGrades sG) throws DisciplineNotFoundException, InputMismatchException {
		try {
			System.out.print("Enter the discipline's code: ");
			int disciplineCode = SCAN.nextInt();
			Discipline discipline = sG.getDiscipline(disciplineCode);

			ArrayList<Student> result = discipline.getStudents();

			System.out.println("=============================");
			System.out.printf("%20S", "All " + discipline.getName() + " Students Final Grades");
			System.out.println("\n=============================");
			for (Student i : result) {
				System.out.println(i.showStudentFinalGrade());
			}
			System.out.println("=============================");
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	@Override
	public void studentListFromSchool(SchoolGrades sG) {
		System.out.println("=============================");
		System.out.printf("%27S", "All School Students List");
		System.out.println("\n=============================");

		for(Discipline d : sG.getDisciplines()) {
			System.out.println(d);
			System.out.println("-----------------------------");
			for(Student s : d.getStudents()) {
				System.out.println(s);
			}
			System.out.println("\n-----------------------------");
		}
		System.out.println("=============================");
	}

	@Override
	public void loadDisciplines(SchoolGrades sG, File file)
			throws NumberFormatException, IOException, IndexOutOfBoundsException {
		SchoolGradesIO sIO = new SchoolGradesIO();
		sIO.loadDisciplines(sG, file);
	}

	@Override
	public void loadStudents(SchoolGrades sG, File file)
			throws NumberFormatException, IOException, InvalidStudentParametersException, InvalidCodeException,
			DisciplineIsEmptyException, IndexOutOfBoundsException, DisciplineNotFoundException, DuplicatedCodeException {
		SchoolGradesIO sIO = new SchoolGradesIO();
		sIO.loadStudents(sG, file);
	}

	@Override
	public void loadStudentsGrades(SchoolGrades sG, File file) throws NumberFormatException, FileNotFoundException,
			IOException, InvalidGradeException, StudentNotFoundException, DisciplineIsEmptyException,
			DisciplineNotFoundException, IndexOutOfBoundsException {
		SchoolGradesIO sIO = new SchoolGradesIO();
		sIO.loadStudentsGrade(sG, file);
	}

	@Override
	public void saveDisciplinesToFile(SchoolGrades sG, File file) throws IOException {
		SchoolGradesIO sIO = new SchoolGradesIO();
		sIO.saveDisciplines(sG, file);
	}

	public void saveStudentsToFile(SchoolGrades sG, File file) throws IOException, DisciplineNotFoundException {
		try {
			System.out.print("Enter the discipline's code: ");
			int disciplineCode = SCAN.nextInt();

			SchoolGradesIO sIO = new SchoolGradesIO();
			sIO.saveStudents(sG, disciplineCode, file);
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InputMismatchException();
		}
	}

	public void saveAllToFile(SchoolGrades sG, File file) throws IOException {
		SchoolGradesIO sIO = new SchoolGradesIO();
		sIO.saveAll(sG, file);
	}

	@Override
	public String setFilePath(int option) throws InvalidOptionException {

		if (option == 14) {
			System.out.println("=============================");
			System.out.println("Warning. File example:");
			System.out.println("disciplineName; disciplineCode");
			System.out.println("Path example:");
			System.out.println("C:\\Users\\myUser\\Desktop\\RegisterDisciplines.txt");
			System.out.println("=============================");
			System.out.print("Enter file path: ");
		} else if (option == 15) {
			System.out.println("=============================");
			System.out.println("Warning. File example:");
			System.out.println("disciplineCode;studentName;studentCode");
			System.out.println("Path example:");
			System.out.println("C:\\Users\\myUser\\Desktop\\RegisterStudents.txt");
			System.out.println("=============================");
			System.out.print("Enter file path: ");
		} else if (option == 16) {
			System.out.println("=============================");
			System.out.println("Warning. File example:");
			System.out.println("disciplineCode;studentCode;gradeA;gradeB");
			System.out.println("Path example:");
			System.out.println("C:\\Users\\myUser\\Desktop\\RegisterStudentsGrades.txt");
			System.out.println("=============================");
			System.out.print("Enter file path: ");
		} else if (option == 17 || option == 18 || option == 19) {
			System.out.println("=============================");
			System.out.println("Path example:");
			System.out.println("C:\\Users\\myUser\\Desktop\\outputFile.txt");
			System.out.println("=============================");
			System.out.print("Enter file path: ");
		}

		String path;

		try {
			path = SCAN.next();
		} catch (InputMismatchException e) {
			SCAN.next();
			throw new InvalidOptionException();
		}
		return path;
	}

}
