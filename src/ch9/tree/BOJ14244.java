package ch9.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리 만들기(S4)
public class BOJ14244 {

    static ArrayList<Integer>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); // m = 리프의 수 = 트리의 최대 차수

        A = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            A[0].add(i);
            A[i].add(0);
            bw.append("0 " + i + "\n");
        }

        for (int i = m + 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int size = A[j].size();
                if (size == 1) {
                    A[j].add(i);
                    A[i].add(j);
                    bw.append(j + " " + i + "\n");
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
