package algospot.ch28;

import java.util.ArrayList;
import java.util.List;

public class EulerCircuit {

    static int[][] graph; // 인접 행렬로 그래프 표현
    static List<Integer> circuit = new ArrayList<>();

    // 오일러 서킷을 구하는 DFS
    public static void findEulerCircuit(int node) {
        for (int i = 0; i < graph.length; i++) {
            while (graph[node][i] > 0) { // 간선이 존재하면
                graph[node][i]--; // 간선을 사용
                graph[i][node]--; // 무향 그래프이므로 대칭적으로 간선 제거
                findEulerCircuit(i); // 다음 정점으로 이동
            }
        }
        circuit.add(node); // 역순으로 결과 추가
    }

    // 그래프가 오일러 서킷 조건을 만족하는지 확인
    public static boolean isEulerian() {
        // 모든 정점의 차수가 짝수인지 확인
        for (int i = 0; i < graph.length; i++) {
            int degree = 0;
            for (int j = 0; j < graph[i].length; j++) {
                degree += graph[i][j];
            }
            if (degree % 2 != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // 예제 그래프: 무향 그래프
        graph = new int[][]{
                {0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0}
        };


        if (!isEulerian()) {
            System.out.println("The graph does not have an Eulerian Circuit.");
            return;
        }

        // 오일러 서킷 찾기
        findEulerCircuit(0);

        // 역순으로 결과 출력
        System.out.println("Eulerian Circuit:");
        for (int i = circuit.size() - 1; i >= 0; i--) {
            System.out.print(circuit.get(i) + " ");
        }
    }
}