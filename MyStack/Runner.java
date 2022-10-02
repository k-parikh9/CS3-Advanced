import java.util.Stack;

public class Runner {
    public static void main(String[] args){
        //1
        int[] test1 = {1, 3, 5, 0, -1};
        System.out.println(StackProbs.doubleUp(makeStack(test1)));

        //2
        int[] test2 = {2, 9, -4, 3, -1, 0, -6};
        System.out.println(StackProbs.posAndNeg(makeStack(test2)));

        //3
        int[] test3 = {7, 23, -7, 0, 22, -8, 4, 5};
        System.out.println(StackProbs.shiftByN(makeStack(test3), 3));

        //4
        System.out.println(StackProbs.reverseVowels("hello how are you"));

        //5
        System.out.println(StackProbs.bracketBalance("(([()])))"));
        System.out.println(StackProbs.bracketBalance("([()[]()])()"));
    }

    public static Stack<Integer> makeStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int num : nums)
            stack.push(num);
        return
                stack;
    }

}
