class Solution {
    String[] numbers = new String[] { "", "One ", "Two ", "Three ", "Four ", "Five ", "Six ",
            "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ",
            "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    String[] tens = new String[] {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ",
            "Eighty ", "Ninety " };

    public String numberToWords(int num) {
        if(num==0) return "Zero";
        ArrayList<String> groups = new ArrayList<>();
        int index = 0;
        while (num != 0) {
            int curr = num % 1000;
            groups.add(threeDigit(index, curr));
            num /= 1000;
            index++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = groups.size() - 1; i >= 0; i--)
            ans.append(groups.get(i));
        return ans.toString().trim();
    }

    public String threeDigit(int index, int num) {
        StringBuilder sb = new StringBuilder();
        int curr = num % 100;
        num /= 100;
        if (num > 0) {
            sb.append(numbers[num]);
            sb.append("Hundred ");
        }
        if (curr < 20)
            sb.append(numbers[curr]);
        else {
            sb.append(tens[curr / 10]);
            sb.append(numbers[curr % 10]);
        }
        String extra = sb.isEmpty() || index == 0 ? ""
                : index == 1 ? "Thousand " : index == 2 ? "Million " : index == 3 ? "Billion " : "Trillion ";
        sb.append(extra);
        return sb.toString();
    }
}