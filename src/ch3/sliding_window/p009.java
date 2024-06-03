package ch3.sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DNA 비밀번호(S2)
public class p009 {
    static int check[];
    static int curr[];
    static int checkSecret;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int result = 0;
        char[] a = bf.readLine().toCharArray();
        st = new StringTokenizer(bf.readLine());

        curr = new int[4];  // A C G T
        check = new int[4]; // A C G T

        for (int i = 0; i < 4; i++) { // check 배열 업데이트
            check[i] = Integer.parseInt(st.nextToken());
            if (check[i] == 0) {
                checkSecret++;
            }
        }

        for (int i = 0; i < p; i++) { // 최초 윈도우 처리
            add(a[i]);
        }
        if (checkSecret == 4) result++;

        // 슬라이딩 윈도우 처리
        for (int i = p; i < s; i++) {
            int j = i - p;
            add(a[i]);
            remove(a[j]);
            if (checkSecret == 4) result++;
        }
        System.out.println(result);

        bf.close();
    }

    private static void remove(char c) {
        switch (c) {
            case 'A':
                if (curr[0] == check[0]) checkSecret--;
                curr[0]--;
                break;
            case 'C':
                if (curr[1] == check[1]) checkSecret--;
                curr[1]--;
                break;
            case 'G':
                if (curr[2] == check[2]) checkSecret--;
                curr[2]--;
                break;
            case 'T':
                if (curr[3] == check[3]) checkSecret--;
                curr[3]--;
                break;
        }
    }

    private static void add(char c) {
        switch (c) {
            case 'A':
                curr[0]++;
                if (curr[0] == check[0]) checkSecret++;
                break;
            case 'C':
                curr[1]++;
                if (curr[1] == check[1]) checkSecret++;
                break;
            case 'G':
                curr[2]++;
                if (curr[2] == check[2]) checkSecret++;
                break;
            case 'T':
                curr[3]++;
                if (curr[3] == check[3]) checkSecret++;
                break;
        }
    }
}
