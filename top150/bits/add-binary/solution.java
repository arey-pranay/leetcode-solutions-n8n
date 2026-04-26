class Solution {
    public String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder("");
        while(i>=0 || j>=0){
            int A = i>=0 ? a.charAt(i--) - '0' : 0;
            int B = j>=0 ? b.charAt(j--) - '0' : 0;
            int sum = A+B+carry;
            sb.append(sum%2);
            carry = sum > 1 ? 1 : 0;
        }   
        if(carry!=0) sb.append(carry);
        return sb.reverse().toString();     
    }
}
