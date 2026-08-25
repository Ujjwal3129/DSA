class Solution {
    public int missingMultiple(int[] nums, int k) {

        HashSet <Integer> hs = new HashSet<>();


        for(int i : nums){
            hs.add(i);
        }


        int mul = k;


        while(hs.contains(mul)){
            mul+=k;
        }


        return mul;
        
        
    }
}