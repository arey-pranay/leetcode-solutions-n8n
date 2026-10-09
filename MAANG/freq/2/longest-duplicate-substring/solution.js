class Solution {
    String S= "";
    int PRIME = 29; // prime no. se multiply krne pr no. unique aane k jyada chances hai, criteria is char value se next prime
    // qki prime ki jb power krte hai to uss value k jyada koi factors nahi hote hai 
    int mod = 1000000007;
    public String longestDupSubstring(String s) {
        S = s;
        int start = 1;
        int end = S.length()-1;
        String ans = "";
        // har length ke liye check krne ki jagah search space half kr skte hai in terms of length. BinarySearch        
        while(start<=end){
            int mid = start+(end-start)/2;
            String duplicate = getDuplicate(mid); // iss length (mid) k duplicate kon kon se hai 
            if(duplicate.isEmpty()){
                end = mid-1; // agr x length ka duplicate nhi hai, to x se bda bhi possible nhi hai, agr wo hota to x bhi hota
            } else {
                ans = duplicate; //     kisi length ki duplicate agr mili hai, to usse chhoti length eliminate kr skte hai
                start = mid+1; // mid length ka answer aaya, ab mid se max length ke beech dhundhna hai kuch
            }
        }
        return ans;
    }
    private String getDuplicate(int length){
        long hash = findHash(S.substring(0,length));
        HashMap<Long,Integer> seen = new HashMap<>();// have we seen this hash before, if yes then at what index?
        seen.put(hash,0);
        long primeLast = 1; // b ki power n-1, for rolling hash formula
        // length = mid
        for(int i=0;i<length-1;i++) primeLast = (primeLast*PRIME)%mod;
        
        for(int i=length;i<S.length();i++){
            hash = nextHash(hash, S.charAt(i-length), S.charAt(i), primeLast);
            int start = i-length+1;
            
            
            // possibility kam hai ki 2 alg alg string k hash same aajaye kyonki isliye to prime liye lekin by chance aajata hai to hum char by char check kr rhe hai ki vo dono pkka same to hai na 
            
            if(seen.containsKey(hash)){// agr hash same aajata hai, then a final verification ki strings actually same hai ya nahi
                int prevStart = seen.get(hash);
                if(S.substring(prevStart,prevStart+length).equals(S.substring(i-length+1,i+1)))
                    return S.substring(i-length+1,i+1);
            }
            seen.put(hash,i-length+1);
        }
        
        return "";
    }
    private long findHash(String s){
        long hash=0,base=1; //base should be greater than char, and should probably be prime
        int n = s.length();
        for(int i=n-1;i>=0;i--){
            char c = s.charAt(i);
            hash = (hash+ ((c-'a')+1)*base) %mod; // agr +1 nhi krte, to 'a' har baar 0 contribute krta
            base = (base*PRIME)%mod;// 1 -> 29 -> 29^2 -> 29^3
        }
        return hash%mod;
    }
    // har baar hash nahi nikaal rhe , 5 length hai agr mid to uske liye ek baar hash nikaala aur roll kiya , jisme se pura ek character niklega aur naya ek character aayega aur isliye next hash function use kr rhe hai 
    private long nextHash(long hash, char prev, char next, long primeLast){
        int prevChar = prev-'a'+1;
        int nextChar = next-'a'+1;
        long toRemove = (prevChar*primeLast) %mod;
        long remaining = ((hash-toRemove)%mod + mod)%mod;
        return (remaining*PRIME + nextChar)%mod;
    }
}
