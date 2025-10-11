package programmers;

import java.util.*;
import java.lang.*;

// 순위 검색
public class Problem_3 {
    public static void main(String[] args) {
        Problem_3 p = new Problem_3();
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        System.out.println(Arrays.toString(p.solution(info, query)));
        StringBuilder sb =new StringBuilder();
    }

    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (String i: info) {
            String[] elements = i.split(" ");
            makeCombination(map, elements, 4, 0, "");
        }

        for (List<Integer> list : map.values()) {
            Collections.sort(list); // Sort를 아래 for문에 포함시키면 시간 복잡도 증가
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] data = query[i].replaceAll(" and ", "").split(" ");
            List<Integer> list = map.getOrDefault(data[0], new ArrayList<>());
            int start = 0;
            int end = list.size() - 1;
            int minIndex = Integer.MAX_VALUE;
            int target = Integer.parseInt(data[1]);
            while (start <= end) {
                int mid = (start + end) / 2;
                boolean result = true;
                if (list.get(mid) >= target) {
                    result = false;
                }

                if (result) {
                    start = mid + 1;
                }
                else {
                    minIndex = Math.min(minIndex, mid);
                    end = mid - 1;
                }
            }

            answer[i] = minIndex == Integer.MAX_VALUE ? 0: list.size() - minIndex;
        }

        return answer;
    }

    public void makeCombination(Map<String, List<Integer>> map, String[] elements, int n, int idx, String value) {
        if (n == idx) {
            List<Integer> list = map.getOrDefault(value, new ArrayList());
            list.add(Integer.parseInt(elements[4]));
            map.put(value, list);
            return;
        }

        String newStr = value + elements[idx];
        makeCombination(map, elements, n, idx + 1, newStr);
        newStr = value + "-";
        makeCombination(map, elements, n, idx + 1, newStr);
    }
}
