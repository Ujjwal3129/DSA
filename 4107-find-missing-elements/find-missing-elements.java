class Solution {
    public List<Integer> findMissingElements(int[] nums) {

        Arrays.sort(nums);

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {

            int curr = nums[i];
            int next = nums[i + 1];

            for (int j = curr + 1; j < next; j++) {
                ans.add(j);
            }
        }

        return ans;
    }
}