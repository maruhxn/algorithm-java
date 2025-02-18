package boj.permutationAndCombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class PermutationTest {
    static int N, M;
    static List<Integer> result;
    static boolean[] taken;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new ArrayList<>();
        taken = new boolean[N];

        permutation(0);
        System.out.println(sb.toString());
    }

    private static void permutation(int depth) {
        if (!result.isEmpty()) {
            for (int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 0; i < N; i++) {
            if (taken[i]) continue;

            taken[i] = true;
            result.add(i + 1);
            permutation(depth + 1);
            result.remove(result.size() - 1);
            taken[i] = false;
        }
    }
}
