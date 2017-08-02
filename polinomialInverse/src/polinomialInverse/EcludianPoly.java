package polinomialInverse;

import java.util.ArrayList;
import java.util.Scanner;

public class EcludianPoly {

	
	
	static  int[][]  findInverse(int[] u,int v[] ){
		int[][] A={{1,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},v};
		int[][] B={{0,0,0,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0},u};
		int[] Q;
		
		
	
		System.out.println("     1   X1  X2  X3  X4  X5  X6  X7  X8 ");
		System.out.print("A1  |");printArray(A[0]);System.out.print("A2  |");printArray(A[1]);System.out.print("A3  |");printArray(A[2]);
		
		System.out.print("B1  |");printArray(B[0]);System.out.print("B2  |");printArray(B[1]);System.out.print("B3  |");printArray(B[2]);
		System.out.println("order of B[3]:"+getOrder(B[2]));
		System.out.println("----------------------------------");
	while(getOrder(B[2])>0){//order zero means value =1 	
		
		//	System.out.println( B[0]+" "+B[1]+" "+B[2]);
	
			ArrayList<int[]> o=devideBIN( A[2],B[2]);
			Q=o.get(0);
			
			int[][]temp=B;
			B=A.clone();
			A=temp.clone();
			
		for(int i=0;i<A.length;i++){
			
			B[i]=XOR(B[i],multiplication(Q, A[i]));
		//	System.out.println("B["+i+"]");printArray(B[i]);
		}
		System.out.println("     1   X1  X2  X3  X4  X5  X6  X7  X8 ");
		System.out.print("Q   |");printArray(Q);
System.out.print("A1  |");printArray(A[0]);System.out.print("A2  |");printArray(A[1]);System.out.print("A3  |");printArray(A[2]);
		
		System.out.print("B1  |");printArray(B[0]);System.out.print("B2  |");printArray(B[1]);System.out.print("B3  |");printArray(B[2]);
		System.out.println("order of B[3]:"+getOrder(B[2]));
		System.out.println("----------------------------------");}
	System.out.println("1   X1  X2  X3  X4  X5  X6  X7  X8 ");
	return B; //the Inverse is B[1]
}
	
public static ArrayList<int[]> devideBIN(int[]x , int[]y){
	ArrayList<int[]> output = new ArrayList<int[]>();
	int[] Q = {0,0,0,0,0,0,0,0,0};
	
	int orderX=getOrder(x);
	int orderY=getOrder(y);
	
	if(orderX>orderY){
	do{
			Q[orderX-orderY]=1;
			int[] o=shift(y, orderX-orderY);
		//	System.out.println("Q:");printArray(Q);
		//	System.out.println("\nX:");printArray(x);
		//	System.out.println("\nQ * "+(orderX-orderY));printArray(o);
			x=XOR(o,x);
		//	System.out.println("\nXOR output + x");printArray(x);
		//	System.out.println();
		orderX=getOrder(x);
		//	System.out.println("order of X "+orderX);
			
	}while(orderX>=orderY);
	
	output.add(Q);//element00 is Q
	output.add(x);//elemnt01 is R
	return output;
	
	}
	
return null;
}	
	private static int getOrder(int[] u){
		for(int i=u.length-1;i>0;i--)
			if(u[i]==1){return i;}
		return 0;
		
	}
	
	private static int[] shift(int[] y,int m){
		int[] out=new int[9];
		for(int j=0;j<out.length;j++)out[j]=0;
		for(int i=0;i<y.length-1;i++){
			if(y[i]==1){
				
				out[i+m]=1;
			}
		}
		return out;
		
	}
	
	private static int[] multBIN(int[] arg1,int times){//one cycle Xor or Xor+shift 
		int[] t=arg1;
		
		int[] m={1,1,0,1,1,0,0,0,0};
		for(int i=0;i<times;i++){
		//	System.out.print("t=  ");	printArray(t);
			if(t[8]==1){
				t=shift(t,1);
				t=XOR(t,m);
			}else if(t[8]==0)
		{
			t=shift(t,1);
		}
		}
		return t; //XOR(t,XOR(shift(arg1,1),arg1));
	}
	
	static int [] multiplication(int[] a, int[] b ){
		int[] out ={0,0,0,0,0,0,0,0,0};
		int times = getOrder(b);
		//System.out.println("order of b"+times);
		ArrayList<int[]> bunchOfArrays=new ArrayList<int[]>();
		//System.out.println("B");printArray(b);
		//System.out.println("A");printArray(a);
	
		for(int i=0;i<=times;i++){
		//	System.out.println("i now is "+b[i]);
				if(b[i]==1){
					
			bunchOfArrays.add(multBIN(a, i));
			}
		}
		
		for(int[] array : bunchOfArrays){
			
			out=XOR(out,array);
		}
		return out;	
	}
	
	private static int[] XOR(int[] array1, int[] array2){
		int[] out=new int[9];
		for(int j=0;j<out.length;j++)out[j]=0;
		//printArray(array2);printArray(array1);
		for(int i=0;i<array1.length;i++){
			if(array1[i]==array2[i]){
				out[i]=0;
			}else{
				out[i]=1;
			}
		}
		return out;	
	}
	
	private static void printArray(int[] arrayToPrint){
		
		for(int e : arrayToPrint){
			System.out.print(e+" | ");
			}
		System.out.println();
	}
	
	public static int[] scanArray(){
		Scanner s=new Scanner(System.in);
		int[] array =new int[9];
		String input=s.nextLine();
		char[] chars=input.toCharArray();
		for(int i=0;i<9;i++){
			array[i]=(int)chars[i]-48;//ASCII shift 
			
		}
		
		return array;
		
	}
	
	public static void main(String[] args) {
			//	0 1 2 3 4 5 6 7 8 
		System.out.println("welcome ... this program is created to find the inverse of a number "
				+ "\nin a galois field ie X^8 + X^4 + X^3 + X + 1"
				+ "\nTo use this program enter 2 polynomial in the binary form.\nlet the first be the desired polynomial to find the inverse of.\nlet the second be the field  ,\nrepresnt each existing power by a 1 "
				+ "\nie  X7 + X +1 would represented as 110000010 notice that we are\nstarting from left to match the index of the array.\n\n");
		System.out.print("enter the first polynomial ie X7 +X+1 :1 1 0 0 0 0 0 1 0");
		int[]u=scanArray();
		System.out.println("thank you your 1 st polynomial is ");printArray(u);System.out.print("enter the second polynomial");
		int[]v=scanArray();//{1 1 0 1 1 0 0 0 1};
		System.out.println("thank you your 2 nd polynomial is ");printArray(v);
		printArray(findInverse(u,v)[1]);
		
}	
	}


