class Solution {
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int[] result = new int[l1 + l2]; // store digits of the answer. no. of digits in product of two numbers can not exceed the sum of their digits

        //  12 => i=1
        // 126 => j=2
        // res => l1+l2 = 5;
        //the product of 2 single-digit numbers can never exceed 2 digits. so any digit will not affect any result digit beyond its left neighbour
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {

                int n1 = num1.charAt(i) - '0'; //2,2,2,1
                int n2 = num2.charAt(j) - '0'; //6,2,1,6
                int prod = n1 * n2; //12 , 4 , 2, 6,2,6

                int p1 = i + j, p2 = i + j + 1;
                int sum = prod + result[p2]; // product 18 aaya, pehle se 6 rkha tha, to ab 24 hogya. usme 2 carry, and 4 assign firse.

                result[p1] += sum / 10; //carry           
                result[p2] = sum % 10; // ones place          
            }
        }

       
        StringBuilder sb = new StringBuilder();
        for (int digit : result)
            if (sb.isEmpty() && digit == 0) continue;
            else sb.append(digit);
        
        return sb.isEmpty() ? "0" : sb.toString();
    }
}

 // [_, _, 2 , 5, 2]
        //     1   2  6  x
        // 0   1  2  3  4
        
//                                         1         2      3
//                                         4         5      6
// ------------------------------------------------------------
//     ->                             (m-3*n-1)     (m-2*n-1)  m-1*n-1 
// +   ->             (m-3 * n-2)     (m-2 * n-2)   (m-1*n-2)  X
// +   ->(m-3 * n-3)  (m-2 * n-3)     (m-1*n-3)        X       X
// -----------------------------------------------------------------

// -----------------------------------------------------------------

//   ( .. + carry)   ((n*m-1 + m*n-1 + carry)%10 = 8 -> carry2) ((m*n)%10 = 8 -> carry1)


//