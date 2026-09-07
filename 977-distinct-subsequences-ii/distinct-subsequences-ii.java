class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] lastCount = new int[26];
        long totalSubsequences = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            long currentAddedCount = (totalSubsequences + 1) % MOD;
            long nextTotal = (totalSubsequences + currentAddedCount - lastCount[charIndex]) % MOD;
            
            if (nextTotal < 0) {
                nextTotal += MOD;
            }
            
            lastCount[charIndex] = (int) currentAddedCount;
            totalSubsequences = nextTotal;
        }
        
        return (int) totalSubsequences;
    }
}
