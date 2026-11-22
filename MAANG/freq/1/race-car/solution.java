class Solution {
    HashMap<Integer,Integer> memo = new HashMap<>();
    public int racecar(int t) {
        if(t==0) return 0;
        if(memo.containsKey(t)) return memo.get(t);
        int n = (int) (Math.log(t)/Math.log(2)) +1; //
        // int p=0, s= 1;
        int exact = (1 << n) -1; 
        if(exact == t) {memo.put(t,n);return n;} // for t = 17 se 31, sbke liye exact is 31
        int min = racecar(exact-t);// we exceeded, so we will solve for diff. and add rev later
        int prevStop = (1<<(n-1));     //(2 ^ (n-1))   
        for(int m=0;m<n-1;m++){ // m represents humne reverse krke, kitni baar acccerate kiya
            int revStep = 1<<m; //hum itna reverse me accelerate krege, to t se aur door jaate jayege
            int curr = racecar(t-prevStop + revStep); // total dist itna hua: jitna difference hai, usme jitna aur reverse krdiya
            int total = curr + m;  //  m baar neg acc, plus us position ka answer
            min = Math.min(min,total);
        }
        memo.put(t,1 + n + min);
        return 1 + n + min;  // n ops till here, then 1 rev
    }
}
// n + 1 + racecar(diff)

// 17
// AAAA
// p=15 s=16 
// 1 2 -> A
// 3 4 -> A
// 7 8 -> A
// 15 16 -> A
// 31 32 -> R 
// 31 -1 -> A
// 30 -2 -> A
// 28 -4 -> A
// 24 -8 -> A
// 16 -16 -> R
// 16 1   -> A
// 17 2   -------

// 15 -1 R
// 15 1  R
// 16 2  A
// 18 -1 R
// 18 -1 A
// 17   ----

// ---
// 15 16
// 15 -1
// 14 -2
// 12 -4