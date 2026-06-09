/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node,Node> oldToNew = new HashMap<>();
        Node curr = head;
        while(curr!=null){
            oldToNew.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while(curr != null){
            oldToNew.get(curr).next = oldToNew.get(curr.next);
            oldToNew.get(curr).random = oldToNew.get(curr.random);
            curr = curr.next;
        }
        return oldToNew.get(head);
    } 
    // public Node copyRandomList(Node head) {
    //     Node root = head;
    //     while(head!=null){
    //         Node copy = new Node(head.val);
    //         Node near = head.next;
    //         head.next = copy;
    //         copy.next = near;
    //         head = near;
    //         near = near.next;
    //     }
    // }
}