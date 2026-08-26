class Solution {
  HashMap<String,Boolean> memo = new HashMap<>();
  HashSet<Integer> set = new HashSet<>();
  int last;
  public boolean canCross(int[] stones) {
    for(int stone : stones) set.add(stone);
    last=stones[stones.length-1];
    return func(1,1); // from i=0, we can only go k=1 as per constraints and practicality, so let's start from i=1 and k=1
  }
  public boolean func(int i, int k){
    if(i==last) return true; //reached
    if(!set.contains(i)) return false; //stone does not exist there
    
    String key = i+","+k; // for memoizing, because n+1 is out of integer bounds so could not use indexing for memoizing
    if(memo.containsKey(key)) return memo.get(key);
    
    boolean ans = k>1 ? func(i+k-1,k-1) : false; // if k=0 or k=1 then there is no point going back or staying at the same point, we skip it
    ans |= func(i+k,k) || func(i+k+1,k+1);
    memo.put(key,ans);
    return ans;
  }
}
// 0 -> 1
// 1 k=1
