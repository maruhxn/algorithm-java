package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실패..
public class JLIS {
    static int n, m;
    static int[] A, B;
    static int[][] cache;
    static long NEGINF = -9876543210L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            A = new int[100];
            B = new int[100];
            cache = new int[n + 1][m + 1];

            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < m + 1; j++) {
                    cache[i][j] = -1;
                }
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(jlis(-1, -1) - 2);
        }
    }

    public static int jlis(int indexA, int indexB) {
        int ret = cache[indexA + 1][indexB + 1];
        if (ret != -1) return ret;

        ret = 2; // JLIS의 최소 길이.. A[indexA], B[indexB]가 이미 존재하므로 2개는 항상 있다.

        long a = indexA == -1 ? NEGINF : A[indexA];
        long b = indexB == -1 ? NEGINF : B[indexB];
        long maxElement = Math.max(a, b);

        // 다음 원소를 찾는다.
        for (int nextA = indexA + 1; nextA < n; nextA++) {
            if (maxElement < A[nextA])
                ret = Math.max(ret, jlis(nextA, indexB) + 1);
        }

        for (int nextB = indexB + 1; nextB < m; nextB++) {
            if (maxElement < B[nextB])
                ret = Math.max(ret, jlis(indexA, nextB) + 1);
        }

        return cache[indexA + 1][indexB + 1] = ret;
    }
}
