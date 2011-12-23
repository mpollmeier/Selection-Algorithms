package com.michaelpollmeier.selection
import org.junit.Test

public class HeapSelectionTest {
    def heapSelection = new HeapSelection()
    
    @Test
    public void shouldSelectTopElements() {
        def unorderedList = [3, 2, 1, 5, 4]
        def k=2
        def topElements = heapSelection.heapSelect(unorderedList, k)
        
        assert topElements == [4, 5]
    }
    
    @Test
    public void shouldSelectNonUniqueElements() {
        def unorderedList = [3, 2, 1, 5, 4, 5]
        def k=2
        def topElements = heapSelection.heapSelect(unorderedList, k)
        
        assert topElements == [5, 5]
    }
    
    @Test
    public void shouldDealWithMoreComplexStructure() {
        def unorderedList = [  [5, 'a'], [4, 'b'], [6, 'c'] ]
        def k=2
        def comparator= [ compare: { a,b ->
            return a[0] <=> b[0]
        }] as Comparator
        def topElements = heapSelection.heapSelect(unorderedList, comparator, k)

        assert topElements == [  [5, 'a'], [6, 'c'] ]
    }
    
}
