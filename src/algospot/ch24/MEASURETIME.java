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

            // 펜윅트리에 배열의 값을 하나씩 집어넣기 (원소 삽입을 부분합 1 증가로 표현)
            for (int i = 0; i < A.length; i++) {
                // tree.prefixSum(999999): 현재까지 넣은 원소의 개수
                // tree.prefixSum(A[i]): 현재 원소보다 작은 원소의 개수
                ret += tree.prefixSum(999999) - tree.prefixSum(A[i]);
                tree.update(A[i], 1);
            }

            bw.append(ret + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class FenwickTree {
        int[] tree;

        FenwickTree(int N) {
            tree = new int[N + 1];
        }

        public int prefixSum(int pos) {
            // 인덱스가 1부터 시작한다고 가정
            ++pos;
            int ret = 0;
            while (pos > 0) {
                ret += tree[pos];
                pos -= (pos & -pos); // 다음 구간을 찾기 위해 최종 비트를 지운다
            }

            return ret;
        }

        public void update(int pos, int val) {
            ++pos;
            while (pos < tree.length) {
                tree[pos] += val;
                pos += (pos & -pos);
            }
        }
    }
}
