package ch3.array_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 평균 구하기
public class p002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int a[] = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;
        long max = 0;

        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) max = a[i];
            sum += a[i];
        }

        System.out.println(sum * 100.0 / max / n);

    }
}
