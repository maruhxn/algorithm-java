package boj.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 우선순위 무시
// 나눗셈은 정수 나눗셈으로 몫만 취하기
// 음수 / 양수일 경우, 음수를 양수로 바꾼 뒤 몫을 취하고 다시 음수로 바꾸기
public class BOJ14888 {
    static int N, max, min;
    static int[] num;
    static int[] opCnt = new int[4]; // + - * /

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[N];

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opCnt[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        execute(1, num[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void execute(int depth, int prev) {
        if (depth == N) {
            max = Math.max(max, prev);
            min = Math.min(min, prev);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (opCnt[i] > 0) {
                opCnt[i]--;
                execute(depth + 1, calculate(prev, i, depth));
                opCnt[i]++;
            }
        }
    }

    private static int calculate(int prev, int opIdx, int depth) {
        int ret = prev;
        // 이전 결과에 이번 결과 계산
        if (opIdx == 0) {
            ret += num[depth];
        } else if (opIdx == 1) {
            ret -= num[depth];
        } else if (opIdx == 2) {
            ret *= num[depth];
        } else {
            if (ret < 0) {
                ret = -(-ret / num[depth]);
            } else {
                ret /= num[depth];
            }
        }
        return ret;
    }
}
