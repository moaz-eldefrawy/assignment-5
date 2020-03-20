package eg.edu.alexu.csd.datastructure.linkedList.Tests;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PloynomialTest {

    @Test
    void test() {
        Polynomial arr = new Polynomial();
        int[][] b= { {0,1},{2,2} };
        arr.setPolynomial('B', b );

        int[][] a= { {0,1},{-1,10} };
        arr.setPolynomial('A', a );

        arr.multiply('A','B');
        assertEquals(arr.print('R').toString(), "-2x^12");


        // clear and set again
        arr.clearPolynomial('B');
        arr.setPolynomial('B',b);
        arr.multiply('A','B');
        assertEquals(arr.print('R').toString(), "-2x^12");


        // set directly
        arr.setPolynomial('B',b);
        arr.multiply('A','B');
        assertEquals(arr.print('R').toString(), "-2x^12");

        // set again
        arr.setPolynomial('A', new int[][]{ {0,1},{2,2},{2,2},{3,2} } );
        arr.setPolynomial('B', new int[][]{ {0,1},{-1,10} });
        arr.multiply('A','B');
        assertEquals(arr.print('R').toString(), "-7x^12");

        arr.setPolynomial('C', new int[][]{ {5,-3}, {2,2}, {2,2} } );
        arr.setPolynomial('B', new int[][]{ {1,-2}, {3,4} , {3,2} });
        arr.add('C','B');
        assertEquals(arr.print('R').toString(), "5x^-3 + 1x^-2 + 7x^2 + 3x^4");

        arr.subtract('C','B');
        assertEquals(arr.print('R').toString(), "5x^-3 + -1x^-2 + 1x^2 + -3x^4");


        // clear and print
        arr.clearPolynomial('R');
        assertThrows(RuntimeException.class, () -> {
            arr.print('R');
        });





    }
}