package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ32341 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마디 수
        double bpm = Double.parseDouble(st.nextToken()); // 초기 BPM
        int K = Integer.parseInt(st.nextToken()); // 변속 수

        int prevM = 1;
        double ret = 0.0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            int bCnt = (M - prevM) * 4;
            ret += (60 * bCnt) / bpm;

            bpm = Double.parseDouble(st.nextToken());
            prevM = M;
        }

        int bCnt = (N + 1 - prevM) * 4;
        ret += (60 * bCnt) / bpm;

        System.out.printf("%.12f", ret);
    }
}

// ㅁㅏ디
// 1 마디 = 4박
// BPM = 1분 동안 박의 수
// BPM은 변속될 수 있음 (마디 시작 시)