package algospot.ch24;

import java.io.*;
import java.util.StringTokenizer;

// 이해가 안되눙..
public class MEASURETIME {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            FenwickTree tree = new FenwickTree(1000000);
            long ret = 0;

            for (int i = 0; i < A.length; i++) {
                ret += tree.prefixSum(999999) - tree.prefixSum(A[i]);
                tree.update(A[i], 1);
            }

            System.out.println(ret);
        }
    }
}
