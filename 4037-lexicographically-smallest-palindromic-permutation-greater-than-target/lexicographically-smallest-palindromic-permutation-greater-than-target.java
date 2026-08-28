import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        // Validate if a palindrome can be formed
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        if (oddCount > 1) return "";

        // Collect characters for the first half
        int halfLen = n / 2;
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = counts[i] / 2;
        }

        // Try to match target prefix of length 'len' from longest to shortest
        for (int len = halfLen; len >= 0; len--) {
            int[] currentCounts = halfCounts.clone();
            char[] halfStr = new char[halfLen];
            boolean validPrefix = true;

            // Try to match the exact prefix of target up to 'len'
            for (int i = 0; i < len; i++) {
                char targetChar = target.charAt(i);
                if (currentCounts[targetChar - 'a'] > 0) {
                    halfStr[i] = targetChar;
                    currentCounts[targetChar - 'a']--;
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            // If we successfully matched up to 'len'
            if (len < halfLen) {
                // We need the next character to be strictly greater than target.charAt(len)
                char targetChar = target.charAt(len);
                boolean foundNext = false;
                for (int c = targetChar - 'a' + 1; c < 26; c++) {
                    if (currentCounts[c] > 0) {
                        halfStr[len] = (char) ('a' + c);
                        currentCounts[c]--;
                        foundNext = true;
                        break;
                    }
                }
                if (!foundNext) continue; // No valid larger character found for this prefix length

                // Fill the rest of the half-string greedily with the smallest available characters
                int idx = len + 1;
                for (int c = 0; c < 26; c++) {
                    while (currentCounts[c] > 0) {
                        halfStr[idx++] = (char) ('a' + c);
                        currentCounts[c]--;
                    }
                }
            }

            // Construct full candidate palindrome string
            StringBuilder sb = new StringBuilder(new String(halfStr));
            String firstHalf = sb.toString();
            String secondHalf = sb.reverse().toString();
            String candidate = firstHalf + (n % 2 != 0 ? midChar : "") + secondHalf;

            // If a valid strictly greater palindrome is found, it is guaranteed to be optimal
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        return "";
    }
}
