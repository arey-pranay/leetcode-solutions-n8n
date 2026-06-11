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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int n = 0;
        ListNode temp = head;
        
        while(temp!=null && n++ < Integer.MAX_VALUE) temp = temp.next;
        k %= n;
        if(k==0) return head;
        temp = head;
        
        for(int i=1;i<n-k;i++) temp = temp.next;
        
        ListNode start = temp.next;
        temp.next = null;
        temp = start;
        while(temp.next != null) temp = temp.next;

        temp.next = head;
        return start;
    }
}