package ch3.array_list;

import java.util.Scanner;

// 바구니 뒤집기(B2)
public class BOJ10811 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] A = new int[n];
        int[] reverse = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = i + 1;
            reverse[i] = i + 1;
        }
        for (int k = 0; k < m; k++) {
            int st = sc.nextInt() - 1;
            int ed = sc.nextInt() - 1;
            for (int i = ed, j = st; i >= st; i--, j++) {
                reverse[j] = A[i];
            }
            for (int i = st; i <= ed; i++) {
                A[i] = reverse[i];
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(reverse[i] + " ");
        }
    }
}
