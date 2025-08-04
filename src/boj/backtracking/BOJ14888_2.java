package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_2 {

    static int N;
    static int[] nums;
    static int[] ops = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void solve(int index, int result) {
        if (index == N - 1) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (ops[i] <= 0) continue;
            --ops[i];
            solve(index + 1, operate(index + 1, i, result));
            ++ops[i];
        }
    }

    private static int operate(int index, int op, int result) {
        return switch (op) {
            case 0 -> result + nums[index];
            case 1 -> result - nums[index];
            case 2 -> result * nums[index];
            case 3 -> result / nums[index];
            default -> 0;
        };
    }
}
