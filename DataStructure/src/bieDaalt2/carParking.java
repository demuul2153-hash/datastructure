package bieDaalt2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dataStructures.*;

public class carParking extends ArrayStack{
	
	private ArrayStack temppark;  
	public carParking() 
	{
		super(10); 
		temppark = new ArrayStack(10); // 10 
	}
	
	public void input(String number)  
	{
		if(this.top == this.stack.length-1) 
		{
			System.out.println("Зогсоол дүүрсэн байна. " + number + " дугаартай машин орох боломжгүй.");
		}
		else
		{
			this.push(number);
			System.out.println(number + " дугаартай машин орлоо.");
		}
	}
	
	public void output(String number) 
	{
		if(this.empty()) 
		{
			System.out.println("Зогсоол хоосон байна. " + number + " дугаартай машин зогсоолд байхгүй.");
		}
		else 
		{
			this.process(number);
		}
	}
	
	public void process(String number) 
	{
		int k=0; 
		while(!this.empty())  
		{
			if(!this.peek().equals(number))  
			{
				temppark.push(this.pop());  
				k++; 
			}
			else  
				break;  

		}
		if(this.empty())  
		{
			System.out.println(number + " дугаартай машин зогсоолд байхгүй.");
		}
		else
		{
			System.out.println(k + " машин гаргасны дараа " + this.pop() + " дугаартай машин зогсоолоос гарлаа.");
		}
		while(!temppark.empty()) 
		{
			this.push(temppark.pop()); 
		}
		if(k>0 && k<=this.top) 
			System.out.println(k + " машин буцааж зогсоолд орууллаа.");
	}
	
	public static void main(String[] args)
	{
		carParking cp = new carParking();  
		try {
			File file = new File("../../src/bieDaalt2/cars.txt"); 
			Scanner sc = new Scanner(file);
			while(sc.hasNext())  
			{
				String[] inputline = sc.nextLine().split(" "); 
				if(inputline[0].equals("A")) 
				{
					cp.input(inputline[1]);  
				}
				else // D bol
				{
					cp.output(inputline[1]);
				}
				System.out.println("----------------------------------------------------");			}
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("Error : " + e.getMessage());  
			System.exit(0);
		}
	}
	
}


