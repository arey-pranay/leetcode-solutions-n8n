class Solution {
    List<List<String>> ans = new ArrayList<>();
    HashMap<String,HashSet<String>> graph = new HashMap<>();
    HashSet<String> wordSet;
    String target;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      wordSet = new HashSet<>(wordList);
      Queue<String> q = new LinkedList<>();
      wordSet.remove(beginWord);
      q.add(beginWord);
      target = beginWord;
      boolean found = false;
      while(!q.isEmpty()){ //O(n)
        int sz = q.size();
        HashSet<String> nextLevel = new HashSet<>();
        while(sz-->0){
          String word = q.poll();
          //bfs = processing level by level. But "Processing" means different things in every single context.
          for(String neigh : wordSet){
            if(!canGo(word,neigh)) continue;
            if(!graph.containsKey(neigh)) graph.put(neigh,new HashSet<>());
            graph.get(neigh).add(word);
            nextLevel.add(neigh);
            if(neigh==endWord) found=true;
          }
        }
        wordSet.removeAll(nextLevel);
        if(!found)q.addAll(nextLevel);
      }
      List<String> path = new ArrayList<>();
      path.add(endWord);
      func(endWord,path);
      return ans;
    }

    public void func(String currWord, List<String> path){
      if(currWord.equals(target)) {
        List<String> temp = new ArrayList<>(path);
        Collections.reverse(temp);
        ans.add(temp);
        return;
      }
      if(!graph.containsKey(currWord)) return;
      for(String neigh : graph.get(currWord)){
        path.add(neigh);
        func(neigh,path);
        path.remove(path.size()-1);
      }
    }
    
    public boolean canGo(String a , String b){
        int count = 0;
        for(int i =0 ; i<a.length();i++){
          if(a.charAt(i)!=b.charAt(i)) count++;
          if(count>1) return false;
        }
        return count==1;
    }
}