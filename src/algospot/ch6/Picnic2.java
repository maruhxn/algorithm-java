package algospot.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Picnic2 {

    static int n, m;
    static boolean[][] areFriends = new boolean[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 학생 수
            m = Integer.parseInt(st.nextToken()); // 친구 쌍의 수 ( 0 <= m <= n * (n - 1) / 2)

            areFriends = new boolean[10][10];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                areFriends[a][b] = true;
                areFriends[b][a] = true;
            }

            boolean[] taken = new boolean[n];
            int result = countPairing(taken);

            System.out.println(result);
        }
    }

    public static int countPairing(boolean[] taken) {
        // 남은 학생들 중 가장 번호가 빠른 학생을 찾는다
        int firstFree = -1;

        // 아직 짝지어지지 않은 학생이 있는 경우 끝나지 않음
        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                firstFree = i;
                break;
            }
        }

        // 기저 사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
        // 종료 조건: 모든 원소가 짝지어져서 firstFree 값이 바뀌지 않았음.
        if (firstFree == -1) return 1;

        int ret = 0;

        // 이 학생과 짝지을 학생을 결정한다
        for (int pairWith = firstFree + 1; pairWith < n; pairWith++) {
            if (!taken[pairWith] && areFriends[firstFree][pairWith]) {
                taken[firstFree] = true;
                taken[pairWith] = true;

                ret += countPairing(taken);

                taken[firstFree] = false;
                taken[pairWith] = false;
            }
        }

        return ret;
    }
}

/**
 * 1. 재귀 호출을 사용하면 같은 답을 중복으로 세는 상황이 빈번하게 발생한다. 이를 해결하기 위해서는 항상 '특정 형태를 갖는 답'만을 세는 것이다. 여기서는 중복 답들 중에서 사전순으로 가장 먼저 오는 답 하나만을 세는 방식을 사용하여 중복 세기 문제를 해결했다. 사전순으로 선택하여 경우의 수를 확 줄이는 것이 키포인트!
 * 2. 기저 사례를 생각하는 것이 너무 어렵다.. 기저 사례는 더 이상 쪼개지지 않는 최소한의 작업의 작업을 말하며, 이를 정의하기 위해서는 일단 '작업'을 잘 정의하는 것이 중요하다.
 * 기저사례를 선택할 때는, 모든 입력이 항상 기저 사례의 답을 이용해 계산될 수 있도록 신경써야 한다.
 */
