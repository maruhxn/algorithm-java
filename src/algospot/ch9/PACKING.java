package algospot.ch9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PACKING {

    static int N, W;
    static Item[] items;
    static int[][] cache;
    static List<Integer> choices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            items = new Item[N];
            cache = new int[1001][100];
            choices = new ArrayList<>();

            for (int i = 0; i < 1001; i++) {
                for (int j = 0; j < 100; j++) {
                    cache[i][j] = -1;
                }
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                int weight = Integer.parseInt(st.nextToken());
                int priority = Integer.parseInt(st.nextToken());

                items[i] = new Item(name, weight, priority);
            }

            reconstruct(W, 0);

            bw.append(packing(W, 0) + " " + choices.size() + "\n");

            for (int i = 0; i < choices.size(); i++) {
                bw.append(items[choices.get(i)].name + "\n");
            }

        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 남은 용량이 capacity일 때, item 이후의 물걸들을 담아 얻을 수 있는 최대 절박도의 합 반환
    static int packing(int capacity, int item) {
        // 기저 사례: 더 담을 물건이 없을 때
        if (item == N) return 0;
        int ret = cache[capacity][item];
        if (ret != -1) return ret;

        // 이 물건을 담지 않을 경우
        ret = packing(capacity, item + 1);
        // 이 물건을 담을 경우
        if (capacity >= items[item].weight) {
            ret = Math.max(ret, packing(capacity - items[item].weight, item + 1) + items[item].priority);
        }

        return cache[capacity][item] = ret;
    }

    static void reconstruct(int capacity, int item) {
        // 기저 사례: 모든 물건을 다 고려했음
        if (item == N) return;

        if (packing(capacity, item) == packing(capacity, item + 1)) {
            // item을 선택하지 않았을 경우
            reconstruct(capacity, item + 1);
        } else {
            choices.add(item);
            reconstruct(capacity - items[item].weight, item + 1);
        }
    }

    static class Item {
        String name;
        int weight;
        int priority;

        public Item(String name, int weight, int priority) {
            this.name = name;
            this.weight = weight;
            this.priority = priority;
        }
    }
}
