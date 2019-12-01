package test5;

import java.util.Random;

import edition2.Equation2;
//算式类
public abstract class Equation {
	
	private static final int UPPER = 100; 
	private static final int LOWER = 0;
	
	private int leftNum;
	private int rightNum;
	private Character operation;
	private int resultNum;
	
	//调用两个抽象方法，生成符合result>=0&&result<=100算式
	protected void generateBinaryOperation(Character op) {
		int right,result,left;
		
		Random random = new Random();
		left = random.nextInt(UPPER+1);
		
		do {
			right = random.nextInt(UPPER+1);
			result = calculate(left,right);
		} while (!checkCalculation(result));
		
		leftNum = left;
		rightNum = right;
		operation = op;
		resultNum = result;
		
	}
	
	//子类必须实现的两个抽象方法
	abstract int calculate(int left,int right);
	abstract boolean checkCalculation(int result);

	//通过equals方法判断两个Equation对象是否相同
	//保证算是的不重复性
	public boolean equals(Equation2 equation){
		boolean one = (leftNum == equation.getLeftNumber() && rightNum == equation.getRightNumber()
				&& operation == equation.getOperator());
		boolean two = (leftNum == equation.getRightNumber() && rightNum == equation.getLeftNumber()
				&& operation == equation.getOperator());
		return one || two;
	}
	
	public int getLeftNum() {
		return leftNum;
	}

	public int getRightNum() {
		return rightNum;
	}

	public Character getOperation() {
		return operation;
	}

	public int getResultNum() {
		return resultNum;
	}

	@Override
	public String toString() {
		return leftNum+""+operation+""+rightNum+"=";
	}
	
}
