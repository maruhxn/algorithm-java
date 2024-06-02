package ch3.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 나머지 합 구하기(G3)

/**
 * 나머지 계산 방식 독특함.. -> 각 원소의 나머지가 같으면 두 원소의 차의 나머지는 0 (S[j] - S[i]) % M = 0
 * 나머지 값이 같은 합 배열의 개수를 세는 방식 독특! -> 조합 배열(c[]) 이용
 */
public class p005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long result = 0;
        long d[] = new long[n + 1];
        long c[] = new long[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            int remainder = (int) (d[i] % m);
            if (remainder == 0) result += 1;
            c[remainder]++;
        }

        for (int i = 0; i < m; i++) {
            if (c[i] > 1) {
                result = result + (c[i] * (c[i] - 1) / 2);
            }
        }

        System.out.println(result);
    }
}