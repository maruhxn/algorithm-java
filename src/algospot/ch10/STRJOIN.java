package algospot.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class STRJOIN {

    static int N, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            N = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }

            result = 0;

            while (pq.size() >= 2) {
                int a = pq.poll();
                int b = pq.poll();

                result += a + b;

                pq.add(a + b);
            }

            if (pq.size() == 1) System.out.println(result);
        }
    }
}
