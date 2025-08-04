package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205 {
    static int n;  // 편의점 개수
    static Point[] points;  // 좌표 저장용
    static boolean[] visited;  // 방문 체크용

    // 좌표를 담을 클래스
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            points = new Point[n + 2];  // 상근이의 집, 편의점 n개, 페스티벌 총 (n+2)개
            visited = new boolean[n + 2];

            // 좌표 입력 받기
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }

            // BFS 탐색 시작
            if (bfs()) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }

        System.out.print(sb);
    }

    // BFS 탐색
    static boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);  // 상근이의 집에서 출발
        visited[0] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 페스티벌 도착 시
            if (current == n + 1) {
                return true;
            }

            // 인접한 정점 탐색
            for (int next = 0; next < n + 2; next++) {
                if (!visited[next] && canReach(points[current], points[next])) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        // 페스티벌에 도착하지 못하면
        return false;
    }

    // 두 좌표 간 거리가 1000m 이하인지 체크 (맨해튼 거리)
    static boolean canReach(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y) <= 1000;
    }
}
