package ch3.two_pointer;

import java.util.Arrays;
import java.util.Scanner;

// 주몽의 명령(S4)
public class p007 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);

        int st = 0;
        int ed = n - 1;
        int count = 0;

        while (st != ed) {                      // st < ed로 변경 필요
            int sum = nums[st] + nums[ed];
            if (sum == m) {
                count++;
                ed--; // st++; 도 해주면 더 빠름. 어차피 나중에 무조건 해주는거라 st++, ed-- 한번에 같이!
            } else if (sum > m) {
                ed--;
            } else {
                st++;
            }
        }

        System.out.println(count);
    }
}
