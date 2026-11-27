import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();

        // Step 1: Multi-source BFS from all thieves
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    q.offer(new int[]{r, c});
                    dist[r][c] = 0;
                }
            }
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[r][c] + 1;
                q.offer(new int[]{nr, nc});
            }
        }

        // Step 2: Max-heap for safest path
        // pq = {safeness, r, c}, max heap by safeness
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{dist[0][0], 0, 0});
        boolean[][] vis = new boolean[n][n];
        vis[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int safe = cur[0], r = cur[1], c = cur[2];

            if (r == n - 1 && c == n - 1) return safe;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (vis[nr][nc]) continue;
                vis[nr][nc] = true;
                int newSafe = Math.min(safe, dist[nr][nc]);
                pq.offer(new int[]{newSafe, nr, nc});
            }
        }
        return 0;
    }
}