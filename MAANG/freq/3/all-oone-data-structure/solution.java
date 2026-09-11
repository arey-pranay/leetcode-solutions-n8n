class AllOne {
    class Node{
        Node prev;
        Node next;
        int freq;
        HashSet<String> keys;
        Node(int freq){
            this.freq = freq;
            this.keys = new HashSet<>();
        }
    }
    HashMap<String,Node> map;
    Node head,tail;
    public AllOne() {
        head = null;
        tail = null;
        map = new HashMap<>();
    }
    
    public void inc(String key) {
        if(head==null && tail==null) { head = createAndAttach(key,1,null,null); tail = head; map.put(key,head); return; }
        
        if(!map.containsKey(key)){ // freq = 1;
            if(head.freq!=1) head = createAndAttach(key,1,null,head);                
            
            head.keys.add(key);
            map.put(key,head);
            
            return;
        }
        
        Node oldNode = map.get(key);
        oldNode.keys.remove(key);
        int oldFreq = oldNode.freq;
        
        Node nextNode = oldNode.next;
        if(nextNode==null || nextNode.freq != oldFreq+1) nextNode = createAndAttach(key,oldFreq+1,oldNode,nextNode);
        if(nextNode.next==null) tail=nextNode;    //nextNode is supposed to be tail kyuki uske baad me nhi hai na kuch    
        
        nextNode.keys.add(key);
        map.put(key,nextNode);
        
        if(oldNode.keys.isEmpty()) removeNode(oldNode);
    }
    
    public void dec(String key) {
        
        Node oldNode = map.get(key);
        oldNode.keys.remove(key);
        int oldFreq = oldNode.freq;
        
        if(oldFreq==1){
            map.remove(key);
            if(oldNode.keys.isEmpty()) removeNode(oldNode);
            return;
        }
        
        Node prevNode = oldNode.prev;
        if(prevNode == null || prevNode.freq != oldFreq-1) prevNode = createAndAttach(key,oldFreq-1,prevNode,oldNode);
        
        if(prevNode.prev==null) head=prevNode; //prevNode is supposed to be head kyuki uske pehle me nhi hai na kuch
        
        prevNode.keys.add(key);
        map.put(key,prevNode);
        
        if(oldNode.keys.isEmpty()) removeNode(oldNode);
    }
    
    public String getMaxKey() {
        if(tail==null) return "";
        return tail.keys.iterator().next();
    }
    
    public String getMinKey() {
        if(head==null) return "";
        return head.keys.iterator().next();
    }
    
    public void removeNode(Node node){        
        if(node.prev==null && node.next==null){
            head = null; 
            tail=null;
            return;
        }
        
        if(node.prev==null){
            node.next.prev = null; 
            head = node.next; 
            node.next=null; 
            return;
        }
        
        if(node.next==null){
            node.prev.next = null; 
            tail = node.prev; 
            node.prev=null; 
            return;
        }
        
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
    
    public Node createAndAttach(String key,int freq, Node prev , Node next){
        Node temp = new Node(freq);
        temp.keys.add(key);
        temp.prev = prev;
        temp.next = next;
        if(next!=null)next.prev = temp;
        if(prev!=null)prev.next = temp;
        return temp;
    }
    
    public void printList(){
        Node curr = head;
        while(curr!=null){
            System.out.print(curr.freq+": "+curr.keys+ " --> ");   
            curr=curr.next;         
        }        
        System.out.println();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */