package lab4;
import dataStructures.HashTable;
import java.util.*;
public class MyHashTable extends HashTable
{
	static Scanner obj = new Scanner(System.in);
	public MyHashTable(int theDivisor) {	   // constructor
		super(theDivisor);	// HashTable constructor duudna
	}
	
	public void insert(){	// element nemne
		Object theKey;
		Object theElement;
		try {
			System.out.print("Түлхүүрээ оруулна уу : ");
			theKey = obj.next();	
			System.out.print("Элементээ оруулна уу : ");
			theElement = obj.next();	
			put(theKey,theElement);	
			System.out.println("Элемент амжилттай нэмэгдлээ.");
		}
		catch(Exception e){		// ymr ngn aldaa uusuul
			System.out.println("Алдаа гарлаа => " + e.getMessage());	// aldaag hevlene
		}
	}
	public void Search(){  // element haina
		Object theKey;
		System.out.print("Түлхүүрээн оруулна уу : ");
		theKey = obj.next();
		if(get(theKey) == null)
		{
			System.out.print("Уг түлхүүрт харгалзах элемент байхгүй байна.");
		}
		else {
		int index = super.search(theKey);
		System.out.print("Түлхүүр дээрх элемент : " + table[index].element.toString());
		}
	}
	public Object put(Object theKey, Object theElement) {  
		return super.put(theKey, theElement);
	}
	public Object get(Object theKey){ 
		return super.get(theKey);
	}
	
	
	//Tasks
	public Object updateElement(Object theKey, Object theElement)
	{ 
		/* Код: хэрвээ тухайн түлхүүртэй элемент олдвол утгыг оруулсан элемэнтээр солих кодыг энд бич.*/
		Object elementToUpdate = null;	//anhni utga null baina
		int n = this.search(theKey);	// tuhain key deerh elemntiin index
		if(this.table[n] != null)	// key der elemnt baival
		{
			elementToUpdate = this.put(theKey, theElement);	// ket deeer elmntiig hiine
			if(elementToUpdate != null)	// elemnt grs ugsun bol
			{
				System.out.println(elementToUpdate + " элемент амжилттай солигдлоо.");
			}
			else {
				System.out.println("Солих утга null байж бололхгүй. Дахин оролдого уу!");
			}
		}
		else {
			System.out.println("Уг түлхүүрт харгалзах элемент олдсонгүй. Дахин оролдого уу!");
		}
		return elementToUpdate;	// nemsen elmntiig butsaana.
	}
	
	public Object updateKey(Object theKey, Object theNewKey)
	{ 
	
		Object element = null;
		int n = this.search(theKey); 
		int m = this.search(theNewKey); 
		if(this.table[n] != null && this.table[m] == null )	
		{
			element = this.get(theKey);	
			this.table[n] = null;	
			this.put(theNewKey, element);	
			System.out.println(theKey + " түлхүүр " + theNewKey + " түлхүүрээр амжилттай солигдлоо.");
		}
		else {
			if(this.table[n] == null)
			{			
				System.out.println(theKey + " түлхүүрт харгалзах элемент олдсонгүй. Дахин оролдоно уу!");
				
			}
			else {
				System.out.println(theNewKey + " түлхүүрт элемент харгалзаж байна. Дахин оролдоно уу!");
			}
		}
		return element;	
	}
	
	public void delete(Object theKey) // tulhuureer elmnt ustgna
	{ 

		Object element = this.get(theKey);  
		if(element != null)
		{
			int n = search(theKey);
			this.table[n] = null; 
			this.size--;
			System.out.println(theKey + " түлхүүртэй " + element + " элемент амжилттай устлаа.");
		}
		else {
			System.out.println("Уг түлхүүрт харгалзах элемент олдсонгүй. Дахин оролдоно уу!");
		}
	}

	
	
	
	public void show() 
	{
		System.out.println("Хэш хүснэгт:");
		output();
	}
	public static void menu() {  
		System.out.println("\n" + "----------------Menu----------------");
		System.out.println("1.Хүснэгтэд элемент нэмэх.");
		System.out.println("2.Хүснэгтээс элэмент хасах.");
		System.out.println("3.Түлхүүр шинэчлэх.");
		System.out.println("4.Түлхүүр дээрх элемент шинэчлэх.");
		System.out.println("5.Хүснэгтээс элемэнт хайх.");
		System.out.println("6.Хүснэгтийг хэвлэх.");
		System.out.println("7.Гарах");
		System.out.println("************************************");
	}
	
	
	
	public static void main(String args[])
	{
		int divisor;
		System.out.print("Хэш хүснэгтийн уртыг оруулна уу : ");
		divisor = obj.nextInt();
		MyHashTable hash = new MyHashTable(divisor);
		int condition = 1;
		int command;
		while(condition == 1){
			menu();
			System.out.print("Коммандаа оруулна уу : ");
			command = obj.nextInt();
			
			switch(command){
			
			case 1 :	
				hash.insert();
				break;
				
			case 2 :	
				Object theKey;
				System.out.print("Устгах элементийн түлхүүрийг оруулна уу : ");
				theKey = obj.next();
				hash.delete(theKey);
				break;
				
			case 3 :	
				Object theKey1;
				Object newKey;
				System.out.print("Хуучин түлхүүрийг оруулна уу : ");
				theKey1 = obj.next();
				System.out.print("Солих түлхүүрийг оруулна уу : ");
				newKey = obj.next();
				hash.updateKey(theKey1, newKey);
				break;
				
			case 4 :	
				Object theKey2;
				Object theElement;
				System.out.print("Түлхүүрийг оруулна уу : ");
				theKey2 = obj.next();
				System.out.print("Солих утгийг оруулна уу : ");
				theElement = obj.next();
				hash.updateElement(theKey2, theElement);
				break;
				
			case 5 :	
				hash.Search();
				break;
				
			case 6 :	
				hash.show();
				break;
				
			case 7 :	
				condition = 0;
				System.out.print("Программ дууслаа :)");
				break;
				
			default :
				System.out.println("Буруу комманд байна!!! Дахин оролдоно уу.");
				break;
			}
		}
	}
}