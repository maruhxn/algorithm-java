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

        items = new Item[N + 1];
        cache = new int[N + 1][100001];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(cache[i], -1);
        }

        items[0] = new Item(0, 0);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new Item(weight, value);
        }

        System.out.println(packing(0, 0));
    }

    // currWeight까지 담았을 때, i번째 물건을 담아서 얻을 수 있는 최대 가치
    static int packing(int curr, int currWeight) {
        if (curr == N) return 0;
        int ret = cache[curr][currWeight];
        if (ret != -1) return ret;

        ret = 0;
        for (int i = curr + 1; i <= N; i++) {
            int nextWeight = currWeight + items[i].weight;
            if (nextWeight <= K) {
                ret = Math.max(ret, items[i].value + packing(i, nextWeight));
            }
        }

        return cache[curr][currWeight] = ret;
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
