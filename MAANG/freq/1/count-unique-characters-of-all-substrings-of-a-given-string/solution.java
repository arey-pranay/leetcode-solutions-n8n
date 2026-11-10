class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        long all = ((long)n*(n+1)*(n+2))/6;
        Map<Character, List<Integer>> indices = new HashMap<>();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(!indices.containsKey(c)) indices.put(c,new ArrayList<>());
            indices.get(c).add(i);
        }

        for(List<Integer> l : indices.values()){
          for(int i=0;i<l.size();i++){
             int index = l.get(i);
             int prev = i==0 ? -1 : l.get(i-1); 
             int next = i==l.size()-1 ? n : l.get(i+1);
             int total = (index+1)*(n-index);//all substrings of that index 
             int good = (index-prev)*(next-index);//good substrings of that index
             int bad = total-good; //invalid substrings
             all -= bad;
          }
        }
       return (int)all; // isko run kro na
    }
}
// BDCASRTYAYK T , TAA , TSY  T-TAA-TSY 
// ( 1*3 )
// ( 2*2 )
// ( 3*1 )

//   1*4 + 3*2 + 2*3 + 4*1

//   n/1 + n/2 + n/n 

//   i^2 
// 18 16 14 8 4
