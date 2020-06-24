package systemExceptions;

public class InvalidStudentParametersException extends Exception{
	public InvalidStudentParametersException(){
		super("Can't create new Student with Invalid parameters!");
	}

}
