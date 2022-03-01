// Time Complexity: O(k^n) + (nklognk) -> O(k^n) where k avg length and n blocks
// Space Complexity: List + recursive stack space O(n)
public class BraceExpansion {
    public String[] expand(String s) {
        if(s == null || s.length() == 0)
            return new String[] {};
        
        List<List<Character>> letters = new ArrayList<>();
        
        // get all letters
        for(int i = 0 ; i < s.length(); i++)
        {
            List<Character> letter = new ArrayList<>();
            if(s.charAt(i) == '{')
            {
                // move to next
                i++;
                while(s.charAt(i) != '}')
                {
                    if(s.charAt(i) != ',')
                       letter.add(s.charAt(i));
                    // increase for alpha or ,
                    i++;
                }
            }
            else // mandatory char
            {
               letter.add(s.charAt(i)); 
            }
            letters.add(letter);
        }
    
        
        List<String> result = new ArrayList<>();
        // build all possible permutations
        backtracking(result, letters, new StringBuilder(), 0);
        
        // copy list to result 
        String ans[] = new String[result.size()];
        for(int i = 0 ; i < result.size(); i++)
        {
            ans[i] = result.get(i);
        }
        // sort to lexico
        Arrays.sort(ans);
        
        return ans;
    }
    
    private void backtracking(List<String> result, List<List<Character>> letters, StringBuilder sb, int index)
    {
        // base case
        if(index == letters.size()) // all blocks seen
        {
            result.add(sb.toString());
            return;
        }
        
        List<Character> letter = letters.get(index);
        // recursive
        for(int i = 0; i < letter.size(); i ++)
        {
            // action for current letter
            sb.append(letter.get(i));
            backtracking(result, letters, sb, index+1);
            // reverse
            sb.setLength(sb.length()-1);
        }
    }
}
