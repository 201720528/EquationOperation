package test2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;


public class Exercise {
	//存储答案
	//生成题目
	public  HashMap<String,Integer> createExercise() {
		//HashSet 用于去除重复的题目
		HashMap<String,Integer> hashMap = new HashMap<String,Integer>();

		//计数器
		int count = 0;
		
		//生成50道题目
		while(count<50) {
			//存储运算符
			GenerateEquations generateEquations = new GenerateEquations();
			Stack<String> stack = generateEquations.generateEquations();	
			String str = null;
			//生成随机数
			int[] num = createRandom();
			//用于计算结果
			int out = calculate(stack,num[0],num[1]);			
			if(out>=0&&out<=100) {//结果满足条件则添加数组
				
				while(!stack.empty()) {
					str = num[0]+stack.pop()+num[1];
				}
				str = str + "=";
				while(str.length()<=8) {
					str +=" ";
				}
				count++;
				hashMap.put(str, out);
			}else {//不满足则跳过本次循环
				continue;
			}
			
		}
		return hashMap;
	}
	
	//私有方法--生成随机数
	private  int[] createRandom() {
		int[] num = new int[2];
		num[0] = (int)(Math.random()*100);
		num[1] = (int)(Math.random()*100);
		return num;
	}
	//私有方法--用于计算结果
	private static int calculate(Stack<String> stack,int num1,int num2) {
		
		int out = 0;
		if(stack.peek()=="+") {
			out = num1 + num2;
		}
		if(stack.peek()=="-") {
			out = num1 - num2;
		}
		return out;
	}
	
}
