package boj.swm;

import javax.sql.RowSetInternal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        int ret = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());

            int idx = deque.indexOf(target);

            int mid;
            if (deque.size() % 2 == 0) {
                mid = deque.size() / 2 - 1;
            } else {
                mid = deque.size() / 2;
            }

            if (idx <= mid) {
                for (int j = 0; j < idx; j++) {
                    deque.offerLast(deque.pollFirst());
                    ++ret;
                }
            } else {
                for (int j = 0; j < deque.size() - idx; j++) {
                    deque.offerFirst(deque.pollLast());
                    ++ret;
                }
            }

            deque.pollFirst();
        }

        System.out.println(ret);
    }
}
