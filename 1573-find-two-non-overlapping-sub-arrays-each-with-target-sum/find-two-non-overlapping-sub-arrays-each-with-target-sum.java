class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

  
        int[] best = new int[n];
        java.util.Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            // If current window has sum == target
            if (sum == target) {
                int len = right - left + 1;

                // We need another valid subarray before 'left'
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, len + best[left - 1]);
                }

                if (right == 0) {
                    best[right] = len;
                } else {
                    best[right] = Math.min(best[right - 1], len);
                }
            } else if (right > 0) {
                best[right] = best[right - 1];
            }
        }

        return answer == INF ? -1 : answer;
    }
}