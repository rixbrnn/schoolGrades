package laboratorioFinal;

public class DisciplineNotFoundException extends Exception {
	public DisciplineNotFoundException() {
		super("It was not possible to found the discipline!");
	}
	
	public DisciplineNotFoundException(String msg) {
		super(msg);
	}

}
