// class Solution {
//     public int hammingWeight(int n) {
//         return Integer.bitCount(n);
//     }
// }
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while(n>0){
            if(n%2==1){
                count++;
            } 
            n /= 2; // this means right shift
        }
        return count;
    }
}
// 10100
// 1010
// 101 c++
// 10
// 1 c++
// 0
