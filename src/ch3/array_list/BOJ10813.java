package ch3.array_list;

import java.util.Scanner;

// 공 바꾸기(B2)
public class BOJ10813 {
    static int[] A;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = i + 1;
        }

        for (int i = 0; i < m; i++) {
            swap(sc.nextInt() - 1, sc.nextInt() - 1);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + " ");
        }
    }

    private static void swap(int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
