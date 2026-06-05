//slow-fast two pointer. one-pass approach.
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(0, head);
        ListNode dummy = res;

        for (int i = 0; i < n; i++) {
            head = head.next;
        }

        while (head != null) {
            head = head.next;
            dummy = dummy.next;
        }

        dummy.next = dummy.next.next;

        return res.next;        
    }
}

// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }
//  */
// class Solution {
//     public ListNode removeNthFromEnd(ListNode head, int n) {
//         if(head==null) return head;

//         int total = 0;
//         ListNode temp = head;
//         while(temp!=null){
//             temp = temp.next;
//             total++;
//         }

//         temp = head;
//         int toRemove = total-n-1;
//         if(toRemove<0) return head.next; //first node

//         for(int i=0;i<toRemove;i++) temp = temp.next;
//         if(temp.next == null) temp = null; //last node
//         else temp.next = temp.next.next;

//         return head;
//     }
// }