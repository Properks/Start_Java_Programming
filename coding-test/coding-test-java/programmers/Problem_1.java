package programmers;

import java.util.Arrays;

// 카카오프렌즈 컬러링북
public class Problem_1 {
    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = { {1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3} };
        Problem_1 problem2 = new Problem_1();
        System.out.println(Arrays.toString(problem2.solution(m, n, picture)));

    }
    public int[] solution(int m, int n, int[][] picture) {

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

        int max = 0;
        int noa = 0;

        for (int i =0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (visited[i][j] || picture[i][j] == 0) {
                    continue;
                }
                else {
                    int cost = dfs(picture, m, n, visited, i, j, picture[i][j]);
                    max = Math.max(max, cost);
                    noa++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = noa;
        answer[1] = max;
        return answer;
    }

    public int dfs(int[][] picture, int m, int n, boolean[][] visited, int i, int j, int color) {
        int[][] DIRECT = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        if (visited[i][j] || color != picture[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        int cost = 1;
        for (int[] d: DIRECT) {
            int newI = i + d[0];
            int newJ = j + d[1];
            if (isValid(m,n,newI, newJ)) {
                cost += dfs(picture, m, n, visited, newI, newJ, color);
            }
        }

        return cost;
    }

    public boolean isValid(int m, int n, int i, int j) {
        return i >= 0 && i < m && j >=0 && j < n;
    }
}

