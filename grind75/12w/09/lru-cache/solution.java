class LRUCache {
  class Node{
    int key;
    int value;
    Node prev;
    Node next;
    Node(int k, int v){
      this.key = k;
      this.value = v;
      this.next =null;
      this.prev =null;
    }
  }
    int capacity;
    HashMap<Integer,Node> hm;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hm = new HashMap<>();
    }
    
    public int get(int key) {
        if(!hm.containsKey(key)) return -1;
        Node node = hm.get(key);
        removeNode(node);
        addAtLast(node);
        return node.value;
    }
    public void put(int key, int value) {
        if(hm.containsKey(key)){ 
            Node node = hm.get(key);
            removeNode(node);
            node.value = value;
            addAtLast(node);
            return;
        }
        if(hm.size()==capacity) removeFirst();
        Node newNode = new Node(key,value);
        addAtLast(newNode);
    }
  public void removeNode(Node node){
    hm.remove(node.key);
    if(node==head) head = node.next; else node.prev.next = node.next;
    if(node==tail) tail = node.prev; else node.next.prev = node.prev;
  }
  public void addAtLast(Node node){
   //first node 
    if(tail==null) head=tail=node;
    else{
      //node already exist
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    hm.put(node.key,node);
  }
  public void removeFirst(){
    if(head==null)return;
    hm.remove(head.key);
    if(head==tail){head= tail=null; return;}
    head = head.next;
    head.prev = null;
  }
}