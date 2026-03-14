class Solution {
    char[] abc;
    int count;
    public String getHappyString(int n, int k) {
        abc = new char[] { 'a', 'b', 'c' };
        StringBuilder str = new StringBuilder();
        count = 0;
        return func(str,n,k);
    }

    public String func(StringBuilder str,int N, int K) {
        if (str.length() == N) { 
            count++;
            if (count == K) return str.toString(); 
            return "";
        }
        for (int i = 0; i < 3; i++) {
            if (!str.isEmpty() && str.charAt(str.length() - 1) == abc[i]) continue;
            str.append(abc[i]); 
            String temp = func(str,N,K);
            if(temp.length()>0) return temp;
            str.deleteCharAt(str.length() - 1);
        }
        return "";
    }

}