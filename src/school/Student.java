package school;

import systemExceptions.InvalidCodeException;
import systemExceptions.InvalidGradeException;
import systemExceptions.InvalidStudentParametersException;

public class Student {
	private String name;
	private int code;
	private double gradeA, gradeB;

	public Student(String name, int code) throws InvalidStudentParametersException, InvalidCodeException {
		if (checkConstructorParameters(name, code)) {
			this.name = name;
			this.code = code;
			this.gradeA = -1;
			this.gradeB = -1;
		} else
			throw new InvalidStudentParametersException();
	}

	private boolean checkConstructorParameters(String name, int code) throws InvalidCodeException {
		if (name.isEmpty())
			return false;
		if (Integer.toString(code).length() < 5)
			throw new InvalidCodeException();
		return true;
	}

	private boolean checkGrades(double grade) throws InvalidGradeException {
		if ((grade < 0 || grade > 10))
			throw new InvalidGradeException();
		else
			return true;
	}

	public double averageGrade() {
		if (gradeA == -1 && gradeB == 1)
			return -1;
		else if (gradeA > -1 && gradeB == -1)
			return gradeA;
		else
			return (gradeA + (gradeB * 2)) / 3;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getGradeA() {
		return gradeA;
	}

	public double getGradeB() {
		return gradeB;
	}

	public void setGradeA(double gradeA) throws InvalidGradeException {
		if (checkGrades(gradeA))
			this.gradeA = gradeA;
	}

	public void setGradeB(double gradeB) throws InvalidGradeException {
		if (checkGrades(gradeB))
			this.gradeB = gradeB;
	}

	public String showStudentFinalGrade() {

		StringBuilder st = new StringBuilder();

		st.append("\nCode: ");
		st.append(this.code);
		st.append(" - Final Grade = ");

		if (gradeA > -1 && gradeB == -1)
			st.append(averageGrade());
		else
			st.append("No final grade yet.");

		return st.toString();
	}

	public String toString() {
		StringBuilder st = new StringBuilder();

		st.append(String.format("%15S;", name));
		st.append(String.format("%15d;", code));
		st.append(String.format("%15S:%6.2f;%6.2f;%15S:%6.2f;", "Grades", gradeA, gradeB, "Final Grade", averageGrade()));

		return st.toString();
	}
}
