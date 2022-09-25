/*



*/


import java.util.*;
import java.math.*;


public class Main {


	int []unit = new int [10000];

	int calc(int n) {
		int sum = 0;
		for (int i=1; i <= n; i++) {
			sum = 0;
			for (int j=1 ; j <= i ; j++) {
					sum += j;
					//System.out.printf("i:%d, j:%d\n", i, j);
			}
			//System.out.printf("sum %d: %d\n",i, sum);
			
		}
		
		return sum;	
		
	}

	int grouping() {
		for (int i=0; i < 1000; i++) {
			unit[i] = calc(i);
		}
		return 0;
		
	}


	void solve() {
		
		int target = 6;
		
		//for (int i=1; i <5; i++) {
		//	System.out.printf("calc(%d):%d \n",i, calc(i));
		//}	
		grouping();	
		for (int i=1; i < 50; i++) {
			//if (i%10 == 0) System.out.printf("i:%d=%d \n",i, unit[i] );
			System.out.printf("i:%d=%d \n",i, unit[i] );
		}
		
		int i=0;
		while (unit[i]++<=target) {
			
		}	
			
			
		
	}



	public static void main(String [] args) {
		Main m = new Main();
		
		m.solve();

		System.out.println("test ...\n");
	}
		

}


/*

    

*/