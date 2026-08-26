/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> hs = new HashSet<>();


        // ListNode temp = head;


        // while(temp!=null){

        //      if(hs.contains(temp)){
        //         return true;
        //     }
        //     hs.add(temp);


            


        //     temp = temp.next;
        
        // } 


      


        // return false;



        ListNode fast = head;
        ListNode slow = head;


        while(fast != null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;


            if(fast == slow){
                return true;
            }
        }


        return false;


        
    }
}