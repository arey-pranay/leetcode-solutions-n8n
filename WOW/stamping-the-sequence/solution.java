class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] SC = stamp.toCharArray(), TC = target.toCharArray();
        int slen = SC.length, tlen = TC.length;
        List<Integer> ans = new ArrayList<>();
        
        boolean flag = true;
        int i=0,j=0,done=0;
        while(flag){
            flag = false;
            for(i=0;i<=tlen-slen; i++){
                for(j=0;j<slen;j++) if(TC[i+j] != '-' && TC[i+j] != SC[j]) break;
                if(j==slen){
                    boolean changed = false;
                    for(j=i; j<i+slen;j++) {
                        if(TC[j]!='-'){
                            TC[j] = '-';
                            done++;
                            changed = true;
                        }
                    }
                    if(changed){
                        ans.add(i);
                        flag = true;
                    }
                }
            }
        }
        
        if(done != tlen) return new int[]{};
        
        int sz = ans.size();
        int[] ret = new int[sz];
        for(i=sz-1;i>=0;i--) ret[sz-i-1] = ans.get(i);
        return ret;
    }
}
// Input: stamp = "abca", target = "aabcaca"

// 'a' 'a b c a' 'c a'
// a b c a
//         a b c a
//     a b c a


// stamp = 'abc'
//    abababcbcbababcbc 
// a b a b a b c b c b a b a b c b c
//         a b c           a b c
//     a b c   a b c   a b c   a b c
// a b c           a b c   

// 0 8 2 6 10 14 4 12