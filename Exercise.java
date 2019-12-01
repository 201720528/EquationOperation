package test5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Exercise {

	private HashSet<Equation> hashSet = new HashSet<Equation>();
	
	public void generateBinaryExercise(int operationCount) {
		Equation equation;
		while(operationCount>0) {
			do {
				equation = generateOperation();
			} while (repeat(equation));
			hashSet.add(equation);
			operationCount--;
		}
	}
	
	private Equation generateOperation() {
		Random random = new Random();
		int num = random.nextInt(2);
		if (num == 0) {
			return new AdditionOperation();
		}else {
			return new SubstractOperation();
		}
	}
	
	private boolean repeat(Equation equation) {
		List<Equation> list = new ArrayList<Equation>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).equals(equation)) {
				return true;
			}
		}
		return false;
	}
	
	public void generateAdditionExercise(int operationCount) {
		Equation equation;
		while(operationCount>0) {
			do {
				equation = new AdditionOperation();
			} while (repeat(equation));
			hashSet.add(equation);
			operationCount--;
		}
	}
	
	public void generateSubstractExercise(int operationCount) {
		Equation equation;
		while(operationCount>0) {
			do {
				equation = new SubstractOperation();
			} while (repeat(equation));
			hashSet.add(equation);
			operationCount--;
		}
	}
	
	public void formatDisplay() {
		int count = 0;
		for(Equation v : hashSet) {
			if(count%5==0) {
				System.out.println();
			}
			System.out.print(v+"\t");
			count++;
		}
		
		System.out.println("\n--------------------------\n答案如下：");
		for(Equation v : hashSet) {
			if(count%5==0) {
				System.out.println();
			}
			System.out.print(v.getResultNum()+"\t");
			count++;
		}
	}
	
}
