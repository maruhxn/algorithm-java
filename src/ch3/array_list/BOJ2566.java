package ch3.array_list;

import java.util.Scanner;

// 최댓값(B3)
public class BOJ2566 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] maxIdx = {1, 1};
        int maxVal = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                int num = sc.nextInt();
                if (maxVal < num) {
                    maxIdx[0] = i;
                    maxIdx[1] = j;
                    maxVal = num;
                }
            }
        }

        System.out.println(maxVal);
        System.out.print(maxIdx[0] + " " + maxIdx[1]);
    }
}
