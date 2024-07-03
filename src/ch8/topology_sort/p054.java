package ch8.topology_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 게임 개발(G3)
public class p054 {

    static ArrayList<Integer>[] buildings;
    static int[] degree; // 진입 차수 배열(해당 건물을 짓기 위해 필요한 건물의 남은 개수)
    static int[] result; // 결과 배열(건물에 저장된 최대 시간 배열)
    static int[] times; // 소요 시간 정보 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        buildings = new ArrayList[n + 1];
        degree = new int[n + 1];
        result = new int[n + 1];
        times = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            buildings[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= n; i++) {
            int time = sc.nextInt();
            times[i] = time;
            while (true) {
                int prevBuilding = sc.nextInt();
                if (prevBuilding == -1) break;
                buildings[prevBuilding].add(i);
                degree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            Integer polled = queue.poll();
            for (int next : buildings[polled]) {
                degree[next]--;
                result[next] = Math.max(result[next], result[polled] + times[polled]); // 중요
                if (degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            result[i] += times[i];
            System.out.println(result[i]);
        }
    }
}