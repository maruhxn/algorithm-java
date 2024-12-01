package algospot.ch23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 이 문제는 트립으로도 해결할 수 있음! 원소의 추가/삭제, kth 원소 찾기가 모두 O(NlgN)에 수행되기 때문!
 * 하지만 너무 귀찮다.. 그리고 더 빠른 방법이 있는데 그것이 우선순위 큐임.
 * 우선순위 큐는 내부적으로 배열을 통해 구현되기 때문에 포인터를 사용하는 트립보다 더 빠른 수행 시간을 보임
 */
public class RUNNINGMEDIAN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long prevSignal = 1983;

            PriorityQueue<Long> minHeap = new PriorityQueue<>();
            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            long result = 0;

            for (int i = 0; i < N; i++) {
                if (maxHeap.size() == minHeap.size()) maxHeap.add(prevSignal);
                else minHeap.add(prevSignal);

                if (!minHeap.isEmpty() && !maxHeap.isEmpty()
                        && minHeap.peek() < maxHeap.peek()) {
                    Long minTop = minHeap.poll();
                    Long maxTop = maxHeap.poll();

                    maxHeap.add(minTop);
                    minHeap.add(maxTop);
                }

                result = (result + maxHeap.peek()) % 20090711;

                prevSignal = (prevSignal * a + b) % 20090711;
            }

            System.out.println(result);
        }
    }
}
