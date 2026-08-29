/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {

        // if (head == null || head.next == null) {
        //     return head;
        // }
        // ArrayList<ListNode> list = new ArrayList<>();

        // ListNode temp = head;

        // while (temp != null) {
        //     list.add(temp);
        //     temp = temp.next;
        // }

        // int count = 0;

        // for (int i = 0; i < list.size(); i++) {

        //     for (int j = i + 1; j < list.size(); j++) {

        //         if (list.get(i).val > list.get(j).val) {
        //             int t = list.get(i).val;
        //             list.get(i).val = list.get(j).val;
        //             list.get(j).val = t;

        //         }

        //     }

        // }

        // return head;


        
        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split into two lists
        ListNode second = slow.next;
        slow.next = null;

        // Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(second);

        // Merge sorted halves
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (left != null && right != null) {

            if (left.val <= right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }

            temp = temp.next;
        }

        // Attach remaining nodes
        if (left != null) {
            temp.next = left;
        } else {
            temp.next = right;
        }

        return dummy.next;



    }
}