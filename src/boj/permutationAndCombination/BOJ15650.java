package boj.permutationAndCombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650 {
    static int N, M;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];

        combination(0, 0);
        System.out.println(sb.toString());
    }

    private static void combination(int start, int depth) {
        if (depth == M) {
            for(int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            result[depth] = i + 1;
            combination(i + 1, depth + 1);
        }
    }
}
