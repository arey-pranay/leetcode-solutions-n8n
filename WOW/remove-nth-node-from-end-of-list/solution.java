class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        func(dummy,n,0);
        return dummy.next;
    }
    public int func(ListNode head, int n, int curr){
        if(head==null) return 0;
        curr = 1 + func(head.next,n,curr);
        if(curr==n+1) head.next=head.next.next;
        return curr;
    }
}