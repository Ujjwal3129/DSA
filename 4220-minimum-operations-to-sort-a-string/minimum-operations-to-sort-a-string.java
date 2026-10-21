class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        
        if (isSorted) {
            return 0;
        }
        
        if (n == 2) {
            return -1;
        }
        
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 1; i < n; i++) {
            char current = s.charAt(i);
            if (current < minChar) {
                minChar = current;
            }
            if (current > maxChar) {
                maxChar = current;
            }
        }
        
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
        
        for (int i = 1; i < n - 1; i++) {
            char current = s.charAt(i);
            if (current == minChar || current == maxChar) {
                return 2;
            }
        }
        
        return 3;
    }
}
