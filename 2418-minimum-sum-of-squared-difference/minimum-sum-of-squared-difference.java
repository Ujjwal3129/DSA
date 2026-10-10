
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int max = 0;
        long operations = (long) k1 + k2;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        if (operations >= Arrays.stream(diff).asLongStream().sum()) {
            return 0;
        }

        int[] freq = new int[max + 1];

        for (int d : diff) {
            freq[d]++;
        }

        for (int d = max; d > 0 && operations > 0; d--) {
            long move = Math.min(operations, (long) freq[d]);
            freq[d] -= move;
            freq[d - 1] += move;
            operations -= move;
        }

        long result = 0;

        for (int d = 1; d < freq.length; d++) {
            result += (long) d * d * freq[d];
        }

        return result;
    }
}
