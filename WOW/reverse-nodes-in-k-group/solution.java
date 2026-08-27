class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode temp = head;
        int total = 0;
        while(temp!=null){temp=temp.next;total++;}
        
        temp = dummy;
        total -= (total%k);
        for(int i=0;i<total;i+=k){
          ListNode[] ans = rev(temp.next,k); 
          temp.next =  ans[0];
          temp = ans[1];
        }
        
        return dummy.next;
    }
    public ListNode[] rev(ListNode head , int k){
      ListNode curr = head;
      ListNode prev = null;
      while(k-->0){
        ListNode fwd = curr.next;
        curr.next = prev;
        prev = curr;
        curr = fwd;
      }
      head.next = curr;
      return new ListNode[]{prev,head};
    }
}    