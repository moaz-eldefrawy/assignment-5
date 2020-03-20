package eg.edu.alexu.csd.datastructure.linkedList;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.SinglyLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.Classes.Polynomial;

import java.util.Scanner;

public class JavaApplicationUI {

    public static char getVarName(Scanner cin)
    {

        char poly = '\0';
        String in;
        do
        {
            System.out.println("Choose Variable name: ");
            in = cin.next();
            poly = in.charAt(0);
        }
        while(in.length() != 1 || "ABCR".indexOf(poly) == -1);

        return poly;
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        Scanner cin = new Scanner(System.in);


        System.out.println("Welcome to the Polynomial Solver!");
        String in = "wahtever";
        while(true)
        {
            if(in.length() != 0) {
                System.out.println("Please choose an action");
                System.out.println("-----------------------------");
                System.out.println("1- Set a polynomial variable");
                System.out.println("2- Print the value of a polynomial variable");
                System.out.println("3- Add two polynomials");
                System.out.println("4- Subtract two polynomials");
                System.out.println("5- Multiply two polynomials");
                System.out.println("6- Evaluate a polynomial at some point");
                System.out.println("7- Clear a polynomial variable");
            }
            in = cin.nextLine();
            if(in.equals("1"))
            {
                char poly = '\0';
                do
                {
                    System.out.println("Choose Variable name: ");
                    in = cin.nextLine();
                    poly = in.charAt(0);
                }
                while(in.length() != 1 || "ABC".indexOf(poly) == -1);
                System.out.println("Enter the number of polynomial terms: ");
                int termsNum = cin.nextInt();

                if(termsNum == 0)
                    throw new RuntimeException("Setting Polynomial to null is invalid");

                System.out.println("Insert the polynomial terms in the form:");
                System.out.println("coeff1 exp1");
                System.out.println("coeff2 exp2....");
                int[][] terms = new int[termsNum][2];
                for(int i = 0; i < termsNum;i++)
                {
                    terms[i][0] = cin.nextInt();
                    terms[i][1] = cin.nextInt();
                }

                p.setPolynomial(poly, terms);
                System.out.println("Polynomial "+ poly + " is set!");
                System.out.println(p.print(poly));
            }
            else if(in.equals("2"))
            {
                char poly = '\0';
                do
                {
                    System.out.println("Choose Variable name: ");
                    in = cin.nextLine();
                    poly = in.charAt(0);
                }
                while(in.length() != 1 || "ABC".indexOf(poly) == -1);

                    if(p.getPolyReference(poly).isEmpty())
                    System.out.println("Polynomial is Empty");
                else
                    System.out.println(p.print(poly));
            }
            else if(in.equals("3"))
            {
                System.out.println("Select Poly1: ");
                char poly1 = getVarName(cin);

                if(p.getPolyReference(poly1).isEmpty())
                {
                    System.out.println("Polynomial is Empty");
                    continue;
                }

                System.out.println("Select Poly2: ");
                char poly2 = getVarName(cin);

                if(p.getPolyReference(poly2).isEmpty())
                {
                    System.out.println("Polynomial is Empty");
                    continue;
                }

                p.add(poly1, poly2);
                System.out.print("The Sum is: ");
                System.out.println(p.print('R'));
            }
            else if(in.equals("4"))
            {
                System.out.println("Select Poly1: ");
                char poly1 = getVarName(cin);

                if(p.getPolyReference(poly1).isEmpty())
                {
                    System.out.println("Polynomial is Empty");
                    continue;
                }

                System.out.println("Select Poly2: ");
                char poly2 = getVarName(cin);

                if(p.getPolyReference(poly2).isEmpty())
                {
                    System.out.println("Polynomial is Empty");
                    continue;
                }

                p.subtract(poly1, poly2);
                System.out.print("The difference is: ");
                System.out.println(p.print('R'));
            }
            else if(in.equals("5"))
            {
                System.out.println("Select Poly1: ");
                char poly1 = getVarName(cin);

                if(p.getPolyReference(poly1).isEmpty())
                {
                    System.out.println("Polynomial is Empty");
                    continue;
                }

                System.out.println("Select Poly2: ");
                char poly2 = getVarName(cin);

                if(p.getPolyReference(poly2).isEmpty())
                {
                    System.out.println("Polynomial is Empty");
                    continue;
                }

                p.multiply(poly1, poly2);
                System.out.print("The product is: ");
                System.out.println(p.print('R'));
            }
            else if(in.equals("6"))
            {
                System.out.println("Select Poly: ");
                char poly = getVarName(cin);

                if(p.getPolyReference(poly).isEmpty())
                {
                    System.out.println("Polynomial is Empty");
                    continue;
                }

                System.out.println("Enter the number you want to evaluate: ");
                float n = cin.nextFloat();

                System.out.println("The value: " + p.evaluatePolynomial(poly, n));
            }
            else if(in.equals("7"))
            {
                System.out.println("Select Poly: ");
                char poly = getVarName(cin);
                p.clearPolynomial(poly);
            }
        }
    }

}
