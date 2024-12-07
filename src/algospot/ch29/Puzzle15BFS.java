package algospot.ch29;

import java.util.*;

public class Puzzle15BFS {

    interface State {
        // 인접한 상태의 목록을 반환한다
        List<State> getAdjacent();
    }

    // start에서 ㄹinish까지 가는 최단 경로의 길이 반환
    public static int bfs(State start, State finish) {
        // 예외: start == finish인 경우
        if (start.equals(finish)) return 0;

        // 각 정점까지의 최단 경로의 길이를 저장
        Map<State, Integer> map = new HashMap<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(start);
        map.put(start, 0);

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int cost = map.get(curr);
            // 인접한 정점들의 번호를 얻어낸다
            List<State> adjacent = curr.getAdjacent();
            for (int i = 0; i < adjacent.size(); i++) {
                if (!map.containsKey(adjacent.get(i))) {
                    // 답을 찾았나?
                    if (adjacent.get(i).equals(finish)) return cost + 1;
                    map.put(adjacent.get(i), cost + 1);
                    queue.add(adjacent.get(i));
                }
            }
        }

        // 답을 찾지 못한 경우
        return -1;
    }
}
