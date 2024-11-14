package algospot.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Picnic1 {

    static int N, M, result;
    static Map<Integer, List<Integer>> friendMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 학생 수
            M = Integer.parseInt(st.nextToken()); // 친구 쌍의 수 ( 0 <= m <= n * (n - 1) / 2)
            result = 0;
            friendMap = new HashMap<>();

            for (int i = 0; i < N; i++) { // friendMap 초기화
                friendMap.put(i, new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                friendMap.get(a).add(b);
                friendMap.get(b).add(a);
            }

            boolean[] remained = new boolean[N];

            for (int i = 0; i < N; i++) {
                remained[i] = true;
            }

            for (int i = 0; i < remained.length; i++) {
                remained[i] = false;
                boolean ok = isOk(1, i, remained);
                remained[i] = true;

                if (ok) break;
            }

            System.out.println(result);
        }
    }

    public static boolean isOk(int depth, int selected, boolean[] remained) {
        List<Integer> friends = friendMap.get(selected);

        for (Integer friend : friends) { // 현재 선택된 학생의 친구들만을 선택
            remained[friend] = false;
            if (depth == N / 2) {
                result++;
                return true;
            } else {
                for (int i = 0; i < remained.length; i++) {
                    if (remained[i]) { // 남아있는 친구들에 대해 수행
                        remained[i] = false;
                        boolean ok = isOk(depth + 1, i, remained);
                        remained[i] = true;

                        if (ok) break;
                    }
                }
            }
            remained[friend] = true;
        }

        return false;
    }
}
