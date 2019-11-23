package test2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Print {

	public static int[] result = new int[50];
	//题目说明
	public  void printHeader() {
		System.out.println("生成50 道100 以内加法/减法算式的习题");
	}
	
	//打印题目
	public  void printExercise() {
		
		Exercise exercise = new Exercise();
		HashMap<String,Integer> hashMap = exercise.createExercise();
		//获取map集合的全部key值
		Set<String> keys = hashMap.keySet();
		//通过迭代器遍历keys
		Iterator<String> it = keys.iterator();
		int count=0;
		while(it.hasNext()) {
			String key = it.next();
			Integer value = hashMap.get(key);
			result[count] = value;
			if(count%5==0) {
				System.out.println();
			}
			count++;
			System.out.print(count+"、"+key+"\t");
			
		}
	}
	
	//打印结果
	public  void printCalculations() {
		//获取答案
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("答案如下：");
		int count = 0;
		for(int v:result) {
			if(count%5==0) {
				System.out.println();
			}
			count++;
			System.out.print(count+"、"+v+"\t");
			
		}
	}
	
	
}
