class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zeroIdx = -1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }
        
        int minOps = Integer.MAX_VALUE;
        
        if (isCyclicSorted(nums, zeroIdx, 1)) {
            minOps = Math.min(minOps, zeroIdx);
            minOps = Math.min(minOps, n - zeroIdx + 2);
        }
        
        if (isCyclicSorted(nums, zeroIdx, -1)) {
            minOps = Math.min(minOps, n - zeroIdx);
            minOps = Math.min(minOps, zeroIdx + 2);
        }
        
        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
    
    private boolean isCyclicSorted(int[] nums, int zeroIdx, int direction) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int prevIdx = (zeroIdx + (i - 1) * direction + n) % n;
            int currIdx = (zeroIdx + i * direction + n) % n;
            
            if (nums[prevIdx] > nums[currIdx]) {
                return false;
            }
        }
        return true;
    }
}
