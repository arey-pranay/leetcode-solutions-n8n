class MedianFinder {
    PriorityQueue<Integer> minHeap ;
    PriorityQueue<Integer> maxHeap ;
    boolean isEven = true;
    public MedianFinder() {
       minHeap = new PriorityQueue<>(); // minheap m humaare saare bade numbers jaayenge lekin vo niklne m unn sab bade numbers ka sbse chhote chhote niklenge
       maxHeap = new PriorityQueue<>(Collections.reverseOrder());// maxheap m humare saare chhote numbers jaayenge lekin  vo nikaalne m sab chhote numbers m se jo bade bade hai vo niklenge
    }
    
    public void addNum(int num) {
        if(isEven){
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        isEven = !isEven;
    }
    
    public double findMedian() {
        if(isEven) return (double)(minHeap.peek() + maxHeap.peek()) /2;
        return maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */