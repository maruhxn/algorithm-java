package boj.permutationAndCombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {
    static int N, M;
    static int[] result;
    static boolean[] taken;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];
        taken = new boolean[N];

        permutation(0);
        System.out.println(sb.toString());
    }

    private static void permutation(int depth) {
        if (depth == M) {
            for(int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if(taken[i]) continue;

            taken[i] = true;
            result[depth] = i + 1;
            permutation(depth + 1);
            taken[i] = false;
        }
    }
}
