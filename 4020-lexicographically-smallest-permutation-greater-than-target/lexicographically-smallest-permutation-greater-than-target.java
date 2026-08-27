class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] count = new int[26];

        // Count characters of s
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        String answer = "";

        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int t = target.charAt(i) - 'a';

            // Try to make the answer greater at this position
            for (int c = t + 1; c < 26; c++) {

                if (count[c] > 0) {

                    StringBuilder candidate = new StringBuilder(prefix);

                    // Put the smallest character greater than target[i]
                    candidate.append((char) ('a' + c));

                    count[c]--;

                    // Put remaining characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        while (count[j] > 0) {
                            candidate.append((char) ('a' + j));
                            count[j]--;
                        }
                    }

                    // Keep the smallest candidate
                    if (answer.equals("") ||
                        candidate.toString().compareTo(answer) < 0) {
                        answer = candidate.toString();
                    }

                    // Restore count because we still need it
                    count[c]++;

                    // Restore all remaining characters
                    for (int j = 0; j < 26; j++) {
                        count[j] = 0;
                    }

                    // Rebuild count from s
                    for (char ch : s.toCharArray()) {
                        count[ch - 'a']++;
                    }

                    // Remove characters already used in prefix
                    for (int j = 0; j < prefix.length(); j++) {
                        count[prefix.charAt(j) - 'a']--;
                    }
                }
            }

            // Continue matching target if possible
            if (count[t] == 0) {
                break;
            }

            prefix.append(target.charAt(i));
            count[t]--;
        }

        return answer;
    }
}