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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode h = new ListNode(0);
        ListNode tail = new ListNode(0);
        h.next = tail;
        
        int minIndex = -1;
        while (minIndex != -2) {
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            minIndex = -2;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minIndex = i;
                }
            }
            if(minIndex == -2)break;
            
            lists[minIndex] = minNode.next
            
            minNode.next = null;
            tail.next = minNode;
            tail = tail.next;
        }
        return h.next.next;
    }
}