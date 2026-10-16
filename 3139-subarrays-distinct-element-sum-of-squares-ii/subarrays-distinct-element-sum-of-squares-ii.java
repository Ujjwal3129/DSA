
class Solution {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        int mod = 1_000_000_007;

        long[] sum = new long[4 * n];
        long[] squareSum = new long[4 * n];
        long[] lazy = new long[4 * n];
        int[] lastSeen = new int[100001];

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastSeen[nums[i]];
            lastSeen[nums[i]] = i + 1;

            update(1, 0, n - 1, prev, i,
                   sum, squareSum, lazy, mod);

            ans = (ans + squareSum[1]) % mod;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                        int left, int right,
                        long[] sum, long[] squareSum,
                        long[] lazy, int mod) {

        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            long oldSum = sum[node];
            int len = end - start + 1;

            squareSum[node] = (
                squareSum[node] + 2 * oldSum
                + len
            ) % mod;

            sum[node] = (oldSum + len) % mod;
            lazy[node]++;
            return;
        }

        push(node, start, end, sum, squareSum, lazy, mod);

        int mid = (start + end) / 2;

        update(node * 2, start, mid, left, right,
               sum, squareSum, lazy, mod);

        update(node * 2 + 1, mid + 1, end, left, right,
               sum, squareSum, lazy, mod);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % mod;

        squareSum[node] = (
            squareSum[node * 2] + squareSum[node * 2 + 1]
        ) % mod;
    }

    private void push(int node, int start, int end,
                      long[] sum, long[] squareSum,
                      long[] lazy, int mod) {

        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = (start + end) / 2;
        long val = lazy[node];

        apply(node * 2, mid - start + 1, val,
              sum, squareSum, lazy, mod);

        apply(node * 2 + 1, end - mid, val,
              sum, squareSum, lazy, mod);

        lazy[node] = 0;
    }

    private void apply(int node, int len, long val,
                       long[] sum, long[] squareSum,
                       long[] lazy, int mod) {

        long oldSum = sum[node];

        squareSum[node] = (
            squareSum[node] + 2 * val % mod * oldSum % mod
            + len * val % mod * val % mod
        ) % mod;

        sum[node] = (oldSum + len * val) % mod;
        lazy[node] = (lazy[node] + val) % mod;
    }
}
