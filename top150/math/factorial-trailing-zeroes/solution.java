class Solution {
    public int trailingZeroes(int n) {
        int i=1;
        int ans = 0;
        while(n>=Math.pow(5,i)) ans += (n/(Math.pow(5,i++)));
        return ans;
    }
}
// trailing zero ke liye we need to count 10 kitni baar aayega a*b*c*d... ki sequence me
// for every 10, we need 2 and 5. 2 to har even number se aajayega, 5 se zyaada hi baar
// so we just need to find, kitni baar 5 aayega is poori multiplication sequence me

// 5 , 25 , 125 se cross hua to jitna usse divide ho rha hai utna badha dena hota hai (5 helps make 1 10. 25 helps make 2 tens, 125 helps make 3 tens, etc) 
//jese 20 tk to 20/4 hi zeroes aayenge kyonki 5 hi utne bann paaye hai 
//lekin 30 tk jb hum aagye to 25 m do 5 aagye na to islie