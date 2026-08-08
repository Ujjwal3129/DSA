import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[i] = maximum matched suffix length starting from word1[i]
        int[] suf = new int[n + 1];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = m - 1 - j;
        }

        List<Integer> ans = new ArrayList<>();
        boolean usedChange = false;
        int i = 0;
        int k = 0;

        while (k < m) {
            while (i < n) {
                // Exact match
                if (word1.charAt(i) == word2.charAt(k)) {
                    ans.add(i);
                    i++;
                    k++;
                    break;
                }

                // Use the one allowed modification
                if (!usedChange) {
                    int need = m - (k + 1);
                    int remain = (i + 1 <= n) ? suf[i + 1] : 0;

                    if (remain >= need) {
                        usedChange = true;
                        ans.add(i);
                        i++;
                        k++;
                        break;
                    }
                }

                i++;
            }

            if (i > n || ans.size() != k) {
                // continue normally
            }

            if (ans.size() < k) {
                return new int[0];
            }

            if (i == n && k < m) {
                return new int[0];
            }
        }

        if (ans.size() != m) return new int[0];

        int[] res = new int[m];
        for (int t = 0; t < m; t++) {
            res[t] = ans.get(t);
        }
        return res;
    }
}