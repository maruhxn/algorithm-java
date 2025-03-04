package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12865 {

    static int N, K;
    static Item[] items;
    static int[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new Item[N];
        cache = new int[N][K + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new Item(weight, value);
        }

        System.out.println(knapsack(N - 1, K));
    }

    // 수용 가능한 무게가 k일 때, curr까지 탐색할 경우 얻을 수 있는 최대 가치
    static int knapsack(int curr, int k) {
        if (curr < 0) return 0;
        if (cache[curr][k] != -1) return cache[curr][k];

        int ret = knapsack(curr - 1, k); // 현재 물건을 담을 수 없는 경우(이전 값 탐색)
        if (items[curr].weight <= k) // 현재 물건을 담을 수 있는 경우
            ret = Math.max(ret, knapsack(curr - 1, k - items[curr].weight) + items[curr].value);

        return cache[curr][k] = ret;
    }

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
