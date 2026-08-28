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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ArrayList<ListNode> list = new ArrayList<>();

        ListNode temp = head;


        while(temp!=null){
            list.add(temp);
            temp = temp.next;
        }


        int count = 0;

        if (n == list.size()) {
    return head.next;
}

        ListNode prev= null;
        for(int i=list.size()-1; i>=0; i--){
            count++;
            if(n==count){


                  list.get(i-1).next = list.get(i).next;
                  continue;


            }

            // prev= i;

            // list.add(list.get(i))
                //   list.get(i) = list.get(i).next;


            


          
        }


        return head;




        

    }
}