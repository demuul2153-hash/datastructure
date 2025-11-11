package bieDaalt1;

import java.io.File;
import java.util.Scanner;

import dataStructures.ArrayLinearList;

public class Registration {
	private ArrayLinearList studentList;
	private ArrayLinearList subjectList;
	private ArrayLinearList majorList;
	private ArrayLinearList file;
	
	public Registration() {
			studentList = new ArrayLinearList();
			subjectList = new ArrayLinearList();
			majorList = new ArrayLinearList();
			
			makeSubjectList();
			makeMajorList();
			makeStudentList();
	}
	
	public Registration(ArrayLinearList studentList, ArrayLinearList subjectList, ArrayLinearList majorList) {
		
		this.studentList = studentList;
		this.subjectList = subjectList;
		this.majorList = majorList;
		
	}
	
	public void setStudentList(ArrayLinearList studentList) {
		this.studentList = studentList;
	}
	
	public void setSubjectList(ArrayLinearList subjectList) {
		this.subjectList = subjectList;
	}
	
	public void setMajorList(ArrayLinearList majorList) {
		this.majorList = majorList;
	}
	
	public ArrayLinearList getStudentList() {
		return this.studentList;
	}
	
	public ArrayLinearList getSubjectList() {
		return this.subjectList;
	}
	
	public ArrayLinearList getMajorList() {
		return this.majorList;
	}
	
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
	
	private void makeMajorList() {
		Major major = null;
		fileRead(new File("/home/demuul/git/data/DataStructure/src/bieDaalt1/majorList.txt"));
		
		for(int i=0; i<file.size(); i++) {
			major = new Major();
			major.setCode(file.get(i).toString().split("/")[0]);
			major.setName(file.get(i).toString().split("/")[1]);
			
			majorList.add(i, major);
		}
	}
	
	private void makeStudentList() {
		
		fileRead(new File("/home/demuul/git/data/DataStructure/src/bieDaalt1/exams.txt"));
		Student student = new Student();
		String[] contain;
		
		for(int i=0; i<file.size(); i++) {
			
			contain = file.get(i).toString().split("/");
		
			if(!contain[0].equals(student.getName()) || student.getName() == null) {
				if(student.getName()!=null)
					studentList.add(studentList.size(), student);
				student = new Student();
				student.setName(contain[0]);
				student.setCode(contain[1]);
			}
			student.addLesson(Integer.parseInt(contain[3]), subjectList, contain[2]);
		}
		studentList.add(studentList.size(), student);
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
	
	public void subjectList() {
		Subject sub;
		for(int i=0; i<subjectList.size(); i++) {
			sub = (Subject) subjectList.get(i);
			sub.print();
		}
	}
	
	public void majorList() {
		Major major;
		for(int i=0; i<majorList.size(); i++) {
			major = (Major) majorList.get(i);
			major.print();
		}
	}
	
	public void avgGPA() {
		Student student;
		for(int i=0; i<studentList.size(); i++) {
			student =(Student) studentList.get(i);
			System.out.println(student.getName()+" "+student.getGPA());
		}
	}
	
	public void studentsHasFallen() {
		Student student;
		for(int i=0; i<studentList.size(); i++) {
			student = (Student) studentList.get(i);
			if(student.isFallen())
				student.print();
		}
	}
	
	public void printAsSubject() {
		
		Subject subject;
		Student student;
		
		for(int i=0; i<subjectList.size(); i++) {
			subject = (Subject) subjectList.get(i);
			System.out.println("----------------------------------------------"+subject.getName()+"----------------------------------------------");
			for(int j=0; j<studentList.size(); j++) {
				
				student = (Student) studentList.get(j);
				if(student.getLessons().contains(subject.getCode())) {
			
					student.print();
				}
			}
		}
		
	}
	
	public void printAsMajor() {
		
		Major major;
		Student student;
		
		for(int i=0; i<majorList.size(); i++) {
			major = (Major) majorList.get(i);
			System.out.println("----------------------------------------------"+major.getName()+"----------------------------------------------");
			for(int j=0; j<studentList.size(); j++) {
				
				student = (Student) studentList.get(j);
				if(student.getCode().contains(major.getCode())) {
					
					student.print();
				}
				
			}
		}
		
	}

}
