package laboratorioFinal;

public class InvalidStudentParametersException extends Exception{
	InvalidStudentParametersException(){
		super("Can't create new Student with Invalid parameters!");
	}

}
