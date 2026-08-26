class Solution {
    List<String> ans = new ArrayList<>();
    HashSet<String> wordSet;
    String ip;
    public List<String> wordBreak(String s, List<String> wordDict) {
      wordSet = new HashSet<>(wordDict); 
      ip = s;
      func(0,new StringBuilder(),new StringBuilder());
      return ans;
    }
    private void func(int i, StringBuilder currWord, StringBuilder done){
        if(i==ip.length()){            
            if(wordSet.contains(currWord.toString())){
                done.append(currWord);
                ans.add(done.toString());
            }
            return;
        }
       
        if(wordSet.contains(currWord.toString())){
            int oldLength = done.length(); // cats
            done.append(currWord).append(" "); // cats and 
            func(i,new StringBuilder(),done); //cats and
            done.setLength(oldLength); //cats
        }
        
         //cat
        currWord.append(ip.charAt(i));//cats
        func(i+1,currWord,done);//cats
        currWord.deleteCharAt(currWord.length()-1);//cat
        
    }
}