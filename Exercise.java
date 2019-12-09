package test6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Exercise {
	private static final String pathEQU = "C:\\Users\\d\\Desktop\\软件构造\\test\\equation.csv";
	private static final String pathPRE = "C:\\Users\\d\\Desktop\\软件构造\\test\\prectise.csv";
	private static final String pathCHE = "C:\\Users\\d\\Desktop\\软件构造\\test\\check.csv";

	private HashSet<Equation> hashSet = new HashSet<Equation>();
	public static ArrayList<String[]> lstFile = new ArrayList<String[]>();
	
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
		writeInFile();
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
		writeInFile();
	}
	
	//将生成的算是写入习题文件
	private void writeInFile() {
		//cvs文件写入部分
		try{
			CsvWriter csvWriter = new CsvWriter(pathEQU,',',Charset.forName("gb2312"));
			String[] csvHeader = {"习题","答案"};
			csvWriter.writeRecord(csvHeader);
			for(Equation v:hashSet){
				String[] csvContent = new String[2];
				csvContent[0] = v.getLeftNum()+""+v.getOperation()+""+v.getRightNum()+"=";
				csvContent[1] = v.getResultNum()+""; 
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
			System.out.println("--------------------已完成写入操作--------------");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//读取习题文件中的内容
	public void readInFile() {
		//cvs文件读入部分
		try {
			int col = 0;
			CsvReader reader = new CsvReader(pathEQU,',',Charset.forName("gb2312"));
			reader.readHeaders();
			while(reader.readRecord()){
				//System.out.println(reader.getRawRecord());
				lstFile.add(reader.getValues());
			}
			
			reader.close();
			System.out.println(lstFile.size());
//			System.out.println(lstFile.get(1)[0].toString());
			for(int row = 0 ; row < lstFile.size(); row ++ ){
				for(col=0;col<lstFile.get(row).length;col++){
				String cell = lstFile.get(row)[col];
				System.out.print(cell);
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//将习题文件和练习文件的答案进行对比和评判
	//将结果写入评判文件
	public void makeCheckFile(int num) {
		//cvs文件读入部分
		List<String> equation = new ArrayList<String>();
		List<String> equAnswer = new ArrayList<String>();
		List<String> preAnswer = new ArrayList<String>();
		List<String> cheAnswer = new ArrayList<String>();
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(pathEQU));
			String str = "";
			int i=0;
			str = reader1.readLine();
			while((str=reader1.readLine())!=null&&i<num) {
				String[] str1 = str.split(",");
				equAnswer.add(str1[1]);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathPRE));
			String str = "";
			str = reader.readLine();
			while((str=reader.readLine())!=null) {
				String[] str1 = str.split(",");
				preAnswer.add(str1[1]);
				equation.add(str1[0]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<num;i++) {
			if(equAnswer.get(i).equals(preAnswer.get(i))) {
//				System.out.println(equAnswer.get(i));
//				System.out.println(preAnswer.get(i));
				cheAnswer.add("对");
			}else {
				cheAnswer.add("错");
			}
		}
		
		try{
			CsvWriter csvWriter = new CsvWriter(pathCHE,',',Charset.forName("gb2312"));
			String[] csvHeader = {"习题","标准答案","所答答案","批改答案"};
			csvWriter.writeRecord(csvHeader);
			for(int i=0;i<num;i++){
				String[] csvContent = {equation.get(i),equAnswer.get(i),preAnswer.get(i),cheAnswer.get(i)};
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
			System.out.println("--------------------已完成写入操作--------------");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	//从习题文件中读取一定数量的习题并作答
	//将所读取的习题和所答写入练习文件和审查文件
	public void makePrectiseFile(int num) {
		Scanner input = new Scanner(System.in);
		List<String> list = new ArrayList<String>();
		ArrayList<String[]> lstFile1 = new ArrayList<String[]>();
		
		try {
			int count = 1;
			BufferedReader reader = new BufferedReader(new FileReader(pathEQU));
			String str = reader.readLine();
			while((str=reader.readLine())!=null && count<=num) {
				String[] str1 = str.split(",");
				System.out.println(str1[0]);
				String answer = input.nextLine();
				list.add(answer);
				count++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			CsvReader reader = new CsvReader(pathEQU,',',Charset.forName("gb2312"));
			reader.readHeaders();
			while(reader.readRecord()){
				//System.out.println(reader.getRawRecord());
				lstFile1.add(reader.getValues());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			CsvWriter csvWriter = new CsvWriter(pathPRE,',',Charset.forName("gb2312"));
			String[] csvHeader = {"习题","答案"};
			csvWriter.writeRecord(csvHeader);
			for(int i=0;i<num;i++){
				String[] csvContent = {lstFile1.get(i)[0],list.get(i)};
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
			System.out.println("--------------------已完成写入操作--------------");
		}
		catch(IOException e){
			e.printStackTrace();
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
