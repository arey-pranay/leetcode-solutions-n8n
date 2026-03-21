class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        return dummy.next;
    }
    
    //     public ListNode mergeKLists(ListNode[] lists) {
    //     ListNode h = new ListNode(0);
    //     ListNode tail = new ListNode(0);
    //     h.next = tail;
        
    //     int minIndex = -1;
    //     while (minIndex != -2) {
    //         ListNode minNode = new ListNode(Integer.MAX_VALUE);
    //         minIndex = -2;
    //         for (int i = 0; i < lists.length; i++) {
    //             if (lists[i] != null && lists[i].val < minNode.val) {
    //                 minNode = lists[i];
    //                 minIndex = i;
    //             }
    //         }
    //         if(minIndex == -2)break;
    //         ListNode temp = minNode.next;
    //         lists[minIndex] = temp;
    //         tail.next = minNode;
    //         tail = tail.next;
    //         minNode.next = null;
    //     }
    //     return h.next.next;
    // }
}
