package boj.permutationAndCombination;

import java.util.Scanner;

public class BOJ10974 {
    static int N;
    static boolean[] taken;
    static int[] curr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        taken = new boolean[N];
        curr = new int[N];

        permutation(0);
        System.out.println(sb.toString());
    }

    private static void permutation(int depth) {
        if (depth == N) {
            for (int num : curr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < taken.length; i++) {
            if (taken[i]) continue;
            taken[i] = true;
            curr[depth] = i + 1;
            permutation(depth + 1);
            taken[i] = false;
        }
    }
}
