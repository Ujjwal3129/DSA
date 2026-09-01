import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;
        int litterCount = 0;

        // Find starting point and count litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i].charAt(j) == 'S') {
                    startR = i;
                    startC = j;
                }

                if (classroom[i].charAt(j) == 'L') {
                    litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;
        int allCollected = totalMasks - 1;

        /*
         * visited[row][col][mask][energy]
         */
        boolean[][][][] visited =
                new boolean[m][n][totalMasks][energy + 1];

        Queue<int[]> q = new LinkedList<>();

        // {row, col, mask, remainingEnergy, moves}
        q.offer(new int[]{startR, startC, 0, energy, 0});

        visited[startR][startC][0][energy] = true;

        // Directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Give every litter cell a bit number
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int id = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i].charAt(j) == 'L') {
                    litterId[i][j] = id++;
                }
            }
        }

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int currEnergy = cur[3];
            int moves = cur[4];

            // All litter collected
            if (mask == allCollected) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // Need 1 energy to make a move
                if (currEnergy == 0) {
                    continue;
                }

                int newEnergy = currEnergy - 1;
                int newMask = mask;

                char cell = classroom[nr].charAt(nc);

                // Collect litter
                if (cell == 'L') {
                    int litter = litterId[nr][nc];
                    newMask = mask | (1 << litter);
                }

                // Reset energy
                if (cell == 'R') {
                    newEnergy = energy;
                }

                // Avoid repeated states
                if (!visited[nr][nc][newMask][newEnergy]) {

                    visited[nr][nc][newMask][newEnergy] = true;

                    q.offer(new int[]{
                            nr,
                            nc,
                            newMask,
                            newEnergy,
                            moves + 1
                    });
                }
            }
        }

        return -1;
    }
}