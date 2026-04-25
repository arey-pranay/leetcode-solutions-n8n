class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> b.profit - a.profit);
        for(int i =0; i<profits.length;i++) pq.add(new Node(profits[i],capital[i]));
        int taken = 0;
        Queue<Node> q = new LinkedList<>();
        while(taken < k && !pq.isEmpty()){
            Node curr = pq.poll();
            if(curr.capital <= w){
                w += curr.profit;
                taken++;
                while(!q.isEmpty())pq.offer(q.poll());
            }
            else q.add(curr);
        }
        return w;
    }
    class Node{
        int profit;
        int capital;
        Node(int p, int c){
            this.profit = p;
            this.capital = c;
        }
    }
}