package bieDaalt1;

import dataStructures.Chain;
import dataStructures.ArrayLinearList;

public class Student {
	private String name;
	private String code;
	private float GPA;
	private Chain lessons;
	
	public Student() {
		lessons = new Chain();
	}
	
	public Student(String code, Chain lessons) {
		
		lessons = new Chain();
		this.code = code;
		this.lessons = lessons;
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void calculateGPA() {
		if(!lessons.isEmpty()) {
			Lessons lesson;
			float qualityPoints = 0, credits = 0;
			
			for(int i=0; i<lessons.size(); i++) {
				lesson = (Lessons)lessons.get(i);
				qualityPoints += ((lesson.getScore()/20)-1)*lesson.getLearned().getCredit();
				credits += lesson.getLearned().getCredit();
			}
			
			this.GPA = qualityPoints/credits;
		}
	}
	
	
	public void addLesson(int score, ArrayLinearList subs, String code) {
		Subject subject = new Subject(subs, code);
		lessons.add(lessons.size(), new Lessons(subject, score));
		calculateGPA();
	}
	
	public boolean isFallen() {
		int count = 0;
		Lessons lesson;
		for(int i=0; i<lessons.size(); i++) {
			lesson = (Lessons) lessons.get(i);
			if(lesson.getScore()<60) 
				count++;
		}
		
		return count >= 3 ? true:false;
	}
	
	public void print() {
		System.out.println(name+" "+code+" "+GPA);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public float getGPA() {
		return this.GPA;
	}
	
	public String getLessons() {
		String code = "";
		Lessons lesson;
		Subject sub;
		for(int i=0; i<lessons.size(); i++) {
			lesson = (Lessons)  lessons.get(i);
			sub = (Subject) lesson.getLearned();
			code += sub.toString();
		}
		return code;
	}
	
	public String getMajor(ArrayLinearList majorList) {
		
		Major major;
		
		for(int i=0; i<majorList.size(); i++) {
			
			major = (Major) majorList.get(i);
			if(code.contains(major.toString()))	
				return major.toString();
		}
		return "null";
		
	}
	
}
