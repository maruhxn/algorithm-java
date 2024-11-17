package algospot.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KaratsubaMultiply {

    static List<Integer> A = new ArrayList<>();
    static List<Integer> B = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        A = A.reversed();
        B = B.reversed();

        System.out.println(multiply(A, B));;
    }

    private static int[] multiply(List<Integer> a, List<Integer> b) {
        int[] c = new int[a.size() + b.size() + 1];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                c[i + j] += a.get(i) * b.get(j);
            }
        }
        normalize(c);
        return c;
    }

    private static void normalize(int[] c) {

    }


}
