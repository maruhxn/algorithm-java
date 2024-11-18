package boj.divideandconquer;

import java.util.Scanner;

// Moo 게임
public class BOJ5904_2 {

    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int len = 3;
        int root = 3;
        while (n > len) {
            len = len * 2 + (++root);
        }

        solve(len, root);

        //결과
        if (n == 1) System.out.println('m');
        else System.out.println('o');
    }

    private static void solve(int len, int root) {
        int prevLength = (len - root) / 2; // 앞 부분의 길이가 곧 이전 단계의 길이

        if (n <= prevLength) solve(prevLength, root - 1);
        else if (n > prevLength + root) {
            n -= (prevLength + root);
            solve(prevLength, root - 1);
        } else {
            n -= prevLength;
        }
    }
}
/**
 * 한 단계씩 줄어들 때마다 중간 부분(root)의 크기가 1씩 줄어들기만 하면 됨
 */
