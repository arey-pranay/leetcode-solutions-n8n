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
        if(lists.length==0) return null;
        int j=0;
        while(j<lists.length && lists[j]==null) j++;
        if(j==lists.length) return null;
        ListNode a = lists[j];
        for(int i=j+1;i<lists.length;i++){
            ListNode b = lists[i];
            a = merge2Lists(a,b);
        }
        return a;
    }
    
    public ListNode merge2Lists(ListNode a, ListNode b){
        ListNode headA = a;
        ListNode headB = b;
        
        ListNode head = new ListNode(-1);
        ListNode dummy = head;
        
        while(headA!=null && headB!=null){
            if(headA.val<headB.val){
                dummy.next = headA;
                headA = headA.next;
            } else{
                dummy.next = headB;
                headB = headB.next;
            }
            dummy = dummy.next;
        }
        
        while(headA != null){dummy.next = headA; dummy = dummy.next; headA=headA.next;}
        while(headB != null){dummy.next = headB; dummy = dummy.next; headB=headB.next;}
        
        return head.next;
    }
}