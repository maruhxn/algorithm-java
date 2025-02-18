package boj.permutationAndCombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2798 {
    static int N, M, ret, sum;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        System.out.println(ret);
    }

//    private static void solve(int index, int depth) {
//        if (depth == 3) {
//            ret = Math.max(ret, sum);
//            return;
//        }
//
//        for (int i = index; i < N; ++i) {
//            if (sum + nums[i] > M) continue;
//            sum += nums[i];
//            solve(i + 1, depth + 1);
//            sum -= nums[i];
//        }
//    }

    private static void solve(int index, int depth) {
        if (depth == 3) {
            if (sum <= M) ret = Math.max(ret, sum);
            return;
        }

        if (index >= N) return;

        // 뽑지 않음
        solve(index + 1, depth);
        sum += nums[index];
        solve(index + 1, depth + 1);
        sum -= nums[index];
    }
}
