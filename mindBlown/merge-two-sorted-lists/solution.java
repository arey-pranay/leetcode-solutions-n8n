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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null) return null;
        if(list1 == null) return list2; if(list2 == null) return list1;
        if(list2.val < list1.val) return mergeTwoLists(list2,list1);
        ListNode head = list1;
        list1 = list1.next;
        head.next = mergeTwoLists(list1,list2);
        return head;
    }
}

// 1 3 4
// 2 4

// 1 -> (...) // 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null

// -----------

// 3 4 
// 2 4

// 1 -> (...) // 1 -> 2 -> 3 -> 4 -> 4 -> null
// -------------

// 4 
// 3 4

// 2 -> (...) // 2 -> 3 -> 4 -> 4 -> null

// ---------------

// 4
// 4

// 3 -> (...) // 3 -> 4 -> 4 -> null

// ------------------

// _
// 4

// 4 -> (...) // 4 -> 4 -> null

// -------------

// _
// _

// 4 ->(...) // 4 -> null

// ------------

