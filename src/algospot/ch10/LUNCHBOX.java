package algospot.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 최초 시도: 데우는 시간이 빠른 순 - >실패
 * 정답: 데우는 시간이 느린 순
 */
public class LUNCHBOX {

    static int N;

    static List<Pair> pairs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            pairs = new ArrayList<Pair>();

            int totalWarmTime = 0;
            for (int i = 0; i < N; i++) {
                int t = Integer.parseInt(st.nextToken());
                totalWarmTime += t;
                pairs.add(new Pair(t));
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pairs.get(i).eatTime = Integer.parseInt(st.nextToken());
            }

            pairs.sort(Comparator.comparingInt((Pair pair) -> pair.eatTime).reversed());

            int currWarmTime = 0;
            int result = totalWarmTime;
            for (int i = 0; i < N; i++) {
                Pair curr = pairs.get(i);
                currWarmTime += curr.warmTime;
                if (result < currWarmTime + curr.eatTime) result = currWarmTime + curr.eatTime;
            }

            System.out.println(result);
        }
    }

    static class Pair {
        int warmTime;
        int eatTime;

        public Pair(int warmTime) {
            this.warmTime = warmTime;
        }
    }
}
