package ch4.selection_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 내림차순으로 자릿수 정렬하기(S5)
public class p017 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        int[] a = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            a[i] = Integer.parseInt(str.substring(i, i + 1));
        }

        for (int i = 0; i < a.length; i++) {
            int max_idx = i;
            for (int j = i; j < a.length; j++) {
                if(a[max_idx] < a[j]) max_idx = j;
            }
            if (a[i] < a[max_idx]) {
                int tmp = a[i];
                a[i] = a[max_idx];
                a[max_idx] = tmp;
            }
        }

        for (int i = 0; i < str.length(); i++) {
            System.out.print(a[i]);
        }
    }
}
