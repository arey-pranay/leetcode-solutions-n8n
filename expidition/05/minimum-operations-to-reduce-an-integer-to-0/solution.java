class Solution {
    public int minOperations(int n) {
        String s = Integer.toBinaryString(n);
        int cont = 0;
        int ans = 0;
        StringBuilder sb = new StringBuilder(s);
        for(int i=sb.length()-1;i>=0;i--){
            char c = sb.charAt(i);            
            if(c=='1') cont++;
            if(c=='0'){
                if(cont==1){
                    ans++;
                    cont = 0;
                }
                else if(cont > 1){
                    ans++;
                    sb.setCharAt(i,'1');
                    cont = 1;
                }
            }
        }
        if(cont==1) ans++;
        else if(cont > 1) ans+=2;
        return ans;
    }
}
