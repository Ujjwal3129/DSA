
import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void add(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long sum(int i) {
            long res = 0;

            while (i > 0) {
                res += tree[i];
                i -= i & -i;
            }

            return res;
        }

        // Find the smallest index whose prefix sum >= k
        int kth(long k) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0;
                 bit >>= 1) {

                int next = idx + bit;

                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= tree[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(
                sorted, 0, m, nums[i]
            ) + 1;
        }

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        // Initialize first window
        for (int i = 0; i < x; i++) {
            count.add(rank[i], 1);
            sum.add(rank[i], nums[i]);
        }

        int windows = n - x + 1;

        long[] cost = new long[windows];

        // Calculate cost for each window
        for (int start = 0; start < windows; start++) {

            // Lower median
            int medianRank = count.kth((x + 1L) / 2);

            long median = sorted[medianRank - 1];

            long leftCount = count.sum(medianRank);
            long leftSum = sum.sum(medianRank);

            long totalCount = x;
            long totalSum = sum.sum(m);

            long rightCount = totalCount - leftCount;
            long rightSum = totalSum - leftSum;

            cost[start] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide window
            if (start + x < n) {

                int outgoing = start;
                int incoming = start + x;

                count.add(rank[outgoing], -1);
                sum.add(rank[outgoing], -nums[outgoing]);

                count.add(rank[incoming], 1);
                sum.add(rank[incoming], nums[incoming]);
            }
        }

        // DP:
        // dp[i][j] = minimum cost using first i elements
        //             to select j non-overlapping windows

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Skip current element
                dp[i][j] = dp[i - 1][j];

                // Take a window ending at i - 1
                if (i >= x &&
                    dp[i - x][j - 1] != INF) {

                    long take =
                        dp[i - x][j - 1]
                        + cost[i - x];

                    dp[i][j] = Math.min(
                        dp[i][j], take
                    );
                }
            }
        }

        return dp[n][k];
    }
}
