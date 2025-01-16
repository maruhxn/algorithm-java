package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        System.out.println(multiply(B));
    }

    static long multiply(int size) {
        if (size == 1) return A % C;

        long k = multiply(size / 2) % C;

        if (size % 2 == 1) return ((k * k) % C) * A % C;
        else return (k * k) % C;
    }
}

