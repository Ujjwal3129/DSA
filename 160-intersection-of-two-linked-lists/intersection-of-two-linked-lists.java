/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ArrayList<ListNode> list = new ArrayList<>();

        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA != null) {
            list.add(tempA);
            tempA = tempA.next;
        }

        while (tempB != null) {
            int Index = 0;

            while (Index < list.size()) {
                if (tempB == list.get(Index)) {
                    return tempB;
                }

                Index++;
            }

            tempB = tempB.next;
        }

        return null;

    }
}