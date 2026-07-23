class Solution {
    public int largestRectangleArea(int[] heights) {        
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        int n = heights.length;
        for(int i=0;i<=n;i++){
            int h = i==n ? 0 :heights[i];
            while(!st.isEmpty() && h<heights[st.peek()]){
                int height = heights[st.pop()];
                int width = st.isEmpty() ? i : i-st.peek()-1;//2
                ans = Math.max(ans,height*width);
            }
            st.push(i);
        }
        return ans;
    }
}