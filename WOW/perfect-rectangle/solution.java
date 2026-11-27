class Solution {
    HashSet<String> corners = new HashSet<>();
    int b=Integer.MAX_VALUE,l=Integer.MAX_VALUE,t=Integer.MIN_VALUE,r=Integer.MIN_VALUE;
    
    public boolean isRectangleCover(int[][] rectangles) {
        int sumTotal =  sumOfAllRect(rectangles);
        int sumOfParent = getRectArea(new int[]{l,b,r,t},false);
        String[] parentKeys = getKeys(new int[]{l,b,r,t});  
        if(corners.size() != 4) return false;
        for(String key : parentKeys) if(!corners.contains(key)) return false;
        return sumTotal == sumOfParent;
    }
    private int sumOfAllRect(int[][] rectangles){
        int sum =0;
        for(int[] rectangle : rectangles) sum += getRectArea(rectangle,true);
        return sum;
    }
    private int getRectArea(int[] rect, boolean updateAndCheck){ 
        if(updateAndCheck) updateEndsAndCheckCorners(rect);
        int len = rect[3]-rect[1];
        int wid = rect[2]-rect[0];
        return len*wid;
    }
    private void updateEndsAndCheckCorners(int[] rect){
        int x1 = rect[0],  y1 = rect[1],  x2=rect[2],  y2=rect[3];
        l = Math.min(l, x1); b = Math.min(b,y1);r = Math.max(r, x2); t = Math.max(t,y2); 
        
        String[] keys = getKeys(rect);
        for(String key : keys) if(corners.contains(key)) corners.remove(key); else corners.add(key);
    }
    
    private String[] getKeys(int[] rect){
        return new String[]{getKey(rect[0],rect[1]),getKey(rect[0],rect[3]),getKey(rect[2],rect[1]),getKey(rect[2],rect[3])};
    }
    private String getKey(int x, int y){
        return x+","+y;
    }
}