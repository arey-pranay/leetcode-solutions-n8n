class LRUCache {
    class Node{
        Node next;
        Node prev;
        int key;
        int value;
        Node(int key, int value){
            this.value = value;
            this.key = key;
            this.next = null;
            this.prev = null;
        }
    }
    HashMap<Integer,Node> hm;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity; 
        hm= new HashMap<>();
        
    }
    
    public int get(int key) {
        if(!hm.containsKey(key)) return -1;
        Node x = hm.get(key);
        remove(x);
        putLast(x);
        return x.value;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node old = hm.get(key);
            old.value = value;
            remove(old);
            putLast(old);
        } else {
            if(hm.size()==capacity){
                hm.remove(head.key);   
                remove(head);
            }
            Node temp =new Node(key,value);
            putLast(temp);
            hm.put(key,temp);
        }
    }
    public void remove(Node x){
        if(x.prev==null) head = x.next;
        else x.prev.next = x.next;

        if(x.next==null) tail = x.prev;
        else x.next.prev = x.prev;
    }
    public void putLast(Node x){
        x.next = null;
        if(tail==null){
            head = tail = x;
            x.prev = null;
        }else{
            tail.next = x;
            x.prev = tail;
            tail = tail.next;
        }
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */