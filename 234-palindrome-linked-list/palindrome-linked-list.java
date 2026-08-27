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
    public boolean isPalindrome(ListNode head) {

        // ArrayList<ListNode> list = new ArrayList<>();

        // ListNode temp = head;
        // while(temp != null){
        //    list.add(temp);
        //    temp = temp.next;

        //     }


        //     int left = 0;
        //     int right = list.size()-1;
        //     while(left<right){
        //         if(list.get(right).val !=list.get(left).val){
        //               return false;
        //         }
             

        //          left++;
        //          right--;
        //     }


        //     return true;


        // Stack<ListNode> st = new Stack<>();

        // ListNode temp = head;

        // while(temp!=null){
        //     st.push(temp);
        //     temp = temp.next;
        // }



        // int left = 0;
        // int right = st.size()-1;

        // while(left<right){
        //     if(st.get(left).val != st.get(right).val){
        //         return false;
        //     }
        //     left++;
        //     right--;
        // }

        // return true;


              if(head == null && head.next == null){
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;


        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
           
        }


        if(fast!=null){
            slow = slow.next;

        }


        ListNode prev = null;
        ListNode curr = slow;


        while(curr != null){
            ListNode Next = curr.next;

            curr.next = prev;
            prev = curr;

            curr=Next;

        }


        ListNode first = head;
        ListNode second = prev;


        while(second != null){
            if(first.val != second.val){
                return false;
            }

            first = first.next;
            second = second.next;
        }


        return true;














        
    }
}