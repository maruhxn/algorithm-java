package algospot.ch24;

import java.io.*;
import java.util.StringTokenizer;

public class MORDOR {

    private static int INT_MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            RMQ rmq = new RMQ(arr);

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int[] pair = rmq.query(left, right);
                bw.append(String.valueOf(pair[1] - pair[0]));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class RMQ {
        private int n; // 배열의 길이
        private int[] rangeMin;
        private int[] rangeMax;

        public RMQ(int[] array) {
            n = array.length;
            rangeMin = new int[n * 4];
            rangeMax = new int[n * 4];
            init(array, 0, n - 1, 1);
        }

        private int[] init(int[] array, int left, int right, int node) {
            if (left == right) {
                rangeMin[node] = array[left];
                rangeMax[node] = array[left];
                return new int[]{rangeMin[node], rangeMax[node]};
            }

            int mid = (left + right) / 2;
            int[] leftPair = init(array, left, mid, node * 2);
            int[] rightPair = init(array, mid + 1, right, node * 2 + 1);

            rangeMin[node] = Math.min(leftPair[0], rightPair[0]);
            rangeMax[node] = Math.max(leftPair[1], rightPair[1]);

            return new int[]{rangeMin[node], rangeMax[node]};
        }

        public int[] query(int left, int right) {
            return query(left, right, 1, 0, n - 1);
        }

        private int[] query(int left, int right, int node, int nodeLeft, int nodeRight) {
            // 두 구간이 겹치지 않으면 아주 큰 값을 반환하여 무시함
            if (right < nodeLeft || left > nodeRight) return new int[]{INT_MAX, -1};

            // node가 표현하는 범위가 array[left..right]에 완전히 포함되는 경우
            if (left <= nodeLeft && right >= nodeRight) return new int[]{rangeMin[node], rangeMax[node]};

            // 양쪽 구간을 나눠서 푼 뒤 결과를 합친다.
            int mid = (nodeLeft + nodeRight) / 2;
            int[] leftPair = query(left, right, node * 2, nodeLeft, mid);
            int[] rightPair = query(left, right, node * 2 + 1, mid + 1, nodeRight);

            return new int[]{Math.min(leftPair[0], rightPair[0]), Math.max(leftPair[1], rightPair[1])};
        }
    }
}
