class Solution {
    public int[] plusOne(int[] digits) {
        boolean allNine = true;
        for(int i : digits){
            if(i != 9){
                allNine = false;
                break;
            }
        }
        if(allNine){
            int[] nines = new int[digits.length+1];
            nines[0] = 1;
            return nines;
        }
        
        int carry =1;
        for(int i =digits.length-1;i>=0;i--){
            if(digits[i] != 9){
                digits[i]++;
                break;
            } else digits[i] = 0;
        }
        return digits;
    }
}