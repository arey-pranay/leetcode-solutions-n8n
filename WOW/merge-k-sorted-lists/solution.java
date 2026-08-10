class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
      if(lists.length==0 ) return null;
      PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
      for(ListNode l : lists) if(l!=null) pq.add(l);
      ListNode head = new ListNode();
      ListNode tail=head;
      while(!pq.isEmpty()){
        ListNode temp = pq.poll();
        tail.next = temp;
        if(temp.next!=null)pq.add(temp.next);
        temp.next=null;
        tail=tail.next;
      }
      return head.next;
    }
}