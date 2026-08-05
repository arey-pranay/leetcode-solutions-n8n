class Solution {
  class Pair{
    String word;
    int count;
    Pair(String w, int c){
      this.word = w;
      this.count = c;
    }
  }
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> hm = new HashMap<>();
        for(String word : words) hm.put(word,hm.getOrDefault(word,0)+1);
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
          if(a.count != b.count) return b.count-a.count;
          return a.word.compareTo(b.word);
        });
        for(Map.Entry<String,Integer> e : hm.entrySet()){pq.add(new Pair(e.getKey(),e.getValue()));}
        List<String> ans = new ArrayList<>();
        for(int i=0;i<k;i++) ans.add(pq.poll().word);
        return ans;
    }
}
