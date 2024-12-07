package algospot.ch29;

import java.util.List;

public class Puzzle15IDS {

    interface State {
        // 인접한 상태의 목록을 반환한다
        List<State> getAdjacent();
    }

    static int best;

    public static void dfs(State curr, State finish, int steps) {
        // 지금까지 구한 최적해보다 더 좋을 가능성이 없으면 버린다
        if (steps >= best) return;
        // 목표 상태에 도달한 경우
        if (curr == finish) {
            best = steps;
            return;
        }
        // 인접 상태들을 깊이 우선 탐색
        List<State> adjacent = curr.getAdjacent();
        for (int i = 0; i < adjacent.size(); i++) {
            dfs(adjacent.get(i), finish, steps + 1);
        }
    }

    // 점점 깊어지는 탐색
    public static int ids(State start, State finish, int growthStep) {
        for (int limit = 4; ; limit += growthStep) {
            best = limit + 1;
            dfs(start, finish, 0);
            if (best <= limit) return best;
        }
    }
}
