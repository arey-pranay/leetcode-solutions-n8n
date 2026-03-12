class LRUCache {
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        
        // Constructor
        public Node(int x, int y) {
            this.key = x;
            this.value = y;
            this.next = null; // next pointer initially null
            this.prev = null; // previous pointer initially null
        }
    }

    HashMap <Integer,Node> map ;
    int capacity ; 
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;        
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node needed = map.get(key);
            if(needed.next == null) return needed.value;
            removeNode(needed);
            addAtTail(needed);
            return needed.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node toUpdate = map.get(key);
            toUpdate.value = value;
            removeNode(toUpdate);
            addAtTail(toUpdate);
        }
        else {
           if(capacity<=map.size()){
            map.remove(head.key);
            removeNode(head);
           }
           Node toAdd = new Node(key,value);
           map.put(key,toAdd);
           addAtTail(toAdd);
        }
       
    }
    
    public void addAtTail(Node n){
        n.next=null;
        // We get a lawaris node, we mark its prev as current tail, attach tail's next to the lawaris node
        // and now our tail moves ahead and that lawaris node is our tail
        if (head == null) {
            head = n;
            tail = n;
        } else{
            n.prev = tail;
            tail.next = n;
            tail = tail.next;
        }
    }
    public void removeNode(Node needed) { 
        if(needed.prev == null) head = needed.next;
        else needed.prev.next = needed.next;
        if(needed.next == null) tail = needed.prev;
        else needed.next.prev = needed.prev;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */