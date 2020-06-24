package main;

import school.*;
import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.DisciplineNotFoundException;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidGradeException;
import systemExceptions.InvalidOptionException;
import systemExceptions.InvalidStudentParametersException;
import systemExceptions.StudentNotFoundException;
import userInterfaceMenu.Menu;

public class Main {
	private static int option = 1, selectOption = 1;

	public static void main(String[] args) {
		Menu menu = new Menu();

		SchoolGrades school = new SchoolGrades();

		do {
			try {
				option = menu.showMenu();
				selectOption = menu.selectOption(option);
			} catch (InvalidOptionException e) {
				System.out.println(e);
			}

			switch (selectOption) {

			case 0:
				break;

			case 1:

				option = -1;

				break;

			case 2:

				menu.registerDiscipline(school);
				break;

			case 3:

				try {
					menu.registerStudent(school);
				} catch (InvalidStudentParametersException | DuplicatedCodeException | DisciplineNotFoundException
						| InvalidCodeException | InvalidGradeException | DisciplineIsEmptyException e) {
					System.out.println(e);
				}
				break;

			case 4:

				try {
					menu.registerGrade(school);
				} catch (InvalidGradeException | StudentNotFoundException | DisciplineIsEmptyException
						| DisciplineNotFoundException e) {
					System.out.println(e);
				}
				break;

			case 5:
				
				try {
					menu.showStudentFinalGrade(school);
				} catch (DisciplineNotFoundException | StudentNotFoundException | DisciplineIsEmptyException e) {
					System.out.println(e);
				}
				break;

			case 6:

				break;

			case 7:
				
				try {
					menu.studentListFromDiscipline(school);
				} catch (DisciplineNotFoundException e) {
					System.out.println(e);
				}
				break;
			
			case 8:
				
				menu.studentListFromSchool(school);
				break;
				
			default:
				break;
			}
		} while (option != -1);
	}
}
