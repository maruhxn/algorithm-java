package ch3.prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간 합 구하기(S3)
public class p003 {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int a[] = new int[n];
//        long ps[] = new long[n];
//
//        for (int i = 0; i < n; i++) {
//            a[i] = sc.nextInt();
//            if (i == 0) ps[i] = a[i];
//            else ps[i] = ps[i - 1] + a[i];
//        }
//
//        for (int i = 0; i < m; i++) {
//            int st = sc.nextInt();
//            int ed = sc.nextInt();
//
//            long result;
//            if (st > 1) {
//                result = ps[ed - 1] - ps[st - 2];
//            } else {
//                result = ps[ed - 1];
//            }
//            System.out.println(result);
//        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        long s[] = new long[n + 1];

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            System.out.println(s[r] - s[l - 1]);
        }
    }
}
