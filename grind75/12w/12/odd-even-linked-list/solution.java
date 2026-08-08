class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode headA = head;
        ListNode headB = head.next;
        ListNode tailA = func(headA,headB);
        tailA.next = headB;
        return head;
    }
    public ListNode func(ListNode a, ListNode b){
      if(b==null || b.next==null) return a;
      a.next = a.next.next; //b.next
      b.next = b.next.next;
      return func(a.next,b.next);
    }
}

// 1 2 3 4 5
// a b

//   1->3
//   2->4