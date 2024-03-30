import java.util.Scanner;

/**
 * This program displays quiz questions to users and takes in their answers.
 * It displays 4 options with letters A, B, C and D and computes the final
 * score as a percentage of the total. This score is displayed to the user.
 */
public class Quiz {
    public static void main(String[] args) {
        String userAns; //the answer from the user
        int score = 0; // the cumulative score as the user submit answers

        String question1 = "Which of the following is a conditional?\nA. for\nB. if\nC. while\nD. Array\n>>>";
        String question2 = "One of the following is not a conditional operator\nA. &&\nB. ||\nC. !\nD. +\n>>>";
        String ans2 = "D";
        String question3 = "Which of the following programming languages in loosely typed?\nA. Java\nB. Python\nC. C\nD. C++\n>>>";
        String ans3 = "B";
        String question4 = "What is the value of 8 % 5 + 4 / 2 in java?\nA. 5\nB. 3.5\nC. 3\nD. 1\n>>>";
        String ans4 = "A";
        String question5 = "Which data type in java accepts floating point numbers?\nA. double\nB. int\nC. char\nD. short\n>>>";
        String ans5 = "A";

        // scanner variable to accept user input
        Scanner in = new Scanner(System.in);

        // display questions to user and consume answers
        System.out.print(question1);
        userAns = in.next();
        switch (userAns) {
            case "B":
                score += 1;
                break;
            default:
                break;
        }
        System.out.print(question2);
        userAns = in.next();
        if (userAns.equals(ans2)) score += 1;
        System.out.print(question3);
        userAns = in.next();
        if (userAns.equals(ans3)) score += 1;
        System.out.print(question4);
        userAns = in.next();
        if (userAns.equals(ans4)) score += 1;
        System.out.print(question5);
        userAns = in.next();
        if (userAns.equals(ans5)) score += 1;
        System.out.printf("Your final score is %.2f%%\n", (score / 5.0) * 100);
    }
}
