class SubrectangleQueries {
    int[][] rectangle; 
    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }
    
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for(int i =row1;i<=row2;i++) for(int j =col1;j<=col2;j++) rectangle[i][j] = newValue;
    }  
    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}
// 4x3 
// 0,0 - 100,100

// 5 1 5
// 4 3 4
// 3 2 1
// 1 1 1

// 5 5 5
// 5 5 5
// 5 5 5
// 10 10 10