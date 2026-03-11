class Solution {
    public int bitwiseComplement(int n) {
        String s = Integer.toBinaryString(n);
        s = s.replace('0','-');
        s = s.replace('1','0');
        s = s.replace('-','1');
        return Integer.parseInt(s,2);
    }
}