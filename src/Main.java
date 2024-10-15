/*You are given two integers m and n, which represent the dimensions of a matrix.
You are also given the head of a linked list of integers.
Generate an mxn matrix that contains the integers in the linked list presented in spiral order (clockwise) starting from the top-left of the
matrix. If there are remaining empty spaces, fill them with -1.
Return the generated matrix.
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Algorithm algorithm = new Algorithm();
        int i, j, k;
        int m = s.nextInt(); //width
        int n = s.nextInt(); //height
        LinkedList<String> head = new LinkedList<>();
        //fill the linked list with user inputs until there are no more inputs to be had.
        //take the rest of the user input as a string so we can use its contents knowingly.
        String input = s.nextLine();//stores the rest of the user input, since we don't know if it's all ints or if there's fluff.
        String hold;//holds the current char under scrutiny from the user input
        j = 0;
        for(i = 0; i < input.length(); i ++) {
            if(Character.isDigit(input.charAt(i))){
                hold = "";//silly solution to adding the next int as a String. It feels choppy.
                hold = hold + input.charAt(i);
                head.add(j, hold);
                j ++;
            }
        }

        //fill in the remaining space with instances of -1
        if(head.size() < (m * n)) {
            for (i = j; i < (m * n); i++) {//j is at the last occupied node +1 before this.
                head.add("-1");
            }
        }
        System.out.println("Linked List: " + head);
        //there are n * 2 points of interest, corners, where straights start and end.
        //corners pertains to identifying the pivot nodes of the swirl in a matrix.
        int[] corners = new int[2 * n];
        for(i = 0; i < (2 * n); i ++){
            corners[i] = algorithm.edges(m, n, i);
        }
        System.out.println("Corners: " + Arrays.toString(corners));

        //output the first entries up to m-1
        for(i = 0; i < (m - 1); i ++) {
            System.out.print(head.get(i) + " ");
        }
        System.out.println(head.get(m - 1));

        //output the second line according to a diminishing algorithm
        if(n <= 2){
            //just output the first and last line if n < 2. no algorithm required.
            //the algorithm only pertains to the right side for values n < 5.
            for(i = corners[3]; i > corners[2]; i --){
                System.out.print(head.get(i) + " ");
            }
            System.out.println(head.get(m));
        }
        /*
        else{
            //the first time the algorithm applies on the right side is on the second line.
            for(i = corners[4]; i <= corners[5]; i ++) {
                System.out.print(head.get(i) + " ");
            }
            System.out.println(head.get(corners[1] + 1));
        }
        */
        if(n % 2 == 0) {
            for(i = 2; i < (n / 2); i++) {//each outputted line, up to n/2 - 1
                k = i - 1;
                for(j = 1; j < k; j ++){//left nodes
                    System.out.print(head.get(corners[(4 * j)] - (k - j)) + " ");
                }
                for(j = corners[(4 * k)]; j <= corners[1 + (4 * k)]; j ++){//straightaway
                    System.out.print(head.get(j) + " ");
                }
                for(j = (k - 1); j > 0; j --){//right nodes
                    System.out.print(head.get(corners[(4 * j) + 1] + (k - j)) + " ");
                }
                System.out.println(head.get(corners[1] + k));
                //on the left, add (corners[4k] - k) k times, starting at pos 2.
                //every other straightaway sees a diminishment of 1.
                //on the right, add (corners[(1 + 4(j - 1))] - j) j times starting at pos 1.
                //notably, the left side moves from corners[x +n] to corners[x],
                //whereas the right side moves from corners[y] to corners[y +n].
                //if n is even, the right side ends early at the halfway point;
                //if n is odd, the left side ends early at the halfway point.
            }
                //make a loop just for the midpoint line n.
                //the last thing the variable i was used for was to be at (n/2) -1, or (n-1) / 2, which is the midpoint -1.
                i ++;
                for(j = 1; j < (i - 1); j ++){//left nodes. the closest corners would be repeats, so we set j = i - 1.
                    System.out.print(head.get(corners[(4 * j)] - (i - j)) + " ");
                }
                for(j = corners[4 * (i - 1) - 1]; j > corners[4 * (i - 1) - 2]; j --){//straightaway with the innermost corners
                    System.out.print(head.get(j) + " ");
                }
                for(j = (i - 2); j > 0; j --){//right nodes. n being even, we skip the innermost corners, setting j = i - 2 instead of j = i - 1.
                    System.out.print(head.get(corners[(4 * j) + 1] + (i - j)) + " ");
                }
                System.out.println(head.get(corners[1] + i));//make sure this isn't a repeat!!

        }
        else{//each outputted line, up to n/2 truncated. It's the same input, to a different stopping point.
            for(i = 2; i <= ((n - 1)/ 2); i++) {//each outputted line, up to n
                k = i - 1;
                for (j = 1; j < k; j++) {//left nodes
                    System.out.print(head.get(corners[(4 * j)] - (k - j)) + " ");
                }
                for (j = corners[(4 * k)]; j <= corners[1 + (4 * k)]; j++) {//straightaway
                    System.out.print(head.get(j) + " ");
                }
                for (j = (k - 1); j > 0; j--) {//right nodes
                    System.out.print(head.get(corners[(4 * j) + 1] + (k - j)) + " ");
                }
                System.out.println(head.get(corners[1] + k));
                //on the left, add (corners[4k] - k) k times, starting at pos 2
                //every other straightaway sees a diminishment of 1.
                //on the right, add (corners[(1 + 4(j - 1))] - j) j times starting at pos 1
                //if n is even, the right side ends early at the halfway point
                //if n is odd, the left side ends early at the halfway point
            }
                //make a loop just for the midpoint line n.
                //the last thing the variable i was used for was to be at (n/2) -1, or (n-1) / 2, which is the midpoint -1.
                i ++;
                for(j = 1; j < (i - 1); j ++){//left nodes. the closest corners would be repeats, so we set j = i - 1.
                    System.out.print(head.get(corners[(4 * j)] - (i - j)) + " ");
                }
                if(corners[corners.length - 1] - corners[corners.length - 2] > 1) {
                    for(j = corners[corners.length - 2]; j <= corners[corners.length - 1]; j ++){//straightaway with the innermost corners
                        System.out.print(head.get(j) + " ");
                    }
                }
                else{
                    System.out.print(head.get(corners[corners.length - 1] + 1) + " ");
                }
                for(j = (i - 2); j > 1; j --){//right nodes. n being odd, we use the innermost corners, so j = i - 1.
                    System.out.print(head.get(corners[(4 * (j - 1)) + 1] + (i - j)) + " ");
                }
                System.out.println(head.get(corners[1] + i));//make sure this isn't a repeat!!

        }

        //output the second half. corners drop off every loop rather than being introduced.
        //resuming where we left off, i is at the midpoint, so we iterate it once.

        int h = i;
        for(i = h; i < n; i ++){//this is awkward, so I make an emergency extra int because I'm sloppy like that
            h = h - 1;
            //System.out.println("h is " + h + " and i is " + i);
            for(j = 1; j <= n - i; j ++){//left nodes
                System.out.print(head.get(corners[(4 * j)] - (i - j)) + " ");
            }
            for(j = corners[(4 * (n - i)) + 3]; j >= corners[(4 * (n - i)) + 2]; j --){//straightaway
                    System.out.print(head.get(j) + " ");
            }
            for(j = (h - 2); j > 0; j --){//right nodes
                System.out.print(head.get(corners[(4 * j) + 1] + (i - j)) + " ");
            }
            System.out.println(head.get(corners[1] + i));
        }

        //output the final line without issue, but only if it has not been posted yet.
        //when n <= 2, it will have been taken care of
        if(n > 2){
            //from corners[3] to corners[2]
            for(i = corners[3]; i > corners[2]; i --){
                System.out.print(head.get(i) + " ");
            }
            System.out.println(head.get(corners[2]));
        }
        //that's everything. the -1 nodes are already dealt with at the start.
    }
}