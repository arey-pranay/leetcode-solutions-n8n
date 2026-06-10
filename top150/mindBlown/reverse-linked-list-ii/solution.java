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
        ListNode near = null; // 3
        ListNode r = null; //2
        
        for(int i=left;i<=right;i++){
            near = curr.next; // 2 -> 4
            curr.next = r; // 3 -> 2
            r = curr;  //  1-> 3
            curr = near; // near = 4
        }
        
        ListNode l = start.next;
        start.next = r;
        l.next = curr;
        
        return temp.next;
    }
}