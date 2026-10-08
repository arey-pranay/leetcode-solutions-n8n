class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        int n = heights.length;
        for(int i=0;i<=n;i++){
            int currH = i==n ? 0: heights[i];
            while(!st.isEmpty() && currH < heights[st.peek()]){
                int peekI = st.pop();
                int height = heights[peekI];
                int width = st.isEmpty() ? i : i-st.peek()-1;
                int area = height*width;
                // System.out.println(area + " = " + height + " * " +width);
                ans = Math.max(ans, area);
            }
            st.push(i);
        }
        return ans;
    }
}
