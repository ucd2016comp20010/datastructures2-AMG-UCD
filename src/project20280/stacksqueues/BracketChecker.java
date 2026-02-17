package project20280.stacksqueues;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public static boolean checkParentheses(String in) {
       LinkedStack<Character> ls = new LinkedStack<>();
        for (int i = 0; i < in.length(); ++i) {
            char current = in.charAt(i);
            
            if (current == '(' || current == '{' || current == '[') {
                ls.push(current);
            } 
        
            else if (current == ')' || current == '}' || current == ']') {
                
                if (ls.isEmpty()) {
                    return false;
                }
                
                char top = ls.top();
                
                
                if ((current == ')' && top == '(') || 
                    (current == '}' && top == '{') || 
                    (current == ']' && top == '[')) {
                    ls.pop();
                } else {
                    
                    return false;
                }
            }   
        }
        return ls.isEmpty();
    }


    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct"
                "a{b[c]d}e", // correct"
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            boolean isBalanced = checkParentheses(input);
            System.out.println("isBalanced " + (isBalanced ? " yes! " :" no! ") + input);
        }
    }
}