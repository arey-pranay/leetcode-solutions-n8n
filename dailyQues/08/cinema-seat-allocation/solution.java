class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int ans=2*n;
        Arrays.sort(reservedSeats,(a,b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        int r = reservedSeats.length;
        int i=0;
        while(i<r){
            int row = reservedSeats[i][0];
            boolean[] booked = new boolean[11];
            while(i<r && reservedSeats[i][0]==row){
                booked[reservedSeats[i][1]] = true;
                i++;
            }
            boolean g1 = check4(booked,2,5);
            boolean g2 = check4(booked,4,7);
            boolean g3 = check4(booked,6,9);
            if(g1 && g3){continue;}
            else if(g1 || g2 || g3) ans--;
            else ans-=2;
        }
        return ans;
    }
    public boolean check4(boolean[] booked,int start, int end){
        for(int i=start;i<=end;i++)if(booked[i]) return false;
        return true;
    }
}