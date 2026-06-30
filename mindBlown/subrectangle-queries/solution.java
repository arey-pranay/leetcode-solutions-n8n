// batch updates - mtlb ki hum sirf aakhrir baar traverse krenge tb tk bss updates store krte jaayenge 
// latest valid update dhundhke 
class SubrectangleQueries {
    private int[][] rectangle;
    // Store updates as {row1, col1, row2, col2, newValue}
    private List<int[]> updates;

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
        this.updates = new ArrayList<>();
    }
    
    // O(1) Time Complexity
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        updates.add(new int[]{row1, col1, row2, col2, newValue});
    }
    
    // O(U) Time Complexity, where U is the number of updates
    public int getValue(int row, int col) {
        // Look from the newest update to the oldest
        for (int i = updates.size() - 1; i >= 0; i--) {
            int[] update = updates.get(i);
            // Check if the coordinate falls inside this updated subrectangle
            if (row >= update[0] && row <= update[2] && col >= update[1] && col <= update[3]) {
                return update[4];
                // r1 c1 r2 c2 value
            }
        }
        // If no updates hit this cell, return the original value
        return rectangle[row][col];
    }
}