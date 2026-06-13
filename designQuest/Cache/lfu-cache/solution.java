class LFUCache {
    class Node{
        int key,value,count; 
        Node next,prev;
        Node(int key, int value){
            this.key = key;
            this.value=value;
            this.count = 1;
        }
    }
    class DLL{
        Node head, tail; 
        DLL(){this.head = null; this.tail = null;}
    }
    int capacity;
    int minFreq;
    HashMap<Integer, Node> ref;
    HashMap<Integer, DLL> freqs;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.ref = new HashMap<>();
        this.freqs = new HashMap<>();
    }
    public int get(int key) {
        if(!ref.containsKey(key)) return -1;
        Node old = ref.get(key);
        increment(old);
        return old.value;
    }
    public void put(int key, int value) {
        if(ref.containsKey(key)){
            Node old = ref.get(key);
            old.value = value;
            increment(old);
        } else {
            if(ref.size()==capacity){
                DLL minList = freqs.get(minFreq);
                Node toRemove = minList.head;
                
                ref.remove(toRemove.key);
                removeNodeFrom(minList,toRemove);
            }
            minFreq = 1; 
            
            Node temp = new Node(key,value);
            ref.put(key,temp); 
            if(!freqs.containsKey(1)) freqs.put(1,new DLL()) ;
    
            putLastIn(freqs.get(1),temp);
        }
    }
    public void removeNodeFrom(DLL list,Node x){
        if(x.prev!=null)x.prev.next = x.next;
        else list.head = x.next;
        
        if(x.next!=null)x.next.prev = x.prev;
        else list.tail = x.prev;
    }
    public void putLastIn(DLL list,Node x){
        x.next = null;
        if(list.tail == null){ 
            list.head = list.tail = x;
            x.prev = null;
        } else{ 
            list.tail.next = x;
            x.prev = list.tail;
            list.tail = list.tail.next; 
        }
    }
    public void increment(Node x){
        int oldFreq = x.count;
        DLL oldList = freqs.get(oldFreq);
        removeNodeFrom(oldList,x);
        if(oldFreq==minFreq && oldList.head == null) minFreq++;
        freqs.putIfAbsent(++x.count, new DLL());
        putLastIn(freqs.get(x.count),x);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
   // 1 -> [{}, {}, {}, {}, {}]
    //      head            tail
         
    // 2 -> [{} <-> {} <-> {} <->{}]
    //      head               tail
 
     //     freq = {
        //         1 -> { }
        //         2 -> { }
        //     } 
        //     to ese 1 k andr bht saare honge , hum konse p put kr rhe hai ye kese figure our hoga
            
        // 1 ke andr DLL hi hai.
        // DLL jo hai wo sb nodes ko connect krti hai, head aur tail DLL me hai
        
        // Arey meri baat suno hnji
        // if(!freq.containsKey(1)) freq.put(1,putLastIn()) ;
        // toh tum putlastIn se dll m jo already node ban gya hai uske andr value daal rhe ?
        // likh rahi ho ? putLastIn 
        // smjh rha hu , mujhe dll khali kyon jaara pehli baar m vo ni smjha
        // DLL() ek empty list hai. fir humne us list me kaha put krdo.
        // {1,yes} ye hai freqs haato ab hum iss dll m value alg se daal rhe hai kyon ?
 
  // list null ni hai. usme 2 pointer hai head aur tail. wo dono null hai.
        // pr list m koi value kaha hai node ki , bss pointer h , node ka existence kaha gya smjh ni paarha mai 
        // Node ka kya existence. head = null hai, tail = null hai.to iska mtlb list bhi null hai yahi hua na
        
        
        
        // list ko assigned hai space, head aur tail ko nhi hai
        // smjhi fark ? , head aur tail list p hai agr to agr list ko assigned hai space to mtlb head aur tail ko bhi hona chahiye na , list kya hai humara ek dll ka node hai , uspe head aur tail point kr rhe hai bss
        
        // list ek dll ka object hai
        // jisme head aur tail hai ek node
        // ek head hai. ek tail hai. dono null hai kisi ko point nhi krre , to agr list ki dono cheezein null hai , to list bhi null nahi hui ?
        // meh, nhi
        // usko assign hui hai address 
        
        // achha to head aur tail bhi to uss space ka part hai. yes
        // to tumhara kehna ye pad rha ki space ka part hokr bhi vo null hai lekin list ko space h to vo null nahi h, ese kese
        // arey ek location assign hogi constructor call hote hi.
        // head aur tail bn gye hai uss list k liye humein bss ye pta hai, vo kaha pr pade hue h vo nahi ?? hena hena yahi na ??
        // hn us address pr do variables hai jo null hai
        // achha ek baat batao , hum null kisko kehte hai ?
        // lekin head tail to krte hai. nhi wo null hai. unko kuch assigned nhi hai. 0 space.
        // sirf DLL list ko assigned hai. uske andr ke variables ko ni.
        // aur ye kese pta chla humein , new keyword se ? 
        // null mnje koi value exist ni krti
        // shyd mere basics nahi clear h , baad m krte ye ques. m thoda kuchh smjha hu pr still nahi smjha
