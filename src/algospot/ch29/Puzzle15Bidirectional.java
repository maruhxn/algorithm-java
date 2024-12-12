package algospot.ch29;

import java.util.*;

public class Puzzle15Bidirectional {

    interface State {
        // 인접한 상태의 목록을 반환한다
        List<State> getAdjacent();
    }

    // x의 부호 반환
    static int sgn(int x) {
        if (x == 0) return 0;
        else return x > 0 ? 1 : -1;
    }

    // x의 절대값을 1 증가
    static int incr(int x) {
        if (x < 0) return x - 1;
        else return x + 1;
    }

    // start에서 finish까지 가는 최단 경로의 길이 반환
    public static int bidirectional(State start, State finish) {
        // 예외: start == finish인 경우
        if (start.equals(finish)) return 0;

        // 각 정점까지의 최단 경로의 길이를 저장
        Map<State, Integer> map = new HashMap<>();
        Queue<State> queue = new LinkedList<>();

        // 시작점과 도착점을 모두 큐에 넣되, 부호를 반대로 저장한다.
        queue.add(start);
        queue.add(finish);

        map.put(start, 1);
        map.put(finish, -1);

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            // 인접한 정점들의 번호를 얻어낸다
            List<State> adjacent = curr.getAdjacent();
            for (int i = 0; i < adjacent.size(); i++) {
                State nextState = adjacent.get(i);

                // 아직 방문하지 않은 정점을 방문하고 최단 경로 길이 갱신
                if (!map.containsKey(nextState)) {
                    map.put(nextState, incr(map.get(curr)));
                    queue.add(nextState);
                }
                // 이미 방문했으며, 가운데서 만난 경우(부호만 서로 다른 경우)
                else if (sgn(map.get(nextState)) != sgn(map.get(curr))) {
                    return Math.abs(map.get(nextState)) + Math.abs(map.get(curr)) - 1;
                }
            }
        }

        // 답을 찾지 못한 경우
        return -1;
    }
}
