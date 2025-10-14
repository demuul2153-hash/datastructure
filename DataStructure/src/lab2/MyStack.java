package lab2;
import dataStructures.Stack;;
public class MyStack implements Stack
{
	int size;    
	Object element[];
	public MyStack (int n) {   
		element = new Object[n];
		size = 0;
	}
	public MyStack () {  
		this(15);
	}
	public boolean empty() { 
		return (size == 0); 
	}
	public Object peek() { 
		Object a;
		a = element[size-1];
		return a;
	}
	public void push(Object a) {  
		element[size] = a; 
		size++;
	}
	public Object pop() { 
		Object a;
		a = element[size-1];
		element[size-1] = null;
		size--;
		return a;
	}
	public int size() {
		return size;
	}
	public void inputStack(Object element[]) {  
		for(int i = 0; i < element.length; i++) 
			push(element[i]);
	}
	public void printStack(){ 
		for(int i = 0; i < size; i++) 
			System.out.print((int)element[i] + "  ");
		System.out.println();
	}
	public MyStack splitStack(MyStack stack) 
	{
		Object op;
		int n = size/2;
		for(int i=0; i<n; i++)
		{
			op = this.peek();
			this.pop();
			stack.push(op);
		}
		return stack;
	}
	public static void combineStack(MyStack a, MyStack b) // stackiig niiluuleh
	{
		Object op;
		int n = b.size();  //b stackiin urt
		for(int i=0; i<n; i++)
		{
			op = b.peek();  //b stackiin oroin elemntiig avna
			b.pop();  // oroin elemntiig ustgna
			a.push(op); // a stackiin oroi der nemne
		}
		// a stack ni a,b giin combine stack bolov
	}
	
	public static void main(String args[]) 
	{
		MyStack m = new MyStack(); // defualt urttai undsen stack uusgev
		MyStack test = new MyStack(); //splitd, combined ashiglah nemenlt stack
		// elemntuud nemev
		m.push((Object)11);
		m.push((Object)12);
		m.push((Object)13);
		m.push((Object)14);
		
		
		System.out.println("-----------------------------------");
		System.out.println("__undsen stack uusgen elemntuud nemen hevlev__" + "\n");
		System.out.print("* Stackiin elemntuud : ");  // stackiig hevlej harav
		m.printStack();
		System.out.println( "* Stackiiin urt : " + m.size());  // urtiig hevlej harav
		
		
		System.out.println("-----------------------------------");
		Object[] ob = {15, 16, 17 , 18};  // object turliin massiv
		m.inputStack(ob); // massiviin elemntudiig stackd nemev
		System.out.print("* Stackd dahin elemntuud nemev : ");
		m.printStack(); // stackiig hevlej harav
		System.out.println( "* Stackiiin urt : " + m.size());
		
		
		System.out.println("-----------------------------------");
		System.out.println("__split() func ashiglan undsen stackiig test stacktai 2 huvaav__" + "\n");
		m.splitStack(test);  // undsen stackiig test stackd 2 huvaav
		System.out.print("* undsen stack elemntuud : ");
		m.printStack();
		System.out.print("* test stack elemntuud : ");
		test.printStack();
		System.out.println("* undsen stack urt : " + m.size()); 
		System.out.println("* test stack urt : " + test.size()); 
		
		
		System.out.println("-----------------------------------");
		System.out.println("__combine() func ashiglan undsen bolon test stackudiig negtgev__" + "\n");
		combineStack(m,test);   // undsen bolon test stackuudiig niiluulev
		System.out.print("* combine stack : ");
		m.printStack();
		System.out.println( "* Stackiiin urt : " + m.size());
	}
}