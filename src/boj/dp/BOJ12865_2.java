package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12865_2 {

    static int N, K;
    static Item[] items;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        items = new Item[N];
        cache = new int[N][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i] = new Item(w, v);
            Arrays.fill(cache[i], -1);
        }

        System.out.println(knapsack(N - 1, K));
    }

    // 0부터 curr번째까지의 아이템을 고려하면서, 수용 가능 무게가 k일 때 얻을 수 있는 최대 가치를 반환
    static int knapsack(int curr, int k) {
        if (curr < 0) return 0;
        if (cache[curr][k] != -1) return cache[curr][k];

        int ret = knapsack(curr - 1, k);
        if (k >= items[curr].weight)
            ret = Math.max(items[curr].value + knapsack(curr - 1, k - items[curr].weight), ret);
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
