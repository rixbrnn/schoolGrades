package laboratorioFinal;

import java.io.IOException;

public class Teste {
	public static void main(String[]args) throws IOException, InvalidStudentParametersException, DisciplineIsEmptyException {
		SchoolGrades s = new SchoolGrades();
		
		s.addDiscipline(new Discipline("MATH",12345));
		try {
			s.getDiscipline(12345).addStudent(new Student("Richard",65432,10.0,10.0));
			s.getDiscipline(12345).addStudent(new Student("Gabriel",65132,10.0,10.0));
		} catch (DuplicatedCodeException e) {
			e.printStackTrace();
		} catch (DisciplineNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidStudentParametersException e) {
			e.printStackTrace();
		}
		
		System.out.println(SchoolGradesIO.saveSchoolGrades(s));
		
		s = SchoolGradesIO.readSchoolGrades();
		System.out.println("NOTAS MEDIAS:"+s.getAverageOfAllDisciplines());
		System.out.println("NUMERO DE ESTUDANTES:"+s.getNumOfAllStudents());
		System.out.println("TODOS ESTUDANTES:"+s.getAllStudents().toString());
		
	}
	
	

}
