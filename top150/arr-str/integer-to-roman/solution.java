class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder("");// 
        while(num >0) num = func(num,sb);
        return sb.toString();
    }
    public int func(int num,StringBuilder sb){
        int pow = (int) Math.log10(num);
        if(Integer.toString(num).charAt(0)=='4'){
            if(pow == 2){sb.append("CD"); return num-400;}
            if(pow == 1){sb.append("XL"); return num-40;}
            sb.append("IV");return num-4;
        }else if(Integer.toString(num).charAt(0)=='9'){
            if(pow == 2){sb.append("CM"); return num-900;}
            if(pow == 1){sb.append("XC"); return num-90;}
            sb.append("IX");return num-9;
        }
        if(num >= 1000){sb.append("M");return num-1000;}
        if(num >= 500){ sb.append("D");return num-500;}
        if(num >= 100){ sb.append("C");return num-100;}
        if(num >= 50){ sb.append("L");return num-50;}
        if(num >= 10){ sb.append("X");return num-10;}
        if(num >= 5){ sb.append("V");return num-5;}        
        sb.append("I"); return num-1;
    }
}




// // class Solution {
// //     public String intToRoman(int num) {
// //         String res = "";
// //         int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
// //         String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
// //         for (int i = 0; i < values.length; i++) {
// //             while (num >= values[i]) {
// //                 num -= values[i];
// //                 res += symbols[i];
// //             }
// //         }
// //         return res;
// //     }
// // }
// class Solution {
//     public String intToRoman(int num) {
        
//         StringBuilder ans = new StringBuilder();
//         int curMul = 1;
//         int curDigit = 0;
//         while (num != 0) {
//             curDigit = num % 10;

//             if (curDigit == 9) {
//                 ans.insert(0, getRomanNumeral(curMul * 10));
//                 ans.insert(0, getRomanNumeral(1 * curMul));
//             }
//             else if (curDigit == 4) {
//                 ans.insert(0, getRomanNumeral(curMul * 5));
//                 ans.insert(0, getRomanNumeral(1 * curMul));
//             }
//             else if (curDigit > 4) {
//                 curDigit -= 5;
//                 addCharacters(ans, getRomanNumeral(1 * curMul), curDigit);
//                 ans.insert(0, getRomanNumeral(5 * curMul));
//             }
//             else {
//                 addCharacters(ans, getRomanNumeral(1 * curMul), curDigit);
//             }

//             curMul *= 10;
//             num /= 10;
//         }
//         return ans.toString();
//     }

//     private void addCharacters(StringBuilder ans, char c, int digit) {
//         for (int i = 0; i < digit; i++) {
//             ans.insert(0, c);
//         }
//     }

//     private char getRomanNumeral(int x) {
//         if (x == 1) return 'I';

//         if (x == 5) return 'V';

//         if (x == 10) return 'X';

//         if (x == 50) return 'L';

//         if (x == 100) return 'C';

//         if (x == 500) return 'D';

//         return 'M';
//     }
// }