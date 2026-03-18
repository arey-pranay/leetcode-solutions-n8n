class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        // har ek "i" ke baad ascending array me kitni dal skte hai wo hai ans[i];
        // Arrays.fill(ans,0);
        //monotonic stack
        for(int i=n-1; i>=0; i--){
            while(!st.isEmpty() && st.peek() < heights[i]){
                st.pop();
                ans[i]++;
            }
            if(!st.isEmpty()) ans[i]++;
            st.push(heights[i]); 
        }
        return ans;
    }
}
