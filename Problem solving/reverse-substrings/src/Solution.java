import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        final String s = new Scanner(System.in).nextLine();

        final String ans = new Solution().reverseSubStrings(s);

        System.out.println(ans);
    }

    private String reverseSubStrings(String s) {

        StringBuilder ans = new StringBuilder();

        // count of how many open parentheses
        int opened = 0;

        Deque<Character> stack = new ArrayDeque<>();

        char[] chars = s.toCharArray();

        for (char c : chars) {

            if (c == '(') { // if parentheses are opened increase count

                opened++;
                stack.push(c);

                continue;
            } else if (c == ')') { // if parentheses are closed find the last opened one

                opened--;

                StringBuilder tmp = new StringBuilder();

                // get the reversed string
                while (stack.peek() != null && stack.peek() != '(') {

                    tmp.append(stack.pop());
                }

                stack.pop();

                if (opened > 0) {

                    // if it's a sub-parentheses add to stack again it will need to be reversed again
                    tmp.chars().forEach(character -> stack.push((char) character));
                } else {

                    // otherwise add to answer
                    ans.append(tmp);
                }

                continue;
            }

            if (opened > 0) { // if parentheses are opened push to stack

                stack.push(c);
            } else { // otherwise append to string

                ans.append(c);
            }
        }

        return ans.toString();
    }
}