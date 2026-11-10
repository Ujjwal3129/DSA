class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;

            for (int j = 1; j <= 3; j++) {
                if (i - j >= 0) {
                    int prev = dp[i - j];
                    int jump = j * j;

                    dp[i] = Math.min(dp[i], prev + costs[i - 1] + jump);
                }
            }
        }

        return dp[n];
    }
}