class LFUCache {
    int capacity;
    int minFreq;
    
    class Node{
        int key, value, count;
        Node next, prev;
        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }
    class DLL{
        Node head,tail;
        DLL(Node node){
            this.head = node;
            this.tail = node;
        }
    }
    
    Map<Integer,Node> map = new HashMap<>(); // node of a given key
    Map<Integer,DLL> freqs = new HashMap<>(); // list of nodes of a given freqt
    
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
    }
  

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        increment(node);
        return node.value;
    }
    
    public void put(int key, int value) {        
        if(map.containsKey(key)){
            Node oldNode = map.get(key);
            oldNode.value = value;
            increment(oldNode);
        } else {
            if(map.size()==capacity){
                DLL minDLL = freqs.get(minFreq);
                Node toRemove = minDLL.head;
                map.remove(toRemove.key);
                removeNodeFrom(minDLL,toRemove);
                if(minDLL.head==null)freqs.remove(minFreq);
            }
            minFreq=1;
            Node node = new Node(key,value);
            map.put(key,node);
            if(!freqs.containsKey(1)) freqs.put(1,new DLL(node));
            else putAtEnd(freqs.get(1),node);            
        } 
    }
    
    public void increment(Node node){
        int oldFreq = node.count;
        DLL oldDLL = freqs.get(oldFreq);
        removeNodeFrom(oldDLL, node);
        if(oldDLL.head==null) {
            freqs.remove(oldFreq); 
            if(oldFreq == minFreq) minFreq++;
        }
        node.count++;
        if(!freqs.containsKey(oldFreq+1)) freqs.put(node.count,new DLL(node));
        else putAtEnd(freqs.get(node.count),node);
    }
    
    public void putAtEnd(DLL dll , Node node){
        node.next = null;
        dll.tail.next = node;
        node.prev = dll.tail;
        dll.tail = node;
    }
    
    public void removeNodeFrom(DLL dll, Node node){
        if(node.prev==null) dll.head = node.next;
        else node.prev.next = node.next;
        if(node.next==null) dll.tail = node.prev;
        else node.next.prev = node.prev;
        node.prev = null; node.next=null;
    }
}



/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */