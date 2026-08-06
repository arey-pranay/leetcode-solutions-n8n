class FreqStack {

    HashMap<Integer,Integer> hm;
    List<Stack<Integer>> stacks;

    public FreqStack() {
        this.hm = new HashMap<>();
        this.stacks = new ArrayList<>();
        stacks.add(new Stack<>());
    }
    
    public void push(int val) {
        int freq = hm.getOrDefault(val,0)+1;
        hm.put(val, freq);
        if(freq==stacks.size()) stacks.add(new Stack<>());
        stacks.get(freq).add(val);
    }
    
    public int pop() {
        Stack<Integer> st = stacks.get(stacks.size()-1);
        int val = st.pop();
        if(st.isEmpty()) stacks.remove(stacks.size()-1);
        hm.put(val,hm.get(val)-1);
        return val;
    }
}