package com.michaelpollmeier.selection;

import com.google.common.collect.Ordering;
import java.util.ArrayList;

/**
 * 
 * from http://www.brilliantsheep.com/java-implementation-of-hoares-selection-algorithm-quickselect/
 */
public class QuickSelect {
 
    /** Helper method for select( int[ ], int, int, int ) */
    public static int select( int[ ] array, int k ) {
        return select( array, 0, array.length - 1, k );
    }
 
    /** 
     * Returns the value of the kth lowest element. 
     * Do note that for nth lowest element, k = n - 1.
     */
    private static int select( int[ ] array, int left, int right, int k ) {
 
        while( true ) {
 
            if( right <= left + 1 ) { 
 
                if( right == left + 1 && array[ right ] < array[ left ] ) {
                    swap( array, left, right );
                }
 
                return array[ k ];
 
            } else {
 
                int middle = ( left + right ) >>> 1; 
                swap( array, middle, left + 1 );
 
                if( array[ left ] > array[ right ] ) {
                    swap( array, left, right );
                }
 
                if( array[ left + 1 ] > array[ right ] ) {
                    swap( array, left + 1, right );
                }
 
                if( array[ left ] > array[ left + 1 ] ) {
                    swap( array, left, left + 1 );
                }
 
                int i = left + 1;
                int j = right;
                int pivot = array[ left + 1 ];
 
                while( true ) { 
                    do i++; while( array[ i ] < pivot ); 
                    do j--; while( array[ j ] > pivot ); 
 
                    if( j < i ) {
                        break; 
                    }
 
                    swap( array, i, j );
                } 
 
                array[ left + 1 ] = array[ j ];
                array[ j ] = pivot;
 
                if( j >= k ) {
                    right = j - 1;
                }
 
                if( j <= k ) {
                    left = i;
                }
            }
        }
    }
 
    /** Helper method for swapping array entries */
    private static void swap( int[ ] array, int a, int b ) {
        int temp = array[ a ];
        array[ a ] = array[ b ];
        array[ b ] = temp;
    }
 
    /** Test method */
    public static void main( String[ ] args ) {
 
        int[ ] input = { 407, 766, 237, 761, 832, 1655, 421, 145, 688, 994, 915, 
            953, 910, 838, 201, 571, 842, 307, 43, 79, 473, 182, 421, 7122, 140, 
            8452, 376, 304, 546, 743, 5, 394, 672, 8439, 684, 223, 50, 601, 122, 
            767, 853, 120, 294, 818, 1986, 130, 947, 389, 93, 88, 487, 403, 943, 
            973, 359, 226, 228, 408, 913, 743, 458, 867, 335, 885, 19, 606, 282, 
            655, 470, 599, 975, 984, 693, 734, 827, 878, 840, 618, 336, 638, 37, 
            41, 552, 287, 20, 253, 288, 907, 111, 883, 73, 473, 762, 4, 11, 42 };
 
        System.out.println( "1st lowest = " + select( input, 0 ) );
        System.out.println( "2nd lowest = " + select( input, 1 ) );
        System.out.println( "3rd lowest = " + select( input, 2 ) );
    }
}