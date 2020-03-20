package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;
import eg.edu.alexu.csd.datastructure.linkedList.Classes.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial implements IPolynomialSolver {


    SinglyLinkedList A, B, C, R;

    public class Pair
    {
        public int exp;
        public int coeff;

        Pair(int exp, int coeff)
        {
            this.exp = exp;
            this.coeff = coeff;
        }

        public String toString(){
            return this.coeff + "x^" + this.exp ;
        }
    }

    Polynomial(){
        this.A = new SinglyLinkedList();
        this.B = new SinglyLinkedList();
        this.C = new SinglyLinkedList();
        this.R = new SinglyLinkedList();
    }


    public void setPolynomial(char poly, int [][] items) {
        Arrays.sort(items, new Comparator<int[]>() {
            public int compare(final int[] entry1,
                               final int[] entry2) {

                if (entry1[1] > entry2[1])
                    return -1;
                else
                    return 1;
            }
        });

        SinglyLinkedList p = getPolyReference(poly);
        p.clear();
        p.add(new Pair(items[0][1], items[0][0]));

        for(int i = 1; i < items.length;i++)
        {
            int exp = items[i][1];
            int coeff = items[i][0];

            Pair lastNode = (Pair)p.get(p.size()-1);
            if(coeff == 0) continue;
            if(exp == lastNode.exp)
            {
                coeff += lastNode.coeff;
                p.set(p.size() - 1, new Pair(exp, coeff));
            }else
                p.add(new Pair(exp, coeff));
        }

        if( ((Pair)p.get(0)).coeff == 0 )
            p.remove(0);
    }

    private SinglyLinkedList getPoly(char poly) throws RuntimeException{

        switch( poly ) {
            case 'A':
                if(this.A.size() != 0)
                    return this.A;
                else
                    throw new RuntimeException("Polynomial doesn't exist");

            case 'B':
                if(this.B.size() != 0)
                    return this.B;
                else
                    throw new RuntimeException("Polynomial doesn't exist");

            case 'C':
                if(this.C.size() != 0)
                    return this.C;
                else
                    throw new RuntimeException("Polynomial doesn't exist");

            case 'R':
                if(this.R.size() != 0)
                    return this.R;
                else
                    throw new RuntimeException("Polynomial doesn't exist");

            default:
                throw new RuntimeException("Polynomial doesn't exist");
        }
    }

    public SinglyLinkedList getPolyReference(char poly) throws RuntimeException{
        switch( poly ) {
            case 'A':
                return this.A;

            case 'B':
                return this.B;

            case 'C':
                return this.C;

            case 'R':
                return this.R;

            default:
                throw new RuntimeException("Polynomial with such a character doesn't exist");
        }
    }

    @Override
    public String print(char poly) {

        SinglyLinkedList polynomial = getPoly(poly);
        String polyString = new String("");
        for(int i=polynomial.size()-1; i>=0; i--){
            Pair a = (Pair) polynomial.get(i);
            polyString += a.toString();
            if(i != 0)
                polyString +=" + ";
        }
        return polyString;
    }

    @Override
    public void clearPolynomial(char poly) {
        SinglyLinkedList polynomial = getPolyReference(poly);
        polynomial.clear();
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        SinglyLinkedList p = getPoly(poly);

        float ans = 0.0f;

        for(int i = 0; i < p.size();i++)
        {
            Pair node = (Pair)p.get(i);
            ans += node.coeff * Math.pow(value, node.exp);
        }

        return ans;
    }

    @Override
    public int[][] add(char poly1, char poly2) {

        ArrayList<int[]> ans = new ArrayList<int[]>();
        SinglyLinkedList a = getPolyReference(poly1);
        SinglyLinkedList b = getPolyReference(poly2);

        int sizeA = a.size();
        int sizeB = b.size();


        for(int i = 0; i < sizeA;i++)
        {
            Pair node = (Pair)a.get(i);
            ans.add(new int[] {node.coeff, node.exp});
        }

        for(int i = 0; i < sizeB;i++)
        {
            Pair node = (Pair)b.get(i);
            ans.add(new int[] {node.coeff, node.exp});
        }

        Collections.sort(ans, new Comparator<int[]>() {
            public int compare(final int[] entry1,
                               final int[] entry2) {

                if (entry1[1] > entry2[1])
                    return -1;
                else
                    return 1;
            }
        });
        for(int i = 1; i < ans.size();i++)
        {
            if(ans.get(i)[1] == ans.get(i-1)[1])
            {
                ans.get(i-1)[0] += ans.get(i)[0];
                ans.remove(i);
                i--;
            }
        }
        setPolynomial('R', ans.toArray(new int[ans.size()][]));
        return ans.toArray(new int[ans.size()][]);
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        ArrayList<int[]> ans = new ArrayList<int[]>();
        SinglyLinkedList a = getPolyReference(poly1);
        SinglyLinkedList b = getPolyReference(poly2);

        int sizeA = a.size();
        int sizeB = b.size();


        for(int i = 0; i < sizeA;i++)
        {
            Pair node = (Pair)a.get(i);
            ans.add(new int[] {node.coeff, node.exp});
        }

        for(int i = 0; i < sizeB;i++)
        {
            Pair node = (Pair)b.get(i);
            ans.add(new int[] {-1*node.coeff, node.exp});
        }

        Collections.sort(ans, new Comparator<int[]>() {
            public int compare(final int[] entry1,
                               final int[] entry2) {

                if (entry1[1] > entry2[1])
                    return -1;
                else
                    return 1;
            }
        });

        for(int i = 1; i < ans.size();i++)
        {
            if(ans.get(i)[1] == ans.get(i-1)[1])
            {
                ans.get(i-1)[0] += ans.get(i)[0];
                ans.remove(i);
                i--;
            }
        }

        setPolynomial('R', ans.toArray(new int[ans.size()][]));
        return ans.toArray(new int[ans.size()][]);
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        ArrayList<int[]> ans = new ArrayList<int[]>();
        SinglyLinkedList a = getPolyReference(poly1);
        SinglyLinkedList b = getPolyReference(poly2);

        int sizeA = a.size();
        int sizeB = b.size();

        for(int i = 0; i < sizeA;i++)
        {
            Pair node1 = (Pair)a.get(i);
            for(int j = 0; j < sizeB;j++)
            {
                Pair node2 = (Pair)b.get(j);
                ans.add(new int[] {node1.coeff*node2.coeff, node1.exp+node2.exp});
            }
        }

        Collections.sort(ans, new Comparator<int[]>() {
            public int compare(final int[] entry1,
                               final int[] entry2) {

                if (entry1[1] > entry2[1])
                    return -1;
                else
                    return 1;
            }
        });

        for(int i = 1; i < ans.size();i++)
        {
            if(ans.get(i)[1] == ans.get(i-1)[1])
            {
                ans.get(i-1)[0] += ans.get(i)[0];
                ans.remove(i);
                i--;
            }
        }

        setPolynomial('R', ans.toArray(new int[ans.size()][]));
        return ans.toArray(new int[ans.size()][]);

    }
    public void printPoly(char poly)
    {
        SinglyLinkedList p = getPolyReference(poly);
        for(int i = 0; i < p.size();i++)
        {
            Pair node = (Pair) p.get(i);
            if(node.coeff == 0)
                continue;
            if(node.coeff > 0)
                System.out.print("+");

            System.out.print(node.coeff + "*X^" + node.exp + " ");
        }
        System.out.println();
    }
}
