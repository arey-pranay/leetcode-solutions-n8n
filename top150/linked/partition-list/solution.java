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
    public ListNode partition(ListNode head, int x) {
        ListNode smalls = new ListNode(-101);
        ListNode moveSmalls = smalls;
        ListNode bigs = new ListNode(101);
        ListNode moveBigs = bigs;
        while(head!=null){
            if(head.val<x){
                moveSmalls.next = head;
                head = head.next;
                moveSmalls = moveSmalls.next;
                moveSmalls.next = null;
            }
            else{
                moveBigs.next = head;
                head = head.next;
                moveBigs = moveBigs.next;
                moveBigs.next = null;
            } 
            
        }
        moveSmalls.next = bigs.next;
        return smalls.next;
    }
}