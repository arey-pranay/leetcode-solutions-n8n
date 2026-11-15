class Solution {
    public String nearestPalindromic(String n) {
        //rightmost se change krna betetr hai, to mimize the difference
        // long num = String.valueOf(n);
        int len = n.length();
        int i=0,j=len-1;
        long num = Long.parseLong(n);
        List<Long> options = new ArrayList<>();
        StringBuilder sb = new StringBuilder(n);
        while(i<j) sb.setCharAt(j--,n.charAt(i++)); //mirror
        long a = Long.parseLong(sb.toString());
        long b = (long)Math.pow(10,len)+1, c = (long)Math.pow(10,len-1)-1; //999 or 1001
        long d =incdec(n,-1), e = incdec(n,1);
        options.add(a); options.add(b); options.add(c); options.add(d); options.add(e);
        // 1997 
        // inc  or dec  or mirror
        // 2002 or 1881 or 1991
        // 5    or 16   or 6 (the case in which incr is better)
        long ans = Long.MAX_VALUE   , minDiff = Long.MAX_VALUE;
        for(long option : options){
            long diff = Math.abs(num-option);
            if(option != num && diff< minDiff  ||  (diff == minDiff && option < ans)){
                minDiff = diff;
                ans = option; 
            }
        }
        return String.valueOf(ans);
    }
    public long incdec(String s, int change){
        StringBuilder half = new StringBuilder(s.substring(0, (s.length()+1)/2));
        long back = Long.parseLong(half.toString());
        back+=change;
        half = new StringBuilder(String.valueOf(back));
        StringBuilder ans = new StringBuilder(half);
        int start = s.length()%2 == 0 ? half.length()-1 : half.length()-2;
        for(int i=start;i>=0;i--)ans.append(half.charAt(i));
        return Long.parseLong(ans.toString());
    }
}
