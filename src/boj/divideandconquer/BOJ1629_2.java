package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629_2 {

    static int A, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(multiply(B));
    }

    private static long multiply(int t) {
        if (t == 1) return A % C;

        long k = multiply(t / 2) % C;

        if (t % 2 == 1) return (((k * k) % C) * A) % C;
        return (k * k) % C;
    }
}
