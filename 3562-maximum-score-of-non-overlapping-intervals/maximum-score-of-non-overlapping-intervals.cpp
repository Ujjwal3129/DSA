
class Solution {
public:
    vector<int> maximumWeight(vector<vector<int>>& intervals) {
        int n = intervals.size();

        vector<array<long long, 4>> a(n);

        for (int i = 0; i < n; i++) {
            a[i] = {intervals[i][0], intervals[i][1], intervals[i][2], i};
        }

        sort(a.begin(), a.end(), [](auto &x, auto &y) {
            if (x[0] != y[0])
                return x[0] < y[0];
            return x[1] < y[1];
        });

        vector<int> nxt(n);

        for (int i = 0; i < n; i++) {
            int l = i + 1;
            int r = n - 1;
            int ans = n;

            while (l <= r) {
                int mid = l + (r - l) / 2;

                if (a[mid][0] > a[i][1]) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            nxt[i] = ans;
        }

        vector<vector<long long>> dp(n + 1, vector<long long>(5, 0));
        vector<vector<vector<int>>> path(n + 1, vector<vector<int>>(5));

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                dp[i][k] = dp[i + 1][k];
                path[i][k] = path[i + 1][k];

                long long take = a[i][2] + dp[nxt[i]][k - 1];

                vector<int> cur = path[nxt[i]][k - 1];
                cur.push_back((int)a[i][3]);

                sort(cur.begin(), cur.end());

                if (take > dp[i][k] ||
                    (take == dp[i][k] && cur < path[i][k])) {
                    dp[i][k] = take;
                    path[i][k] = cur;
                }
            }
        }

        return path[0][4];
    }
};

