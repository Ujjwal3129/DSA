class Solution {
    public int removeDuplicates(int[] nums) {
        ArrayList <Integer> arr = new ArrayList<>();

        int j = 1;

        for(int i=1; i<nums.length; i++){
            if(nums[i] != nums[j-1]){
                nums[j] = nums[i];
                j++;
            }
        }

        return j;

        
    }
}