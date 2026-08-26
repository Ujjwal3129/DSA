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
    public ListNode reverseList(ListNode head) {


        Stack<ListNode> st = new Stack<>();

        ListNode temp = head;
         
        while(temp != null){
            st.push(temp);
            temp = temp.next;
        }



        if(st.isEmpty()){
            return null;
        }

        ListNode newHead = st.pop();

         temp = newHead;


        while(!st.isEmpty()){
            ListNode node = st.pop();
            temp.next = node;
            temp = node;
        } 

        temp.next = null;

        return newHead;

         
        
    }
}