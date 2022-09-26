/*
 * http://jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1273&sca=99&sfl=wr_subject&stx=%EB%8F%99%EC%A0%84
 * http://comganet.github.io/dp/2016/08/19/dp-1001
 
   https://mygumi.tistory.com/129
   https://data-make.tistory.com/392
    
문제
우리나라 동전의 단위는 1원, 5원, 10원, 50원, 100원, 500원의 6단계로 이루어진다. 
잔돈 256원을 내주려면 500원 0개, 100원 2개, 50원 1개, 5원 1개, 1원 1개로 해서 모두 5개의 동전이 필요하다. 
만약 동전 단위가 1원, 4원, 6원의 3단계로 이루어진 나라에서 8원을 내주려면 6원 1개, 1원 2개도 가능하고, 4원 2개로 가능하다. 
앞의 경우에는 동전 3개, 뒤의 경우에는 동전이 2개 필요하다. 동전의 개수를 최소로 하면서 동전을 내주는 것이 목적이라면 뒤의 방법을 택해야 한다. 
동전의 단위들이 주어지고, 원하는 잔돈이 주어질 때, 그 잔돈에 맞추기 위해 필요한 최소의 동전 수를 구하시오. 갖고 있는 동전의 수는 무한하다.

 입력형식
첫 줄은 동전의 단계 수 N(1≤N≤10), 둘째 줄은 N개로 이루어진 동전들의 단위들이며, 셋째 줄은 잔돈 W(1≤W≤64,000)을 나타낸다.

 출력형식
첫 줄에 잔돈을 맞추기 위한 최소의 동전 수를 출력한다. 만약 동전을 맞추기가 불가능한 경우는 “impossible”을 출력한다.

 입력 예
3
1 4 6
8

 출력 예
2

3
1 3 6
8

->
3


3
1 3 6
6

->
1 


 */

package D0;

import java.util.Scanner;

public class coinchange {
	
	private static int N; // 동전 개수
	private static int[] coin; // 동전의 단위
	private static int M; // 잔돈
	
	private static final int IMP = Integer.MAX_VALUE;
	private static final int NOTSOLVED = -1;
	private static int[][] dp; // 다이나믹 테이블(2차원 배열)
	private static int[] D; // 다이나믹 테이블(1차원 배열)
	
	static void initDynamicTable1D() {
		D = new int[M+2];
		// 최소값을 구하기 위한 IMP값 지정
		for(int m=1; m<=M; m++) D[m] = IMP;
	}
	static int doDynamic1D() {
		// N 개의 동전을 돌면서
		for(int n=1; n<=N; n++) {
			// 잔돈만큼 돌면서
			for(int m=coin[n]; m<=M; m++) {
				// 이전 동전이 만든값과 새로운 동전을 하나 써서
				// 만드는 경우를 비교하여 작은값을 취한다.
				// m-coin[n]하는 부분은 동전이 무한대 이므로
				// 동전수를 여려개 쓰는 경우를 감안한 부분이다.
				D[m] = Integer.min(D[m], D[m-coin[n]]+1);
			}
		}
		return D[M];
	}
	
	static void initDynamicTable2D() {
		dp = new int[N+2][M+2];
		for(int m=1; m<=M; m++)
			dp[0][m] = IMP; // 아무 동전도 사용하지 않음. 불가능으로 표기
//		for(int n=1; n<=N; n++)
//			for(int m=1; m<=M; m++)
//				dp[n][m] = NOTSOLVED;
	}
	
	static int doDynamic2D() {
		for(int n=1; n<=N; n++) {
			for(int m=1; m<=M; m++) {
				// 점화식 코드 - Start
				dp[n][m] = dp[n-1][m];
				if(m >= coin[n]) { // n 번째 동전 보다 잔돈이 더 크거나 같으면
					if(dp[n][m-coin[n]] != IMP && dp[n][m] > dp[n][m-coin[n]]+1) {
						dp[n][m] = dp[n][m-coin[n]] + 1;
					}
				}
				// End
//				printTable2D(dp);
			}
		}
		return dp[N][M];
	}
	
	static void initMemoiTable() {
		dp = new int[N+2][M+2];
		for(int m=1; m<=M; m++) dp[0][m] = IMP;
		for(int n=1; n<=N; n++) {
			for(int m=1; m<=M; m++) 
				dp[n][m] = NOTSOLVED;
		}
	}
	
	static int Memoi(int n, int m) {
		if(dp[n][m] == NOTSOLVED) {
			// 점화식 코드 Start
			dp[n][m] = Memoi(n-1, m);
			if(m >= coin[n]) {
				int temp = Memoi(n, m-coin[n]);
				if((temp != IMP) && (dp[n][m] > temp+1))
					dp[n][m] = temp+1;
			}
			// End
//			printTable2D(dp);
		}
		return dp[n][m];
	}

	static void input(Scanner sc) {
		N = sc.nextInt();
		coin = new int[N+2];
		for(int i=1; i<=N; i++)
			coin[i] = sc.nextInt();
		M = sc.nextInt();
	}
	
	static void output(int cnt) {
		if(cnt == -1)
			System.out.println("impossible");
		else
			System.out.println(cnt);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		input(sc);
		initDynamicTable1D();
		output(doDynamic1D());
		initDynamicTable2D();
		output(doDynamic2D());
		initMemoiTable();
		output(Memoi(N, M));
		sc.close();
	}
	
	static void printTable1D(int[] d) {
		System.out.println("---------------------------------------");
		for(int m=1; m<=M; m++)
			System.out.printf("%2d ", d[m]);
		System.out.println();
	}
	static void printTable2D(int[][] d) {
		System.out.println("---------------------------------------");
		for(int n=1; n<=N; n++) {
			for(int m=1; m<=M; m++)
				System.out.printf("%2d ", d[n][m]);
			System.out.println();
		}
	}

}
