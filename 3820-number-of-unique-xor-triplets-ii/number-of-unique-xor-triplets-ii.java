class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAXX = 2048; // nums[i] <= 1500, so XOR range is < 2048

        boolean[][] dp = new boolean[4][MAXX];
        dp[0][0] = true;

        for (int v : nums) {
            boolean[][] next = new boolean[4][MAXX];

            // Skip current index
            for (int c = 0; c <= 3; c++) {
                for (int x = 0; x < MAXX; x++) {
                    if (dp[c][x]) {
                        next[c][x] = true;
                    }
                }
            }

            // Take current index 1, 2, or 3 times
            for (int c = 0; c <= 3; c++) {
                for (int x = 0; x < MAXX; x++) {
                    if (!dp[c][x]) continue;

                    // Take once
                    if (c + 1 <= 3)
                        next[c + 1][x ^ v] = true;

                    // Take twice (v ^ v = 0)
                    if (c + 2 <= 3)
                        next[c + 2][x] = true;

                    // Take three times (v ^ v ^ v = v)
                    if (c + 3 <= 3)
                        next[c + 3][x ^ v] = true;
                }
            }

            dp = next;
        }

        int ans = 0;
        for (int x = 0; x < MAXX; x++) {
            if (dp[3][x]) ans++;
        }
        return ans;
    }
}