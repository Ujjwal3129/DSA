import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair up (value, original_index) and sort by value
        int[][] sortedNums = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums[i][0] = nums[i];
            sortedNums[i][1] = i;
        }
        
        // Sort primarily by value. If values are equal, index order doesn't matter much.
        Arrays.sort(sortedNums, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Map original index to its position in the sorted array
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sortedNums[i][1]] = i;
        }

        // Step 3: Initialize Binary Lifting table
        int log = (int) (Math.log(n) / Math.log(2)) + 2;
        int[][] up = new int[log][n];

        // Compute up[0][i]: the furthest index we can jump to from sorted position i
        for (int i = 0; i < n; i++) {
            int targetVal = sortedNums[i][0] + maxDiff;
            
            // Binary search to find the furthest element <= targetVal
            int furthestIdx = binarySearchFurthest(sortedNums, targetVal);

            // If we can't even reach past ourselves, we are stuck in this component
            if (furthestIdx <= i) {
                up[0][i] = i;
            } else {
                up[0][i] = furthestIdx;
            }
        }

        // Step 4: Fill the binary lifting table
        for (int k = 1; k < log; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        // Step 5: Answer the queries
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = pos[queries[i][0]];
            int v = pos[queries[i][1]];

            if (u == v) {
                answer[i] = 0;
                continue;
            }

            // Ensure u is always the smaller index in the sorted list
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }

            // If even the maximum possible jump from u cannot reach v, path is impossible
            if (up[log - 1][u] < v) {
                answer[i] = -1;
                continue;
            }

            int jumps = 0;
            int current = u;

            // Lift greedily as long as the jump does not overshoot v
            for (int k = log - 1; k >= 0; k--) {
                if (up[k][current] < v) {
                    current = up[k][current];
                    jumps += (1 << k);
                }
            }

            // One final jump is required to step into or over v
            answer[i] = jumps + 1;
        }

        return answer;
    }

    // Custom binary search to find the largest index whose value is <= target
    private int binarySearchFurthest(int[][] sortedNums, int target) {
        int low = 0;
        int high = sortedNums.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedNums[mid][0] <= target) {
                ans = mid; // Found a valid index, try to find a larger one to the right
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
