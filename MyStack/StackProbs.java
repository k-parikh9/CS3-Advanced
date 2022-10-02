import java.util.Stack;

public class StackProbs {

    public static Stack<Integer> doubleUp(Stack<Integer> nums){
        //Base case
        if(nums.size() == 1){
            nums.push(nums.peek());
            return nums;
        }

        Integer num = nums.pop();
        doubleUp(nums);
        nums.push(num);
        nums.push(num);

        return nums;
    }

    public static Stack<Integer> posAndNeg(Stack<Integer> nums){
        //Sort between positive and negative integers
        Stack<Integer> pos = new Stack<Integer>();
        Stack<Integer> neg = new Stack<Integer>();
        while(!nums.isEmpty()){
            int num = nums.pop();
            if(num < 0){
                neg.push(num);
            }
            else{
                pos.push(num);
            }
        }

        //Create sorted stack
        while(!pos.isEmpty()){
            neg.push(pos.pop());
        }

        return neg;
    }

    public static Stack<Integer> shiftByN(Stack<Integer> nums, int n){
        Stack<Integer> original = new Stack<Integer>();
        Stack<Integer> shift = new Stack<Integer>();
        Stack<Integer> shifted = new Stack<Integer>();

        //Separate data into different stacks
        while(nums.size() > 0){
            if(nums.size() > n){
                original.push(nums.pop());
            }
            else{
                shift.push(nums.pop());
            }
        }

        //Add non-shifted numbers to stack
        while(original.size() > 0){
            shifted.push(original.pop());
        }

        //Add shifted numbers to stack
        while(shift.size() > 0){
            shifted.push(shift.pop());
        }

        return shifted;
    }

    public static String reverseVowels(String str){
        Stack<String> vowels = new Stack<String>();
        String allVowels = "aeiouAEIOU";

        for(int i = 0; i < str.length(); i++){
            if(allVowels.contains(str.substring(i, i+1))){
                vowels.push(str.substring(i, i+1));
            }
        }

        for(int i = 0; i < str.length(); i++){
            if(allVowels.contains(str.substring(i, i+1))){
                str = str.substring(0, i) + vowels.pop() + str.substring(i + 1);
            }
        }

        return str;
    }

    public static boolean bracketBalance(String s){
        Stack<String> brackets = new Stack<String>();

        for(int i = 0; i < s.length(); i++){
            String character = s.substring(i, i+1);
            if(character.equals("(") || character.equals("[")){
                brackets.push(character);
            }
            else{
                //Exit if stack is empty
                if(brackets.empty()){
                    return false;
                }
                 String last = brackets.peek();
                 String set = last + character;
                 if(!(set.equals("()") || set.equals("[]"))){
                    return false;
                 }
                 else{
                     brackets.pop();
                 }
            }
        }
        return true;
    }
}
