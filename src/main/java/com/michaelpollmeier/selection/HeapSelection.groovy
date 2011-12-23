package com.michaelpollmeier.selection

/**
 * Implementation of selection algorithm that return the top k elements from a list or map
 * 
 * @author Michael Pollmeier
 * @see http://en.wikipedia.org/wiki/Selection_algorithm#Selecting_k_smallest_or_largest_elements
 * Inspired by http://stevehanov.ca/blog/index.php?id=122
 */
public class HeapSelection {

    def heapSelect(list, k) {
        def comparator= [ compare: { a,b ->
            return a <=> b
        }] as Comparator
        heapSelect(list, comparator, k)
    }
    
    def heapSelect(List list, Comparator comparator, k) {
        def heap = new PriorityQueue(k, comparator)
        list.each{ item ->
            if (heap.size() < k || comparator.compare(item, heap.peek()) == 1) {
                if (heap.size() == k)
                    heap.remove(heap.peek())
                heap.offer(item)
            }
        }
        return heap as List
    }
    
    def heapSelectNatural(List list, k) {
        def heap = new PriorityQueue(k)
        list.each{ item ->
            if (heap.size() < k || item > heap.peek()) {
                if (heap.size() == k)
                    heap.remove(heap.peek())
                heap.offer(item)
            }
        }
        return heap as List
    }
    
}
