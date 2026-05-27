class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        
        int wordCount = words.length;
        int unit = words[0].length();
        int size  = unit*wordCount;
        int n = s.length();
        
        HashMap<String,Integer> hm = new HashMap<>();
        for(int i = 0; i<wordCount; i++) hm.put(words[i], hm.getOrDefault(words[i],0)+1);
        
        for(int x=0;x<unit;x++){
            HashMap<String,Integer> hm2 = new HashMap<>();
            int i = x;
            int count = 0;
            for(int j=x;j<= n-unit; j+= unit){
                String currWord = s.substring(j,j+unit);
                if(hm.containsKey(currWord)){
                    hm2.put(currWord,hm2.getOrDefault(currWord,0)+1);
                    count++;
                    
                    while(hm2.get(currWord) > hm.get(currWord)){
                        String startWord = s.substring(i,i+unit);
                        hm2.put(startWord,hm2.get(startWord)-1);
                        i += unit;
                        count--;
                    }
                    
                    if(count == wordCount) ans.add(i);
                
                }else{
                    count = 0;
                    i = j + unit;
                    hm2.clear();
                }
            }
        }
        return ans;
    }
}