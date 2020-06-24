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
				} catch (InvalidStudentParametersException | DuplicatedCodeException | DisciplineNotFoundException
						| InvalidCodeException | InvalidGradeException | DisciplineIsEmptyException e) {
					System.out.println("\n" + e);
				}
				break;

			case 4:

				try {
					menu.registerGrade(school);
				} catch (InvalidGradeException | StudentNotFoundException | DisciplineIsEmptyException
						| DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 5:

				try {
					menu.showStudentGrades(school);
				} catch (DisciplineNotFoundException | StudentNotFoundException | DisciplineIsEmptyException e) {
					System.out.println("\n" + e);
				}
				break;

			case 6:

				try {
					menu.showDisciplineFinalGrades(school);
				} catch (InputMismatchException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 7:

				try {
					menu.studentListFromDiscipline(school);
				} catch (DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 8:

				menu.studentListFromSchool(school);
				break;

			case 14:

				try {
					String path = menu.searchFilePath(selectOption);
					menu.loadDisciplines(school, new File(path));
				} catch (NumberFormatException | IOException | InvalidOptionException e) {
					System.out.println("\n" + e);
					;
				}

				break;

			case 15:
				try {
					String path = menu.searchFilePath(selectOption);
					menu.loadStudents(school, new File(path));
				} catch (InvalidOptionException | NumberFormatException | IOException
						| InvalidStudentParametersException | InvalidCodeException | DisciplineIsEmptyException e) {
					System.out.println("\n" + e);
				}

				break;

			case 16:
				try {
					String path = menu.searchFilePath(selectOption);
					menu.loadStudentsGrades(school, new File(path));
				} catch (InvalidOptionException | NumberFormatException | IOException | InvalidGradeException
						| StudentNotFoundException | DisciplineIsEmptyException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}

				break;

			case 17:

				try {
					String path = menu.searchFilePath(selectOption);
					menu.saveDisciplinesToFile(school, new File(path));
				} catch (IOException | InvalidOptionException e) {
					System.out.println("\n" + e);
				}
				break;

			case 18:

				try {
					String path = menu.searchFilePath(selectOption);
					menu.saveStudentsToFile(school, new File(path));
				} catch (IOException | InvalidOptionException | DisciplineNotFoundException e) {
					System.out.println("\n" + e);
				}
				break;

			case 19:

				try {
					String path = menu.searchFilePath(selectOption);
					menu.saveAllToFile(school, new File(path));
				} catch (IOException | InvalidOptionException e) {
					System.out.println("\n" + e);
				}
				break;

			default:
				break;

			}
		} while (option != -1);
	}
}
