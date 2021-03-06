package main;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

import school.*;
import systemExceptions.*;
import userInterfaceMenu.Menu;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();

		SchoolGrades school = new SchoolGrades();

		int option = 0, selectOption = 0;

		do {
			try {
				option = menu.showMenu();
				selectOption = menu.selectOption(option);
			} catch (InvalidOptionException e) {
				System.out.println("\n" + e);
			}

			switch (selectOption) {

			case 0:
				break;

			case 1:

				option = -1;
				break;

			case 2:
				try {
					menu.registerDiscipline(school);
				} catch (InputMismatchException e) {
					System.out.println("\n" + e);
				}
				break;

			case 3:

				try {
					menu.registerStudent(school);
				} catch (InputMismatchException | InvalidStudentParametersException | DuplicatedCodeException
						| DisciplineNotFoundException | InvalidCodeException | InvalidGradeException
						| DisciplineIsEmptyException e) {
					System.out.println("\n" + e);
				}
				break;

			case 4:

				try {
					menu.registerGrade(school);
				} catch (InputMismatchException | InvalidGradeException | StudentNotFoundException
						| DisciplineIsEmptyException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 5:

				try {
					menu.showStudent(school);
				} catch (InputMismatchException | DisciplineNotFoundException | StudentNotFoundException
						| DisciplineIsEmptyException e) {
					System.out.println("\n" + e);
				}
				break;

			case 6:

				menu.showDisciplines(school);
				break;

			case 7:

				try {
					menu.showDisciplineFinalGrades(school);
				} catch (InputMismatchException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 8:

				try {
					menu.studentListFromDiscipline(school);
				} catch (InputMismatchException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 9:

				menu.studentListFromSchool(school);
				break;

			case 14:

				try {
					String path = menu.setFilePath(selectOption);
					menu.loadDisciplines(school, new File(path));
				} catch (InputMismatchException | InvalidOptionException | NumberFormatException
						| IndexOutOfBoundsException | IOException e) {
					System.out.println("\n" + e);
				}
				break;

			case 15:
				try {
					String path = menu.setFilePath(selectOption);
					menu.loadStudents(school, new File(path));
				} catch (InputMismatchException | InvalidOptionException | NumberFormatException
						| IndexOutOfBoundsException | IOException | InvalidStudentParametersException
						| InvalidCodeException | DisciplineIsEmptyException | DisciplineNotFoundException
						| DuplicatedCodeException e) {
					System.out.println("\n" + e);
				}
				break;

			case 16:
				try {
					String path = menu.setFilePath(selectOption);
					menu.loadStudentsGrades(school, new File(path));
				} catch (InputMismatchException | InvalidOptionException | NumberFormatException
						| IndexOutOfBoundsException | IOException | InvalidGradeException | StudentNotFoundException
						| DisciplineIsEmptyException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 17:

				try {
					String path = menu.setFilePath(selectOption);
					menu.saveDisciplinesToFile(school, new File(path));
				} catch (InputMismatchException | InvalidOptionException | IOException e) {
					System.out.println("\n" + e);
				}
				break;

			case 18:

				try {
					String path = menu.setFilePath(selectOption);
					menu.saveStudentsToFile(school, new File(path));
				} catch (IOException | InvalidOptionException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 19:

				try {
					String path = menu.setFilePath(selectOption);
					menu.saveAllToFile(school, new File(path));
				} catch (IOException | InvalidOptionException | InputMismatchException e) {
					System.out.println("\n" + e);
				}
				break;

			default:
				break;

			}
		} while (option != -1);
	}
}
