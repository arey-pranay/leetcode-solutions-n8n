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
    public ListNode reverseKGroup(ListNode head, int k) {
        int length = 0;
        ListNode first = head;
        while(first!=null){
            length++;
            first = first.next;
        }
        length -= length%k;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        for(int i=0;i<length;i+=k){
            ListNode[] arr = revRange(curr.next,k); // yaha curr.next se call , mtlb he
            curr.next = arr[0];
            curr = arr[1];
        }
        return dummy.next;
    }
    public ListNode[] revRange(ListNode head,int k){
        ListNode prev = null;
        ListNode curr = head;
        while(k-->0){
            ListNode near = curr.next;
            curr.next = prev;
            prev = curr;
            curr = near;
        }
        head.next = curr;
        return new ListNode[]{prev,head};
    }
}