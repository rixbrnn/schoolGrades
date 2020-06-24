package userInterfaceMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import school.*;
import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.DisciplineNotFoundException;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidGradeException;
import systemExceptions.InvalidOptionException;
import systemExceptions.InvalidStudentParametersException;
import systemExceptions.StudentNotFoundException;

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
		System.out.println("3 - Files");
		System.out.println("0 - Exit");
		System.out.println("=============================");
		System.out.print("Enter your option: ");

		int option = SCAN.nextInt();

		if (option < 0 || option > 2)
			throw new InvalidOptionException();

		return option;
	}

	public int selectOption(int op) throws InvalidOptionException {
		switch (op) {
		case 1:
			System.out.println("\n1 - Register Discipline");
			System.out.println("2 - Register Student");
			System.out.println("3 - Register Student's Grades");
			System.out.print("Enter your option: ");

			int option = SCAN.nextInt();

			if (option < 1 || option > 3)
				throw new InvalidOptionException();

			return option + op;

		case 2:
			System.out.println("\n1 - Show Student's Grades");
			System.out.println("2 - Show Discipline's Grades");
			System.out.println("3 - Show Discipline's Students");
			System.out.println("4 - Show All School's Students List");
			System.out.print("Enter your option: ");
			
			int option2 = SCAN.nextInt();

			if (option2 < 1 || option2 > 4)
				throw new InvalidOptionException();

			return option2 + op + difOPTION;
			
		case 3:
			System.out.println("\n1 - Register Disciplines From File");
			System.out.println("2 - Save Disciplines To File");
			System.out.println("3 - Register Students From File");
			System.out.println("4 - Save Students To File");
			System.out.print("Enter your option: ");

			int option3 = SCAN.nextInt();

			if (option3 < 1 || option3 > 4)
				throw new InvalidOptionException();
			
			return option3 + op + difFILE;

		case 0:
			System.out.println("\n1 - Confirm Exit");
			System.out.println("0 - Cancel");
			System.out.print("Enter your option: ");

			int option0 = SCAN.nextInt();

			if (option0 < 0 || option0 > 1)
				throw new InvalidOptionException();
			
			if(option0 == 1)
				System.out.println("System closed.");
			
			return option0 + op;

		default:
			throw new InvalidOptionException();
		}
	}

	@Override
	public void registerDiscipline(SchoolGrades sG) {
		System.out.print("Enter discipline name: ");
		String name = SCAN.next();
		System.out.print("Enter discipline code: ");
		int code = SCAN.nextInt();

		Discipline d = new Discipline(name, code);

		sG.addDiscipline(d);
	}

	@Override
	public void registerStudent(SchoolGrades sG) throws InvalidStudentParametersException, DuplicatedCodeException,
			DisciplineNotFoundException, InvalidCodeException, InvalidGradeException, DisciplineIsEmptyException {
		System.out.print("Enter student name: ");
		String name = SCAN.next();
		System.out.print("Enter student code: ");
		int code = SCAN.nextInt();

		Student s = new Student(name, code);

		System.out.println("Enter the discipline code: ");
		int dCode = SCAN.nextInt();

		sG.getDiscipline(dCode).addStudent(s);

		registerGradesNewStudent(s);
	}

	private void registerGradesNewStudent(Student s) throws InvalidGradeException {
		System.out.println("Do you wish to register " + s.getName().toUpperCase() + " grades?");
		System.out.println("Enter 1 to Register or 0 to Exit");
		System.out.print("Your option: ");
		int option = SCAN.nextInt();

		if (option == 1) {
			registerGrade(s);
		}
	}

	private void registerGrade(Student s) throws InvalidGradeException {
		System.out.print("Enter Grade A: ");
		int gA = SCAN.nextInt();
		System.out.print("Enter Grade B: ");
		int gB = SCAN.nextInt();

		s.setGradeA(gA);
		s.setGradeB(gB);
	}

	@Override
	public void registerGrade(SchoolGrades sG) throws InvalidGradeException, StudentNotFoundException,
			DisciplineIsEmptyException, DisciplineNotFoundException {
		System.out.print("Enter Student's discipline code: ");
		int disciplineCode = SCAN.nextInt();
		Discipline result = sG.getDiscipline(disciplineCode);

		System.out.print("Enter Student's code: ");
		int studentCode = SCAN.nextInt();
		Student resultS = result.getStudent(studentCode);

		System.out.print("Enter Grade A: ");
		int gA = SCAN.nextInt();
		System.out.print("Enter Grade B: ");
		int gB = SCAN.nextInt();

		resultS.setGradeA(gA);
		resultS.setGradeB(gB);
	}

	@Override
	public void studentListFromDiscipline(SchoolGrades sG) throws DisciplineNotFoundException {
		System.out.print("Enter the discipline code: ");
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

	}

	@Override
	public void studentListFromSchool(SchoolGrades sG) {
		ArrayList<Student> result = sG.getAllStudents();
		System.out.println("=============================");
		System.out.printf("%27S", "All School Students List");
		System.out.println("\n=============================");
		for (Student i : result) {
			System.out.println(i);
		}
		System.out.println("=============================");
	}

	@Override
	public void showStudentFinalGrade(SchoolGrades sG) throws DisciplineNotFoundException, StudentNotFoundException, DisciplineIsEmptyException {
		System.out.print("Enter the discipline's code: ");
		int disciplineCode = SCAN.nextInt();
		Discipline discipline = sG.getDiscipline(disciplineCode);
		
		System.out.print("Enter the student's code: ");
		int studentCode = SCAN.nextInt();
		
		System.out.println("=============================");
		System.out.println(discipline.getStudent(studentCode).showStudentFinalGrade());
		System.out.println("=============================");

	}

	@Override
	public void showDisciplineFinalGrades(SchoolGrades sG) throws DisciplineNotFoundException {
		System.out.print("Enter the discipline code: ");
		int disciplineCode = SCAN.nextInt();
		Discipline discipline = sG.getDiscipline(disciplineCode);

		ArrayList<Student> result = discipline.getStudents();

		System.out.println("=============================");
		System.out.printf("%20S", "All " + discipline.getName() + " Student's Final Grades");
		System.out.println("\n=============================");
		for (Student i : result) {
			System.out.println(i.showStudentFinalGrade());
		}
		System.out.println("=============================");
	}

	@Override
	public void registerFromFile(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(File file) {
		// TODO Auto-generated method stub
		
	}
	
}
