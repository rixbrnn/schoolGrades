package systemExceptions;

public class DuplicatedCodeException extends Exception{
	public DuplicatedCodeException(){
		super("This code has already been added!");
	}

}
