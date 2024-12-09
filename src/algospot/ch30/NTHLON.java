package algospot.ch30;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 걸리는 시간의 총 합이 갖아 짧은 코스 찾기
 * 한 종목을 여러 번 코스에 추가할 수 있으며, 아예 추가하지 않을 수도 있음
 */
public class NTHLON {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            int M = Integer.parseInt(br.readLine());

            StringTokenizer st;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
            }
        }
    }
}
