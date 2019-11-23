package test2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exercise exercise = new Exercise();
		Print print = new Print();
		//打印标题
		print.printHeader();		
		//生成试题
		exercise.createExercise();		
		//打印试题
		print.printExercise();			
		//答应结果
		print.printCalculations();
	}

}
