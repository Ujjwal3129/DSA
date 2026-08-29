class Solution {
    public ListNode deleteMiddle(ListNode head) {

        // if (head.next == null) {
        //     return null;
        // }

        // ListNode slow = head;
        // ListNode fast = head.next.next;

        // while (fast != null && fast.next != null) {
        //     slow = slow.next;
        //     fast = fast.next.next;
        // }

        // slow.next = slow.next.next;

        // return head;


        // ArrayList<ListNode>


        if(head.next== null){
            return null;
        }
        ListNode temp = head;

        int count=0;
        while(temp!=null){
            count++;
            temp= temp.next;
        }


        temp = head;


        for(int i=0; i<count/2-1; i++){
            temp = temp.next;
        }


        temp.next= temp.next.next;
        return head;

    }
}