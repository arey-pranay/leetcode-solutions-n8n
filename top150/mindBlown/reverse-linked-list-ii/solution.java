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
        System.out.println(start.val);
        
        ListNode curr = start.next;
        ListNode near = null; 
        ListNode prev = null; 
        
        for(int i=left;i<=right;i++){
            near = curr.next; 
            curr.next = prev; 
            prev = curr;  
            curr = near; 
        }
        
        ListNode l = start.next;
        start.next = prev;
        l.next = curr;
        
        return temp.next;
    }
}