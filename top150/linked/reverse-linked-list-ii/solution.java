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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left >= right) return head;
        ListNode temp = new ListNode(0);
        temp.next = head;
        
        ListNode start = temp;
        for(int i=1;i<left;i++)start = start.next;
        
        ListNode tail = start.next;
        ListNode near = tail.next;
        
        for(int i=0;i<right-left;i++){
            tail.next = near.next;
            near.next = start.next;
            start.next = near;
            near = tail.next; 
        }
        
        return temp.next;
    }
}