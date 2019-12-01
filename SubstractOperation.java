package test5;

public class SubstractOperation extends Equation{

	public SubstractOperation() {
		// TODO Auto-generated constructor stub
		generateBinaryOperation('-');
	}
	
	@Override
	protected int calculate(int left, int right) {
		// TODO Auto-generated method stub
		return left - right;
	}

	@Override
	protected boolean checkCalculation(int result) {
		// TODO Auto-generated method stub
		return result>=0;
	}

}

