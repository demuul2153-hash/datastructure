package bieDaalt1;

public class Lessons {
	private Subject learned;
	private int score;
	
	public Lessons() {
		
	}
	
	public Lessons(Subject learned, int score) {
		this.learned = learned;
		this.score = score;
	}
	
	public void setLearned(Subject learned) {
		this.learned = learned;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Subject getLearned() {
		return this.learned;
	}
	
	public int getScore() {
		return this.score;
	}
}
 	