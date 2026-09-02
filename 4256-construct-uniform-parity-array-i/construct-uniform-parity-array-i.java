class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean odd = false;
        boolean even = false;

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] % 2 == 0)
                even = true;
            else
                odd = true;
        }

        if (odd && even)
            return true;

        return true;
    }
}