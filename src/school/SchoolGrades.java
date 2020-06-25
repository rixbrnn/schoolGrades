package school;

import java.util.ArrayList;
import systemExceptions.DisciplineIsEmptyException;
import systemExceptions.DisciplineNotFoundException;
import systemExceptions.DuplicatedCodeException;
import systemExceptions.StudentNotFoundException;

public class SchoolGrades {
	private ArrayList<Discipline> disciplines = new ArrayList<>();

	public ArrayList<Discipline> getDisciplines() {
		return disciplines;
	}

	public void addDiscipline(Discipline discipline) {
		if (!hasDiscipline(discipline.getCode()))
			disciplines.add(discipline);
	}

	public Discipline getDiscipline(int code) throws DisciplineNotFoundException {
		for (Discipline i : disciplines) {
			if (i.getCode() == code)
				return i;
		}
		throw new DisciplineNotFoundException();
	}

	public boolean removeDiscipline(int code) throws DisciplineNotFoundException {
		if (!hasDiscipline(code))
			throw new DisciplineNotFoundException();

		disciplines.remove(getDiscipline(code));
		return true;

	}

	private boolean hasDiscipline(int code) {
		if (disciplines.isEmpty())
			return false;
		for (Discipline i : disciplines) {
			if (i.getCode() == code)
				return true;
		}
		return false;
	}

	public void addStudentToDiscipline(Student std, int disCode)
			throws DisciplineNotFoundException, DuplicatedCodeException {
		if (!hasDiscipline(disCode))
			throw new DisciplineNotFoundException();

		this.getDiscipline(disCode).addStudent(std);
	}

	public boolean removeStudentFromDiscipline(int stdCode, int disCode)
			throws StudentNotFoundException, DisciplineNotFoundException, DisciplineIsEmptyException {
		if (!hasDiscipline(disCode))
			throw new DisciplineNotFoundException();
		if (!hasStudent(stdCode))
			throw new StudentNotFoundException();

		this.getDiscipline(disCode).removeStudent(stdCode);
		return true;
	}

	public Student getStudent(int code) throws StudentNotFoundException, DisciplineIsEmptyException {
		if (hasStudent(code)) {
			for (Discipline i : disciplines) {
				return i.getStudent(code);
			}
		}
		throw new StudentNotFoundException();
	}

	private boolean hasStudent(int code) throws DisciplineIsEmptyException {
		if (disciplines.isEmpty())
			throw new DisciplineIsEmptyException();
		for (Discipline i : disciplines) {
			if (i.hasStudent(code))
				return true;
		}
		return false;
	}

	public boolean removeStudentFromAll(int stdCode) throws DisciplineIsEmptyException, StudentNotFoundException {
		if (!hasStudent(stdCode))
			throw new StudentNotFoundException();

		for (Discipline i : disciplines) {
			if (i.hasStudent(stdCode))
				i.removeStudent(stdCode);
		}
		return true;
	}

	public ArrayList<Discipline> getDisciplinesOfStudent(int code)
			throws DisciplineNotFoundException, DisciplineIsEmptyException {
		ArrayList<Discipline> result = new ArrayList<Discipline>();
		for (Discipline i : disciplines) {
			if (i.hasStudent(code))
				result.add(i);
		}
		if (result.isEmpty())
			throw new DisciplineNotFoundException("The Student has no Disciplines registered!");
		return result;
	}

	public ArrayList<Student> getStudentsOfDiscipline(int code)
			throws DisciplineNotFoundException, DisciplineIsEmptyException {
		if (!hasDiscipline(code))
			throw new DisciplineNotFoundException();

		Discipline discipline = getDiscipline(code);

		if (discipline.getStudents().isEmpty())
			throw new DisciplineIsEmptyException();

		return discipline.getStudents();
	}

	public boolean clearDisciplines() {
		if (disciplines.isEmpty())
			return false;
		disciplines.clear();
		return true;
	}

	public double getAverageOfAllDisciplines() throws DisciplineIsEmptyException {
		double sum = 0;
		for (Discipline i : disciplines) {
			sum += i.averageGrade();
		}
		return sum / disciplines.size();
	}

	public int getNumOfAllStudents() {
		int sum = 0;
		for (Discipline i : disciplines) {
			sum += i.getNumOfStudents();
		}
		return sum;
	}

	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> result = new ArrayList<>();
		for (Discipline i : disciplines) {
			for (Student j : i.getStudents()) {
				if (!result.contains(j))
					result.add(j);
			}
		}
		return result;
	}

	public int getNumOfDisciplines() {
		return this.disciplines.size();
	}
}
