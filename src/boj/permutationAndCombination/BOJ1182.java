package boj.permutationAndCombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
    static int N, S, ret;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);
        if(S == 0) --ret; // S가 0이면, 아무것도 선택하지 않은 경우(공집합) 처리해주어야 함

        System.out.println(ret);
    }

    private static void solve(int depth, int sum) {
        if (depth == N) {
            if (sum == S) ++ret;
            return;
        }

        solve(depth + 1, sum);
        solve(depth + 1, sum + nums[depth]);
    }
}
