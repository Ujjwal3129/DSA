class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                if (!isRotation(nums, sorted, start, k)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }

    private boolean isRotation(int[] nums, int[] sorted, int start, int k) {
        int[] lps = new int[k];
        int j = 0;

        for (int i = 1; i < k; i++) {
            while (j > 0 && sorted[start + i] != sorted[start + j]) {
                j = lps[j - 1];
            }

            if (sorted[start + i] == sorted[start + j]) {
                j++;
            }

            lps[i] = j;
        }

        j = 0;

        for (int i = 0; i < 2 * k - 1; i++) {
            int value = nums[start + i % k];

            while (j > 0 && value != sorted[start + j]) {
                j = lps[j - 1];
            }

            if (value == sorted[start + j]) {
                j++;
            }

            if (j == k) {
                return true;
            }
        }

        return false;
    }
}