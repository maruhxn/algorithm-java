package boj.segmenttree;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2042 {
    private static long[] rangeSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] tempArr = new long[N];
        rangeSum = new long[N * 4];

        for (int i = 0; i < N; i++) {
            tempArr[i] = Long.parseLong(br.readLine());
        }

        init(tempArr, 0, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) update(b - 1, c, 1, 0, N - 1);
            else if (a == 2) bw.append(query(b - 1, (int) c - 1, 1, 0, N - 1) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static long init(long[] array, int left, int right, int node) {
        if (left == right) return rangeSum[node] = array[left];

        int mid = (left + right) / 2;
        long leftSum = init(array, left, mid, node * 2);
        long rightSum = init(array, mid + 1, right, node * 2 + 1);

        return rangeSum[node] = leftSum + rightSum;
    }

    public static long query(int left, int right, int node, int nodeLeft, int nodeRight) {
        if (right < nodeLeft || left > nodeRight) return 0;
        if (left <= nodeLeft && right >= nodeRight) return rangeSum[node];

        int mid = (nodeLeft + nodeRight) / 2;
        return query(left, right, node * 2, nodeLeft, mid) +
                query(left, right, node * 2 + 1, mid + 1, nodeRight);
    }

    public static long update(int index, long newValue, int node, int nodeLeft, int nodeRight) {
        if (index < nodeLeft || index > nodeRight) return rangeSum[node];
        if (nodeLeft == nodeRight) return rangeSum[node] = newValue;

        int mid = (nodeLeft + nodeRight) / 2;
        return rangeSum[node] = update(index, newValue, node * 2, nodeLeft, mid) +
                update(index, newValue, node * 2 + 1, mid + 1, nodeRight);
    }
}
