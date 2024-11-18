package boj.divideandconquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Moo 게임
public class BOJ5904 {

    static List<Integer> length = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int k = 0;
        length.add(3);

        while (true) {
            int prev = length.get(k);
            if (n > prev) {
                k++;
                length.add(2 * prev + k + 3);
            } else break;
        }

        solve(k, n - 1);
    }

    private static void solve(int n, int index) {
        if (n == 0) {
            System.out.println("moo".charAt(index));
            return;
        }

        int prevLength = length.get(n - 1);

        if (index < prevLength) solve(n - 1, index);
        else if (index >= prevLength && index < prevLength + n + 3) {
            int target = index - prevLength;
            System.out.println(target == 0 ? "m" : "o");
        } else solve(n - 1, index - prevLength - n - 3);

    }
}
