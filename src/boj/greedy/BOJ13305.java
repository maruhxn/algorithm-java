package boj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13305 {

    static int[] length;
    static int[] price;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        length = new int[N - 1];
        price = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            length[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        // 매 순간. 현재 도시 이후 더 싼 도시가 있다면, 해당 도시까지 갈 정도로만 구매
        // 없다면, 현재 도시에서 다 사기

        long ret = 0;
        int curr = 0;
        while (curr < N - 1) {
            int minPriceIdx = curr;
            int lengthSum = 0;
            for (int next = curr + 1; next < N; next++) {
                lengthSum += length[next - 1];
                if (price[curr] >= price[next]) {
                    // 더 작은 애를 찾았을 경우, 해당 도시까지 갈 정도로만 구매하고 이동
                    minPriceIdx = next;
                    ret += (long) lengthSum * price[curr];
                    break;
                }
            }
            if (minPriceIdx == curr) { // 더 작은 애를 못 찾았을 경우, 현재 도시에서 전부 사기
                ret += (long) lengthSum * price[curr];
                break;
            }
            curr = minPriceIdx;
        }

        System.out.println(ret);
    }
}


// 최소 비용일 때, 최대한 많이 사기 -> 얼마나?
// -> 남은 도시 중 가장 금액이 싼 도시에서 이후 도시 중 가장 싼 도시까지의 거리만큼
// -> 이걸 반복
// 최대 비용이면, 딱 다음 도시까지 갈 정도만 사기