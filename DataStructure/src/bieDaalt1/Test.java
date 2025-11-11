package bieDaalt1;
import java.io.*;
import java.util.Scanner;

import dataStructures.ArrayLinearList; 
public class Test {
	private ArrayLinearList subjectList;
	private ArrayLinearList file;
	private void makeSubjectList() {
		
		Subject subject = null;
		fileRead(new File("/home/demuul/git/data/DataStructure/src/bieDaalt1/subjectList.txt"));
		
		for(int i=0; i<file.size(); i++) {
			subject = new Subject();
			subject.setCode(file.get(i).toString().split("/")[0]);
			subject.setName(file.get(i).toString().split("/")[1]);
			subject.setCredit(Float.parseFloat(file.get(i).toString().split("/")[2]));

			subjectList.add(i, subject);
		}
		
	}
	private void fileRead(File file) {
		this.file = new ArrayLinearList();
		try {
			Scanner scanner = new Scanner(file);
			do {
				this.file.add(this.file.size(), scanner.nextLine());
			}while(scanner.hasNextLine());
			scanner.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	  public static void main(String args[])throws Exception{    
	        FileReader fr=new FileReader("/home/demuul/git/data/DataStructure/src/bieDaalt1/exams.txt");    
	        BufferedReader br=new BufferedReader(fr);    
	  
	        int i;    
	        while((i=br.read())!=-1){  
	        System.out.print((char)i);  
	        }  
	        br.close();    
	        fr.close();
	        try {
	        	FileReader fileName=new FileReader("/home/demuul/git/data/DataStructure/src/bieDaalt1/majorList.txt\"");
	        	BufferedReader input = new BufferedReader(fileName);
	        	String line = input.readLine();
	        	String values[] = line.split("/");//Massive indexes: 0 – subject code, 1 – subject name, 2 – subject credit
	   			  System.out.println(" "+values[0]);
	        	}
	        	catch (java.io.FileNotFoundException e) {
	        		System.out.println("File not found: ");
	        		System.exit(1); 
	        		}
	        Test testObject = new Test();
	        testObject.makeSubjectList();
	  }
}

   
