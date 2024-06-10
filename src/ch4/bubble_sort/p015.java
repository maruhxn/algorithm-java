package ch4.bubble_sort;

import java.util.Scanner;

// 수 정렬하기(B2)
public class p015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = n - 1; i > 0; i--) {
            boolean isSwapped = false;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j);
                    isSwapped = true;
                }
            }
            if(!isSwapped) break;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
    }

    private static void swap(int[] a, int j) {
        int tmp = a[j];
        a[j] = a[j + 1];
        a[j + 1] = tmp;
    }
}

