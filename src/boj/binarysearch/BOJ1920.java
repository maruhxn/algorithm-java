package boj.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {
    static int[] A;
    static int target;

//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int N = Integer.parseInt(br.readLine());
//        A = new int[N];
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            A[i] = Integer.parseInt(st.nextToken());
//        }
//        int M = Integer.parseInt(br.readLine());
//        st = new StringTokenizer(br.readLine());
//        Arrays.sort(A);
//        while (M-- > 0) {
//            int ok = Arrays.binarySearch(A, Integer.parseInt(st.nextToken()));
//            if (ok >= 0) {
//                sb.append(1);
//            } else {
//                sb.append(0);
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Arrays.sort(A);
        while (M-- > 0) {
            target = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(0, N)).append("\n");
        }

        System.out.println(sb);
    }

    static int binarySearch(int st, int ed) {
        if(st >= ed) return 0;
        int mid = (st + ed) / 2;
        if (A[mid] == target) return 1;
        else if (A[mid] > target) {
            return binarySearch(st, mid);
        } else {
            return binarySearch(mid + 1, ed);
        }
    }
}

// 1 2 3 4 5