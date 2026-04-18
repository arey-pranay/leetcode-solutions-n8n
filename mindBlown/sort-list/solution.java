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
    public ListNode sortList(ListNode head) {
        int n = 0;
        ListNode end = head;
        if(head==null) return null;
        while(end!=null){
            n++;
            end = end.next;
        }
        return divide(head,n);
    }
    public ListNode divide(ListNode start, int n){
        if(n == 1){
            start.next=null;
            return start;
        }
        ListNode mid = start;
        int midCount = n/2;
        for(int i=0;i<midCount-1;i++) mid = mid.next;
        ListNode head2 = mid.next;
        mid.next=null;
        ListNode left = divide(start,midCount);
        ListNode right = divide(head2,n-midCount);
        return merge(left,right);
    }
    public ListNode merge(ListNode left ,ListNode right){
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(left!=null && right!=null){
            if(left.val < right.val){
                tail.next = left;
                left = left.next;
            }
            else{
                tail.next=right;
                right = right.next;
            }
            tail = tail.next;
        }
        if(left == null) tail.next = right;
        else tail.next = left;
        return head.next;
    }
}