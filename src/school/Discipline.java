package school;

import java.util.ArrayList;

import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.StudentNotFoundException;

public class Discipline {
	private String name;
	private int code;
	private ArrayList<Student> students = new ArrayList<>();

	public Discipline(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public Student getStudent(int code) throws StudentNotFoundException, DisciplineIsEmptyException {
		if (!hasStudent(code))
			throw new StudentNotFoundException();
		for (Student i : students) {
			if (i.getCode() == code)
				return i;
		}
		throw new StudentNotFoundException();
	}

	public int getNumOfStudents() {
		return students.size();
	}

	public void addStudent(Student student) throws DuplicatedCodeException {
		if (hasStudent(student.getCode()))
			throw new DuplicatedCodeException();
		students.add(student);
	}

	public boolean removeStudent(int code) throws StudentNotFoundException, DisciplineIsEmptyException {
		if (!hasStudent(code))
			return false;

		Student st;
		try {
			st = getStudent(code);
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		students.remove(st);
		return true;
	}

	public boolean hasStudent(int cod) {
		if (students.isEmpty())
			return false;
		for (Student i : students) {
			if (i.getCode() == cod)
				return true;
		}
		return false;
	}

	public double averageGrade() {
		double sum = 0;
		for (Student i : students) {
			sum += i.averageGrade();
		}
		return sum / students.size();
	}

	public String toString() {
		StringBuilder st = new StringBuilder();

		st.append(String.format("%10S;", name));
		st.append(String.format("%15d;", code));
		st.append(String.format("%15d;", getNumOfStudents()));

		return st.toString();
	}
}
