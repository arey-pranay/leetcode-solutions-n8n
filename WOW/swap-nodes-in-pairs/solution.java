class Solution {
    public ListNode swapPairs(ListNode head) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode curr = dummy;
      while(curr!=null){
          if(curr.next==null) break;
          curr.next = swapPair(curr.next,curr.next.next);
          curr = curr.next.next;
      }
      return dummy.next;
    }
    public ListNode swapPair(ListNode left , ListNode right){
      if(left==null || right==null) return left;
      ListNode temp = right.next;
      right.next = left;
      left.next= temp;
      return right;
    }
}