package programmers;

import java.util.*;
import java.lang.*;

// 합승 택시 요금
public class Problem_4 {

    public static void main(String[] args) {
        Problem_4 p = new Problem_4();
        int n = 6, s = 4, a = 6, b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        int result = p.solution(n, s, a, b, fares);
        System.out.println(result);
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] fare: fares) {
            List<int[]> innerList = map.getOrDefault(fare[0], new ArrayList<>());
            innerList.add(new int[]{fare[1], fare[2]});
            map.put(fare[0], innerList);

            innerList = map.getOrDefault(fare[1], new ArrayList<>());
            innerList.add(new int[]{fare[0], fare[2]});
            map.put(fare[1], innerList);
        }
        Set<Integer> allNodeSet = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            allNodeSet.add(i);
        }
        int[] sCost = dijkstra(s, allNodeSet, n, map);
        int[] aCost = dijkstra(a, allNodeSet, n, map);
        int[] bCost = dijkstra(b, allNodeSet, n, map);
        answer = sCost[a] + sCost[b]; // 따로 가기

        for (int i = 1; i < n + 1; i++) {
            if (i == s || map.get(i) == null) continue;
            // int[] nodeCost = dijkstra(i, new HashSet<>(Set.of(a,b)), n, map);
            answer = Math.min(answer, sCost[i] + aCost[i] + bCost[i]);
        }
        return answer;
    }

    // 거리(Cost) 순으로 처리하며 출발 지점에서 각 지점의 값들을 담는 배열로 만들어 반환
    public int[] dijkstra(int start, Set<Integer> set, int n, Map<Integer, List<int[]>> edge) {
        int[] cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int dest = node[0];
            int dist = node[1];

            if (dist >= cost[dest]) {
                continue;
            }
            else {
                cost[dest] = dist;
            }

            if (set.contains(dest)) {
                set.remove(dest);
                if (set.isEmpty()) {
                    break;
                }
            }


            for (int[] fare: edge.get(dest)) {

                queue.add(new int[]{fare[0], fare[1] + cost[dest]});
            }
        }
        return cost;
    }
}
