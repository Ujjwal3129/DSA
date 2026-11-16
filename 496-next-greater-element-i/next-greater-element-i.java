class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Stack<Integer> st = new Stack<>();
        // for (int i = 0; i < nums1.length; i++) {
        //     for (int j = 0; j < nums2.length; j++) {
        //         if (nums1[i] == nums2[j]) {
        //             boolean found = false;
        //             for (int k = j + 1; k < nums2.length; k++) {
        //                 if (nums2[k] > nums1[i]) {
        //                     st.push(nums2[k]);
        //                     found = true;
        //                     break;
        //                 }
        //             }

        //             if (found == false) {
        //                 st.push(-1);
        //             }
        //         }

        //         else {
        //             continue;

        //         }

        //     }
        // }

        // int ans[] = new int[st.size()];
        // for (int i = 0; i < st.size(); i++) {
        //     ans[i] = st.get(i);
        // }

        // return ans;

        Stack<Integer> st = new Stack<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!st.isEmpty() && st.peek() < nums2[i]) {

                hm.put(st.peek(), nums2[i]);
                st.pop();

            }
            st.push(nums2[i]);

        }

        while (!st.isEmpty()) {
            hm.put(st.pop(), -1);
        }

        int ans[] = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {

            ans[i] = hm.get(nums1[i]);

        }

        return ans;

    }
}