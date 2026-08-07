#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
    int getMinDigitsNeeded(int c2, int c3, int c5, int c7) {
        int count = c5 + c7;
        
        count += c3 / 2;
        c3 %= 2;
        
        count += c2 / 3;
        c2 %= 3;

        if (c3 == 1 && c2 == 1) {
            count += 1;
        } else if (c3 == 1 && c2 == 2) {
            count += 2;
        } else if (c3 == 1 && c2 == 0) {
            count += 1;
        } else if (c3 == 0 && c2 > 0) {
            count += 1;
        }
        
        return count;
    }

    string fillSmallest(int len, int c2, int c3, int c5, int c7) {
        string res = "";
        
        while (c3 >= 2 && len > (int)res.length()) { res += '9'; c3 -= 2; }
        while (c2 >= 3 && len > (int)res.length()) { res += '8'; c2 -= 3; }
        while (c7 > 0 && len > (int)res.length()) { res += '7'; c7--; }
        while (c2 >= 1 && c3 >= 1 && len > (int)res.length()) { res += '6'; c2--; c3--; }
        while (c5 > 0 && len > (int)res.length()) { res += '5'; c5--; }
        while (c2 >= 2 && len > (int)res.length()) { res += '4'; c2 -= 2; }
        while (c3 > 0 && len > (int)res.length()) { res += '3'; c3--; }
        while (c2 > 0 && len > (int)res.length()) { res += '2'; c2--; }
        while ((int)res.length() < len) { res += '1'; }

        reverse(res.begin(), res.end());
        return res;
    }

public:
    string smallestNumber(string num, long long t) {
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        long long temp_t = t;
        
        while (temp_t % 2 == 0) { c2++; temp_t /= 2; }
        while (temp_t % 3 == 0) { c3++; temp_t /= 3; }
        while (temp_t % 5 == 0) { c5++; temp_t /= 5; }
        while (temp_t % 7 == 0) { c7++; temp_t /= 7; }

        if (temp_t > 1) return "-1";

        int n = num.length();

        vector<int> req2(n + 1, 0), req3(n + 1, 0), req5(n + 1, 0), req7(n + 1, 0);
        req2[0] = c2; req3[0] = c3; req5[0] = c5; req7[0] = c7;

        int zero_idx = -1;
        for (int i = 0; i < n; ++i) {
            if (num[i] == '0') {
                zero_idx = i;
                break;
            }
            int d = num[i] - '0';
            req2[i + 1] = max(0, req2[i] - (d == 2 || d == 6 ? 1 : (d == 4 ? 2 : (d == 8 ? 3 : 0))));
            req3[i + 1] = max(0, req3[i] - (d == 3 || d == 6 ? 1 : (d == 9 ? 2 : 0)));
            req5[i + 1] = max(0, req5[i] - (d == 5 ? 1 : 0));
            req7[i + 1] = max(0, req7[i] - (d == 7 ? 1 : 0));
        }

        if (zero_idx == -1 && req2[n] == 0 && req3[n] == 0 && req5[n] == 0 && req7[n] == 0) {
            return num;
        }

        int limit = (zero_idx != -1) ? zero_idx : n - 1;
        for (int i = limit; i >= 0; --i) {
            int start_digit = num[i] - '0' + 1;
            for (int d = start_digit; d <= 9; ++d) {
                int nc2 = max(0, req2[i] - (d == 2 || d == 6 ? 1 : (d == 4 ? 2 : (d == 8 ? 3 : 0))));
                int nc3 = max(0, req3[i] - (d == 3 || d == 6 ? 1 : (d == 9 ? 2 : 0)));
                int nc5 = max(0, req5[i] - (d == 5 ? 1 : 0));
                int nc7 = max(0, req7[i] - (d == 7 ? 1 : 0));

                int rem_len = n - 1 - i;
                if (getMinDigitsNeeded(nc2, nc3, nc5, nc7) <= rem_len) {
                    string prefix = num.substr(0, i) + to_string(d);
                    string suffix = fillSmallest(rem_len, nc2, nc3, nc5, nc7);
                    return prefix + suffix;
                }
            }
        }

        int total_len = max(n + 1, getMinDigitsNeeded(c2, c3, c5, c7));
        return fillSmallest(total_len, c2, c3, c5, c7);
    }
};