package algospot.ch24;

import java.io.*;
import java.util.StringTokenizer;

public class MORDOR_2 {

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

            RMQ rmqMin = new RMQ(arr);
            int[] minusArr = new int[N];
            for (int i = 0; i < N; i++) {
                minusArr[i] = -arr[i];
            }

            RMQ rmqMax = new RMQ(minusArr);

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int min = rmqMin.query(left, right);
                int max = -rmqMax.query(left, right);
                bw.append(String.valueOf(max - min));
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

        public RMQ(int[] array) {
            n = array.length;
            rangeMin = new int[n * 4];
            init(array, 0, n - 1, 1);
        }

        private int init(int[] array, int left, int right, int node) {
            if (left == right) return rangeMin[node] = array[left];

            int mid = (left + right) / 2;
            int leftMin = init(array, left, mid, node * 2);
            int rightMin = init(array, mid + 1, right, node * 2 + 1);

            return rangeMin[node] = Math.min(leftMin, rightMin);
        }

        public int query(int left, int right) {
            return query(left, right, 1, 0, n - 1);
        }

        private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
            // 두 구간이 겹치지 않으면 아주 큰 값을 반환하여 무시함
            if (right < nodeLeft || left > nodeRight) return INT_MAX;

            // node가 표현하는 범위가 array[left..right]에 완전히 포함되는 경우
            if (left <= nodeLeft && right >= nodeRight) return rangeMin[node];

            // 양쪽 구간을 나눠서 푼 뒤 결과를 합친다.
            int mid = (nodeLeft + nodeRight) / 2;
            return Math.min(query(left, right, node * 2, nodeLeft, mid),
                    query(left, right, node * 2 + 1, mid + 1, nodeRight));
        }
    }
}
