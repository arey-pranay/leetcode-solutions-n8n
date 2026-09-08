class Solution {
    int m,n;
    int[] heights, leftBound, rightBound;
    public int maximalRectangle(char[][] matrix) {
        m = matrix.length; //rows
        n = matrix[0].length; //cols
        
        //har ek col ke liye h,lb,rb
        heights = new int[n];
        leftBound = new int[n];
        rightBound = new int[n];
        Arrays.fill(rightBound,n);
        int ans = Integer.MIN_VALUE;
        for(int row=0;row<m;row++){
            updateHeight(matrix[row]); // current number ki height
            updateLeftBound(matrix[row]); // wo height left me kahan tk chlegi
            updateRightBound(matrix[row]); // wo height right me kahan tk chlegi
            ans = Math.max(ans,findMaxArea()); // calculate for the rows covered, then cover the next row and calculate again
            // printArr(heights);
            // printArr(leftBound);
            // printArr(rightBound);
            // System.out.println();
        }
        
        return ans;
    }
    
    public void updateHeight(char[] row){
        for(int col=0;col<n;col++){
            if(row[col] == '1') heights[col]++;
            else heights[col] = 0;
        }
    }
    public void updateLeftBound(char[] row){
        int left=0;
        for(int col=0;col<n;col++){
            if(row[col] == '1') leftBound[col]=Math.max(leftBound[col],left);
            else {
                leftBound[col] = 0;
                left=col+1;
            }
        }    
    }
    
    public void updateRightBound(char[] row){
        int right=n;
        for(int col=n-1;col>=0;col--){
            if(row[col] == '1') rightBound[col]=Math.min(rightBound[col],right);
            else {
                rightBound[col] = n;
                right=col;
            }
        }    
    }
    public int findMaxArea(){
        int area = 0;
        for(int i=0;i<n;i++){
            int width = rightBound[i]-leftBound[i];
            area = Math.max(area,heights[i] * width);
        }
        return area;
    }
    
    public void printArr(int[] arr){
        for(int num : arr) System.out.print(num+" ");
        System.out.println();
    }
}