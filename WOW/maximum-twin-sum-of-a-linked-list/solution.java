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
    ListNode start;
    int max = Integer.MIN_VALUE;

    public int pairSum(ListNode head) {
        start = head;
        func(head);
        return max;
    }

    public void func(ListNode end) {
        if (end == null) return;
        func(end.next);
        max = Math.max(max, start.val + end.val);
        start = start.next;
    }
}