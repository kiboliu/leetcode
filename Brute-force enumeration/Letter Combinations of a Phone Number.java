/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
*/

// A tricky method, use linkedlist, first store element in it, then pop it out and add a new element to it and store.
class Solution{
    public List<String> letterCombinations(String digits){
        LinkedList<String> ans=new LinkedList<String>();
        if(digits.length()==0) return ans;
        String[] mapping=new String[]{"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        ans.add("");
        for(int i=0;i<digits.length();i++){
            int x=Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t=ans.remove();
                for(char s:mapping[x].toCharArray()) ans.add(t+s);
            }
        }
        return ans;
    }
}

//dfs, ps: Character.getNumericValue() : convert char to int.
class Solution{
    public List<String> letterCombinations(String digits){
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return res;
        String[] list = {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        dfs(res, new StringBuilder(), digits, list, 0);
        return res;
    }
    private void dfs(List<String> res, StringBuilder tmp, String digits, String[] list, int start) {
        if (tmp.length() == digits.length()) {
            String s = tmp.toString();
            res.add(s);
            return;
        } else {
            for(int i = 0; i < list[Character.getNumericValue(digits.charAt(start))].length(); i++) {
                tmp.append(list[Character.getNumericValue(digits.charAt(start))].charAt(i));
                dfs(res, tmp, digits, list, start + 1);
                tmp.deleteCharAt(tmp.length() - 1);
            }
        }
    }
}