//package D0;

/*
입력 예
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000

출력 예
3
7
8
9
 */
 
import java.util.Arrays;
import java.util.Scanner;


public class apt {
	
	private static int N; 
	private static int[][] a; 
	static int[][] visit; 
	
	private static int gCnt;
	private static int[] group;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };

	static void input(Scanner sc) {
		N = sc.nextInt();
		a = new int[N+2][N+2];
		group = new int[N+2];
		visit = new int[N+2][N+2];
		
		for(int i=1; i<=N; i++) {
			 char[] t = ("<" + sc.next() + ">").toCharArray();  
			/* char[] t = (sc.next()).toCharArray();  */
			
			System.out.println("i="+i+" "+t[0]+t[1]+t[2]+t[3]+t[4]+t[5]+t[6]+t[7]+t[8]);
			
			for(int j=1; j<=N; j++) {
				a[i][j] = t[j] - '0';
				//System.out.println("a["+i+"]["+j+"]"+"="+a[i][j]);
			}
			
		}
		System.out.println("-->gCnt : "+ gCnt);
	}
	
	static void output() {
		System.out.println(gCnt);
		//Arrays.sort(group);
		//simpleSort(0, 2);
		simpleSort();
		for(int g=0; g<gCnt; g++)
			System.out.println(group[g]);
	}
	
	static void printAry() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
	}
	
	static void simpleSort() {
		for(int i=0; i<gCnt-1; i++) {
			for(int j=i+1; j<gCnt; j++) {
				if(group[i] > group[j]) {
					int t = group[i];
					group[i] = group[j];
					group[j] = t;
				}
			}
		}
	}
	
	static void simpleSort(int s, int c) {
		for(int i=s; i<s+c; i++) {
			for(int j=i+1; j<gCnt; j++) {
				if(group[i] > group[j]) {
					int t = group[i];
					group[i] = group[j];
					group[j] = t;
				}
			}
		}
	}
	
	/*
	// 1. 프로토타입 작성
	//    - 리턴형 : int 성공(1), 실패(0) -> 찾고자 하는 결과값은 리턴형으로 사용하지 않고 전역변수로 사용
	//            void : 모두 경우의 수를 수행해야 할 경우
	//    - 인수리스트 : 반드시 종료 조건(int L)이 있어야 하고 이전 레벨의 어떤 상태 정보를 사용할 경우 인수 추가
	// 2. 리턴형이 int이면 함수 마지막에 return 0(실패)
	// 3. depth 까지만 수행되도록 종료 조건 작성
	// 4. 재귀호출문 및 결과값을 찾고자 하는 조건 검색 작성

	// Flood Fill은 DFS의 특징인 backtracking이 없는 재귀 형태이다.
	// return을 넣어 돌려 보내는 구조는 성능이 좋지 않음

*/

	static void FF_DFS(int r, int c) {
		int nr, nc;
		
		visit[r][c] = 1;
		group[gCnt]++; 
		
		System.out.println("-->group["+gCnt+"]="+group[gCnt]);
		
		for(int i=0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr < 1 || nc < 1 || nr > N || nc > N) continue;
			if(visit[nr][nc] == 1) continue;
			if(a[nr][nc] == 0) continue;
			FF_DFS(nr, nc);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		input(sc);
		//printAry();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(visit[i][j] == 0 && a[i][j] == 1) {
					FF_DFS(i, j);
					gCnt++;
					System.out.println("-->gCnt = "+gCnt);
				}
			}
		}
		output();
		sc.close();
	}

}
