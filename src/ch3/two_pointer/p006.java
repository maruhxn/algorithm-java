package ch3.two_pointer;

import java.util.Scanner;

// 연속된 자연수의 합 구하기(S5)
public class p006 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 1;
        int count = 1;
        int st = 1;
        int ed = 1;

        while (ed < n) {
            if (sum < n) {
                ed++;
                sum += ed;
            } else if (sum > n) {
                sum -= st;
                st++;
            } else {
                count++;
                ed++;
                sum += ed;
            }
        }

        System.out.println(count);
    }
}
