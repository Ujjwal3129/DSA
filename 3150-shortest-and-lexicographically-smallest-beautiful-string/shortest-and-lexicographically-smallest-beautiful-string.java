class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";

        int left = 0;
        int ones = 0;

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // Jab exactly k ones mil gaye
            while (ones == k) {

                String current = s.substring(left, right + 1);

                // Pehli valid string ya
                // shorter string mil gayi
                // ya same length mein lexicographically smaller
                if (ans.equals("") ||
                    current.length() < ans.length() ||
                    (current.length() == ans.length() &&
                     current.compareTo(ans) < 0)) {
                    ans = current;
                }

                // left ko aage move karo
                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }
        }

        return ans;
    }
}