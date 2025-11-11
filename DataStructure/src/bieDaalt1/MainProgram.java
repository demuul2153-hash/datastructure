package bieDaalt1;
import java.util.Scanner;

public class MainProgram {
public static void main(String[] args) {
		
		Registration reg = new Registration();
		Scanner scanner = new Scanner(System.in);
		boolean quit = true;
		
		while(quit) {
			
			System.out.println("1 Нийт хичээлүүдийн жагсаалтыг харуулах\r\n"
					+ "2 Нийт мэргэжлүүдийн жагсаалтыг харуулах\r\n"
					+ "3 Нийт оюутны дундаж голч дүнг харуулах\r\n"
					+ "4 Гураваас дээш хичээлд “F” үнэлгээ авссан хасагдах оюутны жагсаалт хэвлэх\r\n"
					+ "5 Хичээл бүрээр оюутнуудын дүнгийн жагсаалтыг харуулах\r\n"
					+ "6 Мэргэжил бүрээр оюутнуудын дүнгийн жагсаалтыг харуулах\r\n"
					+ "7 Гарах");
			
			switch(scanner.nextInt()) {
			
			case 1:
				reg.subjectList();
				break;
			case 2:
				reg.majorList();
				break;
			case 3:
				reg.avgGPA();
				break;
			case 4:
				reg.studentsHasFallen();
				break;
			case 5:
				reg.printAsSubject();
				break;
			case 6:
				reg.printAsMajor();
				break;
			case 7:
				quit = false;
				break;
			default:
				System.out.println("Буруу комманд\n");
			}
			
		}
		
	}
}
