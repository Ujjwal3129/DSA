class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        long[] prefix = new long[n];

        prefix[0] = stones[0];

        // Prefix sum
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // Initially, all stones are taken
        long dp = prefix[n - 1];

        // Try every possible point
        for (int i = n - 2; i > 0; i--) {
            dp = Math.max(dp, prefix[i] - dp);
        }

        return (int) dp;
    }
}