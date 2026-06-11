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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        func(dummy,0,n);
        return dummy.next;
    }
    private int func(ListNode head, int curr,int n){
        if(head==null) return 0;
        else curr = 1 + func(head.next,curr,n);

        if(curr == n+1) head.next = head.next.next;
        return curr;
    }
}