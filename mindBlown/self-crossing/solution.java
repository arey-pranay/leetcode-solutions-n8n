class Solution {
    int[] arr;
    public boolean isSelfCrossing(int[] d) {
        int n = d.length;
        arr=d;
        if(n<=3) return false;
        // current line (i) can collide with i-3, i-4, i-5
        for(int i=3;i<n;i++) if(touches3(i) || (i>3 && touches4(i)) || (i>4 && touches5(i))) return true;
        return false;
    }
    public boolean touches3(int i){
        return arr[i] >= arr[i-2] && arr[i-1] <= arr[i-3];
    }
    public boolean touches4(int i){
        return arr[i-1]==arr[i-3] && arr[i] >= arr[i-2] - arr[i-4];
    }
    public boolean touches5(int i){
        return arr[i-1] <= arr[i-3] 
        && arr[i-2] >= arr[i-4] 
        && arr[i] >= arr[i-2] - arr[i-4] 
        && arr[i-1] >= arr[i-3] - arr[i-5];
    }
}





    // for(int i=3;i<n;i++){
        //     if( (distance[i] >= distance[i-2]) && (distance[i-1]<=distance[i-3]) ) return true;        
        // }
        
        // while(i<n && (d[i] > d[i-2])) i++;
        // if(i==n) return false;
        // //if i is going fdown, then i-2 was going up, and i-4 was going down
        
        // if(d[i] >= (d[i-2] d[i-4])) i++;
        
        // else if(d[i-1] <= d[i-3]) return true;