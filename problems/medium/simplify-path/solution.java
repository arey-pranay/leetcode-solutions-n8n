class Solution {
        public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        String[] arr = path.split("/");
        for (int i = 0;i < arr.length;i++)
        {
            if (arr[i].equals(".."))
            {
                if (!stack.isEmpty())
                    stack.pop();
           //     else
           //         continue;
            }
            else if (arr[i].equals(".") || arr[i].equals(""))
                continue;
            else
                stack.push(arr[i]);
        }
        for (int i = 0; i < stack.size();i++)
        {
            res.append("/").append(stack.get(i));
        }
    
        if (res.isEmpty())
            return "/";
        else
            return res.toString();
    }

}