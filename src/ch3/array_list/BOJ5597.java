package ch3.array_list;

import java.util.Scanner;

// 과제 안 내신 분..?(B3)
public class BOJ5597 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] A = new boolean[30];
        for (int i = 0; i < 28; i++) {
            A[sc.nextInt() - 1] = true;
        }

        for (int i = 0; i < 30; i++) {
            if(!A[i]) System.out.println(i + 1);
        }
    }
}
