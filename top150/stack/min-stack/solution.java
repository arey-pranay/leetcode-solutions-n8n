class MinStack {
    ListNode head;
    int min;
    public MinStack() {
        head=null;
        min=Integer.MAX_VALUE;
    }
    
    public void push(int val) {
        if(min>val){
            min=val;
        }
        head=new ListNode(val,min,head);
    }
    
    public void pop() {
        head=head.next;
        if(head==null){
            min=Integer.MAX_VALUE;
        }else{
            min=head.currentMin;
        }
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.currentMin;
    }
}

class ListNode{
    int val;
    int currentMin;
    ListNode next;
    ListNode(){

    }
    ListNode(int val){
        this.val=val;
    }
    ListNode(int val,int currMin,ListNode next){
        this.val=val;
        this.currentMin=currMin;
        this.next=next;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */