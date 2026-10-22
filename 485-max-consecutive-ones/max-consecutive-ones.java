class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {

        // int count = 0;
        // int maxCount = 0;

        // for(int i=0; i<nums.length; i++){
        //     if(nums[i]==1){
        //         count++;
        //     }
        //     else {
        //         maxCount = Math.max(maxCount, count);
        //         count=0;
        //     }

        // }

        int i=0;
      
        int maxCount=0;
       


        while(i<nums.length){
               int count=0;
            while(i<nums.length && nums[i]==1){
                count++;
                   i++;
              
            }

              maxCount = Math.max(maxCount,count);

              if(i<nums.length){
                i++;
              }
         

        }

        return maxCount;

    }
}