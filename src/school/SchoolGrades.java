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
		disciplines.add(discipline);
	}

	public Discipline getDiscipline(int code) throws DisciplineNotFoundException {
		for (Discipline i : disciplines) {
			if (i.getCode() == code)
				return i;
		}
		throw new DisciplineNotFoundException();
	}

	public boolean removeDiscipline(int code) {
		if (!hasDiscipline(code))
			try {
				disciplines.remove(getDiscipline(code));
				return true;
			} catch (DisciplineNotFoundException e) {
				e.printStackTrace();
			}
		return false;
	}

	private boolean hasDiscipline(int code) {
		for (Discipline i : disciplines) {
			if (i.getCode() == code)
				return true;
		}
		return false;
	}

	public boolean addStudentToDiscipline(Student std, int disCode) throws DisciplineIsEmptyException {
		if (!hasDiscipline(disCode))
			return false;
		try {
			try {
				this.getDiscipline(disCode).addStudent(std);
			} catch (DuplicatedCodeException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} catch (DisciplineNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeStudentFromDiscipline(int stdCode, int disCode)
			throws StudentNotFoundException, DisciplineIsEmptyException {
		if (!hasDiscipline(disCode) || !hasStudent(stdCode))
			return false;
		try {
			this.getDiscipline(disCode).removeStudent(stdCode);
			return true;
		} catch (DisciplineNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Student getStudent(int code) throws StudentNotFoundException, DisciplineIsEmptyException {
		if (hasStudent(code)) {
			for (Discipline i : disciplines) {
				try {
					return i.getStudent(code);
				} catch (StudentNotFoundException e) {
					continue;
				}
			}
		}
		throw new StudentNotFoundException();
	}

	private boolean hasStudent(int code) throws DisciplineIsEmptyException {
		for (Discipline i : disciplines) {
			if (i.hasStudent(code))
				return true;
		}
		return false;
	}

	public boolean removeStudentFromAll(int stdCode) throws DisciplineIsEmptyException, StudentNotFoundException {
		if (!hasStudent(stdCode))
			return false;

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

	public double getAverageOfAllDisciplines() {
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
}
