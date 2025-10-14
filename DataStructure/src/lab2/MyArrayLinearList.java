package lab2;
import java.util.*;
public class MyArrayLinearList
{
    public static void main(String []args)
    {
        Scanner obj = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>(); // list
        int n , s;
        System.out.print("Heden element oruulh ve : ");
        n = obj.nextInt();
        for(int i = 1; i<=n; i++)
        {
            s = obj.nextInt();
            numbers.add(s);   // elemntuud nevev
        }
        System.out.println("anhnii ugsun list : ");
        show(numbers);
        System.out.println("\nhamgiin ih element : " + max(numbers));
        System.out.println("hamgiin baga element : " + min(numbers));
        System.out.println("elementuudiin niilber : " + sum(numbers));
        System.out.println("elementuudiin dundaj : " + average(numbers));
        System.out.println("list iig usuhuur eremblev : ");
        sort(numbers);
        show(numbers);
        System.out.println("\nsondgoi elemntuudiig ustgav: ");
        removeOdd(numbers);
        show(numbers);
    }
    public static void show(List<Integer> a) 
    {
        for(int i=0; i<a.size(); i++)
        {
            System.out.print(a.get(i) + " ");
        }
    }
    public static int max(List<Integer> a)  
    {
        int max = a.get(0);
        for(int i=0; i<a.size(); i++)
        {
            if(max <= a.get(i))
            {
                max = a.get(i);
            }
        }
        return max;
    }
    public static int min(List<Integer> a)  
    {
        int min = a.get(0);
        for(int i=0; i<a.size(); i++)
        {
            if(min >= a.get(i))
            {
                min = a.get(i);
            }
        }
        return min;
    }
    public static int sum(List<Integer> a)  
    {
        int s = 0;
        for(int i=0; i<a.size(); i++)
        {
            s += a.get(i);
        }
        return s;
    }
    public static float average(List<Integer> a)  
    {
        int s = 0;
        for(int i=0; i<a.size(); i++)
        {
            s += a.get(i);
        }
        return s/a.size();
    }
    public static void removeOdd(List<Integer> a)  
    {
        for(int i=0; i<a.size(); i++)
        {
            if(a.get(i) %2 != 0)
            {
                a.remove(i);
            }
        }
    }
   
    public static void sort(List<Integer> a) {
    	int len = a.size();
    	for(int i=0; i<len; i++) {
    		for(int j=1; j<(len-i); j++) {
    			if(a.get(j-1)>a.get(j)) {
    				int temp;
    				temp=a.get(j);
    				a.set(j, a.get(j-1));
    				a.set(j-1, temp);
    				
    			}
    		}
    	}
    }
}