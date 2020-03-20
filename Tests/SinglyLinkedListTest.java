package eg.edu.alexu.csd.datastructure.linkedList.Tests;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.SinglyLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static groovy.test.GroovyTestCase.assertEquals;

class SinglyLinkedListTest {


    @Test
    void test() {

        ArrayList<Integer> originalArr = new ArrayList<Integer>();
        SinglyLinkedList newArr = new SinglyLinkedList();

        for(int i=0; i<10; i++){
            originalArr.add(i+1);
            newArr.add(i+1);
        }

        assertArrays(originalArr,newArr);
        //for(int)

        /** test for boundaries **/
        originalArr.add(0, -152 );
        originalArr.add(originalArr.size(), 100 );

        newArr.add(0, -152 );
        newArr.add(newArr.size(),100);

        newArr.add(1000, -152 );

        assertArrays(originalArr,newArr);

        /** set function **/
        newArr.set(newArr.size()-1, 123);
        originalArr.set(originalArr.size()-1,123);
        newArr.set(-10, -1); // out of bounds
        newArr.set(1, -1);
        originalArr.set(1,-1);
        newArr.set(0, -2);
        originalArr.set(0,-2);

        assertArrays(originalArr,newArr);


        /** clear and test insert funtction **/
        originalArr.clear();

        assertEquals(newArr.isEmpty(),false);
        newArr.clear();
        assertEquals(newArr.isEmpty(),true);

        assertArrays(originalArr,newArr);

        for(int i=0; i<10; i++){
            originalArr.add(i+1);
            newArr.add(i,i+1);
        }

        assertArrays(originalArr,newArr);

        assertEquals(newArr.contains(5),originalArr.contains(5));
        newArr.remove(4); originalArr.remove(4);
        assertEquals(newArr.contains(5),false);
        newArr.remove(0); originalArr.remove(0);
        newArr.remove(newArr.size()-1); originalArr.remove(originalArr.size()-1);
        newArr.remove(-1);
        assertEquals(newArr.contains(5),false);

        ILinkedList subList = newArr.sublist(0,1);
        assertEquals(newArr.get(1),subList.get(1));
        assertEquals(newArr.get(0),subList.get(0));

        assertEquals(newArr.sublist(-1,1),null);

    }


    @Test
    void insertionAtTheBeginningTest(){
        ArrayList<Integer> originalArr = new ArrayList<Integer>();
        SinglyLinkedList newArr = new SinglyLinkedList();


        // test1
        originalArr.add(0, -152 );
        originalArr.add(originalArr.size(), 100 );
        originalArr.add(0, -151 );

        newArr.add(0, -152 );
        newArr.add(newArr.size(),100);
        newArr.add(0, -151 );

        assertArrays(originalArr,newArr);

    }

    void assertArrays( ArrayList<Integer> originalArr, SinglyLinkedList newArr){
        assertEquals(originalArr.size(),newArr.size());
        for(int i=0; i<originalArr.size(); i++){
            assertEquals(originalArr.get(i),newArr.get(i));
        }
    }


}