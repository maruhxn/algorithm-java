package ch3.array_list;

// 공 넣기(B3)
import java.util.Scanner;

/**
 * 1 2 3 4 5
 * 3 3 0 0 0
 * 3 3 4 4 0
 * 1 1 1 1 0
 * 1 2 1 1 0
 */
public class BOJ10810 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < m; i++) {
            int st = sc.nextInt();
            int ed = sc.nextInt();
            int num = sc.nextInt();
            for (int j = st - 1; j < ed; j++) {
                A[j] = num;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
