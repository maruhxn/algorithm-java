package ch8.minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다리 만들기 2(G1)
public class p065 {
    static int n, m, currIslandNum, useEdge, result;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static PriorityQueue<Edge> queue;
    static int[] parent;
    static ArrayList<int[]> mList;
    static ArrayList<ArrayList<int[]>> sumList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        currIslandNum = 1;
        sumList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 || visited[i][j]) continue;
                BFS(i, j);
                currIslandNum++;
                sumList.add(mList);
            }
        }

        queue = new PriorityQueue<>();
        // 에지 리스트 초기화 -> 섬의 각 지점에서 만들 수 잇는 모든 에지를 저장
        for (int i = 0; i < sumList.size(); i++) {
            ArrayList<int[]> nowSum = sumList.get(i);
            for (int j = 0; j < nowSum.size(); j++) {
                int[] now = nowSum.get(j);
                int nowSumNum = board[now[0]][now[1]];
                for (int dir = 0; dir < 4; dir++) {
                    int nx = now[0] + dx[dir];
                    int ny = now[1] + dy[dir];
                    int length = 0;
                    while (true) {
                        if(nx < 0 | nx >= n | ny < 0 | ny >= m) break;
                        if(board[nx][ny] == nowSumNum) break; // 같은 섬이면 에지 연결 X
                        if (board[nx][ny] == 0) { // 바다면 다리 연장
                            length++;
                        }
                        if(board[nx][ny] != 0) {// 다른 섬이고 바다도 아니면
                            if(length > 1) queue.add(new Edge(nowSumNum, board[nx][ny], length));
                            break;
                        }

                        nx += dx[dir];
                        ny += dy[dir];
                    }
                }
            }
        }

        // 최소 스패닝 트리 로직 시작
        parent = new int[currIslandNum];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        while (!queue.isEmpty()) {
            Edge now = queue.poll();
            if (find(now.start) != find(now.end)) {
                union(now.start, now.end);
                result += now.weight;
                useEdge++;
            }
        }

        if (useEdge == currIslandNum - 2) { // 에지의 개수가 노드의 개수 - 1개가 됐다면,
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        mList = new ArrayList<>();
        addNode(i, j, queue);
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = now[0] + dx[dir];
                int ny = now[1] + dy[dir];
                if (nx < 0 | nx >= n | ny < 0 | ny >= m) continue;
                if (board[nx][ny] != 0 && !visited[nx][ny]) { // 0이고 방문한적 없어야함.
                    addNode(nx, ny, queue);
                }
            }
        }
    }

    private static void addNode(int i, int j, Queue<int[]> queue) {
        int[] node = {i, j};
        queue.add(node);
        mList.add(node);
        board[i][j] = currIslandNum;
        visited[i][j] = true;
    }

    static int find(int v) {
        if (parent[v] == v) return v;
        else return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
