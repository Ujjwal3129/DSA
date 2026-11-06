
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        // Store queries with original indices
        int m = queries.length;
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int[] ans = new int[m];

        // Frequency of requests for each server
        int[] freq = new int[n + 1];

        int left = 0;
        int right = 0;
        int active = 0;

        for (int[] query : q) {

            int time = query[0];
            int start = time - x;

            // Add logs inside the current time window
            while (right < logs.length && logs[right][1] <= time) {

                int server = logs[right][0];

                if (freq[server] == 0) {
                    active++;
                }

                freq[server]++;
                right++;
            }

            // Remove logs before the window
            while (left < right && logs[left][1] < start) {

                int server = logs[left][0];

                freq[server]--;

                if (freq[server] == 0) {
                    active--;
                }

                left++;
            }

            // Servers with no requests
            ans[query[1]] = n - active;
        }

        return ans;
    }
}
