package ch3.array_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 세로읽기(B1)
public class BOJ10798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] A = new char[5][15];
        for (int i = 0; i < 5; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                A[i][j] = chars[j];
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < A.length; j++) {
                if(A[j][i] != 0) System.out.print(A[j][i]);
            }
        }
    }
}