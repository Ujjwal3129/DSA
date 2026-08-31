class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prev = -1;
        int min = Integer.MAX_VALUE;
        int index = 1;

        ListNode left = head;
        ListNode cur = head.next;

        while (cur.next != null) {
            if ((cur.val > left.val && cur.val > cur.next.val) ||
                (cur.val < left.val && cur.val < cur.next.val)) {

                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - prev);
                }

                prev = index;
            }

            left = cur;
            cur = cur.next;
            index++;
        }

        if (first == -1 || first == prev) {
            return new int[]{-1, -1};
        }

        return new int[]{min, prev - first};
    }
}