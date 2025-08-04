package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912_2 {

    static int[] nums;
    static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        cache = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            cache[i] = Integer.MIN_VALUE;
        }

        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            ret = Math.max(ret, continuousSum(i));
        }

        System.out.println(ret);
    }

    private static int continuousSum(int curr) {
        if (curr == 0) return nums[curr];

        if (cache[curr] != Integer.MIN_VALUE) return cache[curr];
        return cache[curr] = Math.max(nums[curr] + continuousSum(curr - 1), nums[curr]);
    }
}
