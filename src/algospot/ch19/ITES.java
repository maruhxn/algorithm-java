package algospot.ch19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ITES {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int result = 0;
            long prevSignal = 1983;

            Queue<Integer> queue = new LinkedList<>();

            long rangeSum = 0;

            for (int i = 0; i < N; ++i) {
                // 구간에 숫자 추가
                int newSignal = (int) (prevSignal % 10000 + 1);
                rangeSum += newSignal;
                queue.add(newSignal);

                // 구간의 랍이 k를 초과하는 동안 구간에서 숫자를 뺀다
                while (rangeSum > K) {
                    rangeSum -= queue.poll();
                }

                if (rangeSum == K) result++;

                prevSignal = (prevSignal * 214013 + 2531011) % (long) (Math.pow(2, 32));
            }

            System.out.println(result);
        }
    }

}
