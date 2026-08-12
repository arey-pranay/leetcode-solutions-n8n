class MedianFinder {
    PriorityQueue<Integer> bigs;
    PriorityQueue<Integer> smalls;
    boolean isEven;
    public MedianFinder() {
        this.bigs = new PriorityQueue<>(); //minheap kyuki bde walo me se smallest chahiye median ke liye
        this.smalls = new PriorityQueue<>((a,b)->b-a); // maxheap
        this.isEven = true;
    }
    
    public void addNum(int num){
        if(this.isEven){ // ek bar smalls me daaleneg ek baar bigs me
          smalls.add(num);
          bigs.add(smalls.poll());// balance krne ke liye smalls ka biggest number nikaal kr bigs me daala hai
        } else {
          bigs.add(num);
          smalls.add(bigs.poll());
        }
        this.isEven = !this.isEven;
    }
    
    public double findMedian() {
        return isEven ? (bigs.peek()+smalls.peek())/2.0 : bigs.peek(); //by default uneven case me bigs me zyada elements hai
    }
}
