package bieDaalt1;
import dataStructures.ArrayLinearList;

public class Subject {
	private String code;
	private String name;
	private float credit;
	
	public Subject(String code, String name, float credit) {
		
		this.code = code;
		this.name = name;
		this.credit = credit;
		
	}
	
	public Subject(ArrayLinearList subs, String code) {
		this.code = code;
		subjectAsCode(subs);
	}
	
	public Subject() {}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCredit(float credit) {
		this.credit = credit;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getCredit() {
		return this.credit;
	}
	
	private void subjectAsCode(ArrayLinearList subs) {
		
		for(int i=0; i<subs.size(); i++) {
			Subject sub = (Subject) subs.get(i);
			if(sub.getCode().equals(this.code)) {
				this.name = sub.getName();
				this.credit = sub.getCredit();
			}
		}
		
	}
	
	public void print() {
		System.out.println(this.code+"/"+this.name+"/"+this.credit);
	}
	
	public String toString() {
		return this.code+"/"+this.name+"/"+this.credit;
	}
}
