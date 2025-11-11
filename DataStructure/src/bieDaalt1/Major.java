package bieDaalt1;

public class Major {  // Мэргэжил
	private String code;
	private String name;
	
	public Major() {
		
	}
	
	public Major(String code, String name) {
		
		this.code = code;
		this.name = name;
		
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void print() {
		System.out.println(code+"/"+name);
	}
	
	public String toString() {
		return code+"/"+name;
	}
}
