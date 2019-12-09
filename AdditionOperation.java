package test6;

public class AdditionOperation extends Equation{

	//通过构造方法调用父类generateBinaryOperation方法
	public AdditionOperation() {
		// TODO Auto-generated constructor stub
		generateBinaryOperation('+');
	}
	
	//计算结果
	@Override
	protected int calculate(int left, int right) {
		// TODO Auto-generated method stub
		return left + right;
	}
	
	
	@Override
	protected boolean checkCalculation(int result) {
		// TODO Auto-generated method stub
		return result<=100;
	}

}
