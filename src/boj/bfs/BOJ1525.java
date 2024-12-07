package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1525 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String start = "";
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start += st.nextToken();
            }
        }
        System.out.println(bfs(start, "123456780"));
    }

    public static int bfs(String start, String finish) {
        // 예외: start == finish인 경우
        if (start.equals(finish)) return 0;

        // 각 정점까지의 최단 경로의 길이를 저장
        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);
        map.put(start, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int cost = map.get(curr);
            int zeroPos = curr.indexOf('0');
            int px = zeroPos % 3;
            int py = zeroPos / 3;

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || ny < 0 || nx > 2 || ny > 2) continue;
                // 위치 바꾸기
                int nPos = ny * 3 + nx;
                char c = curr.charAt(nPos);
                String next = curr.replace(c, 'c');
                next = next.replace('0', c);
                next = next.replace('c', '0');

                if (!map.containsKey(next)) {
                    if (next.equals(finish)) return cost + 1;
                    map.put(next, cost + 1);
                    queue.add(next);
                }
            }
        }

        // 답을 찾지 못한 경우
        return -1;
    }
}