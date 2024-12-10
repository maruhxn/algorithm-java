package algospot.ch30;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실패..
public class DRUNKEN {

    static final int INF = 987654321;
    static int V, E;
    static int[][] graph;

    // W[i][j] = i에서 j로 가는 최소 예상 시간
    static int[][] W;
    static Pair[] delay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 장소의 수
        E = Integer.parseInt(st.nextToken()); // 도로의 수

        graph = new int[V + 1][V + 1];
        W = new int[V + 1][V + 1];
        delay = new Pair[V];

        for (int i = 1; i <= V; i++) {
            Arrays.fill(graph[i], INF);
            Arrays.fill(W[i], INF);
            graph[i][i] = W[i][i] = 0;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            int time = Integer.parseInt(st.nextToken());
            delay[i] = new Pair(time, i + 1);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a][b] = W[a][b] = c;
            graph[b][a] = W[b][a] = c;
        }

        calcMaxExpectDist();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.append(W[start][end] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void calcMaxExpectDist() {
        Arrays.sort(delay);

        for (int k = 0; k < V; k++) {
            int delayTime = delay[k].time;
            int viaIdx = delay[k].index;

            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][viaIdx] + graph[viaIdx][j]);
                    W[i][j] = Math.min(W[i][j], graph[i][viaIdx] + graph[viaIdx][j] + delayTime);
                }
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int time, index;

        Pair(int time, int index) {
            this.time = time;
            this.index = index;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.time, other.time);
        }
    }
}
