package laboratorioFinal;

public class Student {
	private String name;
	private int code;
	private double gradeA;
	private double gradeB;
	
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
	
	public Student(String name, int code, double gradeA, double gradeB) throws InvalidStudentParametersException {
		if(checkConstructorParameters(name,code,gradeA,gradeB)) {
			this.name = name;
			this.code = code;
			this.gradeA = gradeA;
			this.gradeB = gradeB;
		}else
			throw new InvalidStudentParametersException();
	}
	
	private boolean checkConstructorParameters(String name, int code, double gradeA, double gradeB) {
		if(name.isEmpty() || name.length()<3)
			return false;
		if(Integer.toString(code).length()<5)
			return false;
		if(gradeA<0 || gradeA>10 || gradeB<0 || gradeB>10)
			return false;
		return true;
	}
	
	public double averageGrade() {
		return (gradeA+(gradeB*2))/2;
	}
	
	
	
	
	
	

}
