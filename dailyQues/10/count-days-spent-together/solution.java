class Solution {
    static int[] dayCount = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        
        int aa = normalizedDays(arriveAlice); //01-05 => Jan 5th => 5
        int la = normalizedDays(leaveAlice); // 04-27 => Apr 27th => 120+27 => 147
        int ab = normalizedDays(arriveBob); 
        int lb = normalizedDays(leaveBob);
        
        int start = Math.max(aa,ab);
        int end = Math.min(la,lb);
        return Math.max(0,end - start + 1);
        
    }
    private int normalizedDays(String s){
        // days since start of the year;
        // 12 - 31
        int month = (s.charAt(0)-'0')*10 + (s.charAt(1)-'0');
        int days =  (s.charAt(3)-'0')*10 + (s.charAt(4)-'0');
        for(int i=0;i<month-1;i++) days += dayCount[i];
        return days;
    }
}