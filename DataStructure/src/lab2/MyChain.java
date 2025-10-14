package lab2;

import dataStructures.Chain;

public class MyChain extends Chain 
{
	public static void main(String[] args) 
	{
		MyChain A = new MyChain();
		A.add(0, 8);
		A.add(1, 7);
		A.add(2, 7);
		A.add(3, 7);
	
		MyChain B = new MyChain();
		B.add(0, 5);
		B.add(1, 8);
	    B.add(2, 8);
	    
	    System.out.println( "A : " + A.toString());
	    System.out.println("B : " + B.toString());
	    Object[] arr = A.toArray();    
	    System.out.print("A - g Massiv bolgov : ");
	    for (int i = 0; i < arr.length; i++) 
	    {
	    	System.out.print(arr[i] + " ");
	    }
	    System.out.println();
	    Object[] x = {1,9,11}; 
	    Object[] y = {22,9,22};
	    A.addRange(x);  
	    B.addRange(y);
	    System.out.println( "elemntuud nevev - " + " A : " + A.toString());
	    System.out.println( "elemntuud nevev - " + " B : " + B.toString());
	    A.delete(A);  
	    B.delete(B);
	    System.out.println( "ijil elemntiig ustgav - " + " A : " + A.toString());
	    System.out.println( "ijil elemntiig ustgav - " + " B : " + B.toString());
	    System.out.println("intersection : " + A.intersection(A,B));
	    System.out.println("union : " + A.union(A,B));
	}
	
	public void addRange(Object[] element)    
	{
		for (int i = 0; i < element.length; i++) 
		{
			this.add(this.size, element[i]);
		}
	}
	
	public Object[] toArray()   
	{
		Object[] elements = new Object[this.size()];
		for (int i = 0; i < this.size; i++)
		{
			elements[i] = this.get(i);
		}
		return elements;
	}
	
	public MyChain intersection(MyChain A, MyChain B)  
	{
		MyChain u = new MyChain();
		int c = 0;
		for(int i=0; i<B.size(); i++)
		{
			for(int j=0; j<A.size(); j++)
			{
				if(B.get(i) == A.get(j))
				{
					u.add(c, B.get(i));
					c++;
				}
			}
		}
		u.delete(u);	
		return u;
	}
	
	public MyChain union(MyChain A, MyChain B)
	{
		MyChain u = new MyChain();
		int c = 0;
		int n = A.size();
		int m = B.size();
		int max = n >= m ? n : m;
		for(int i=0; i<max; i++)
		{
			if(n >= (i+1))
			{
				u.add(c, A.get(i));
				c++;
			}
			if(m >= (i+1))
			{
				u.add(c, B.get(i));
				c++;
			}
		}
		u.delete(u);	
		return u;
	}
	
	
	
	public void delete(MyChain A)  
	{
		for(int i=0; i<A.size(); i++)
		{
			if(count(A , i) != 1)	
			{
				remove(i);
			}
		}
	}
	public static int count(MyChain A, int i)   
	{
		int c=0;
		for(int j=0; j<A.size(); j++)
		{
			if(A.get(i) == A.get(j))   
			{
				c ++;  
			}
		}
		return c;
	}
}