/**
 * performance test to compare heap select with traditional sort to select the top k elements of an unordered list
 * run with 'mvn compile groovy:execute'
 * the performance depends on the generated numbers, so let it run multiple times to see the distribution
 **/
println "JVM version " + System.getProperty("java.version")

/* some setup */
def numberCount = 10*1000*1000
def k=5
def random = new Random()
def unorderedList
def start
def topElements 
def executionTime 
def comparator
def timeit = {String message, Closure cl->
    def startTime = System.currentTimeMillis()
    cl()
    def deltaTime = System.currentTimeMillis() - startTime
    println "$message: \ttime: $deltaTime"
}


/* here come the tests */
unorderedList = (1..numberCount).collect{random.nextInt()}
arrayList =  com.google.common.collect.Lists.newArrayList((int[])unorderedList)
timeit("Guava Ordering") {
    topElements = com.google.common.collect.Ordering.natural()
        .greatestOf(arrayList, k)
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("QuickSelect") {
    topElements = (numberCount-1..numberCount-k).collect{com.michaelpollmeier.selection.QuickSelect.select((int[])unorderedList, it)}
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("heapSelect natural") {
    topElements = new com.michaelpollmeier.selection.HeapSelection().heapSelectNatural(unorderedList, k)
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("heapSelect") {
    topElements = new com.michaelpollmeier.selection.HeapSelection().heapSelect(unorderedList, k)
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("PriorityQueue") {
    heap = new PriorityQueue(unorderedList.size())
    heap.addAll(unorderedList)
    topElements = (1..k).collect{heap.poll()}
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("PriorityQueue with groovy closure comparator") {
    comparator= [ compare: { a,b ->
                return b <=> a
            }] as Comparator
    heap = new PriorityQueue(unorderedList.size(), comparator)
    heap.addAll(unorderedList)
    topElements = (1..k).collect{heap.poll()}
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("PriorityQueue with MyComparator") {
    comparator = new com.michaelpollmeier.selection.MyComparator()
    heap = new PriorityQueue(unorderedList.size(), comparator)
    heap.addAll(unorderedList)
    topElements = (1..k).collect{heap.poll()}
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("PriorityQueue with MyComparator2") {
    comparator = new com.michaelpollmeier.selection.MyComparator2()
    heap = new PriorityQueue(unorderedList.size(), comparator)
    heap.addAll(unorderedList)
    topElements = (1..k).collect{heap.poll()}
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
public class MyComparator3 implements Comparator{
    int compare(o1, o2) {
       return o2.compareTo(o1);
    }
}
timeit("PriorityQueue with MyComparator3") {
    comparator = new MyComparator3()
    heap = new PriorityQueue(unorderedList.size(), comparator)
    heap.addAll(unorderedList)
    topElements = (1..k).collect{heap.poll()}
    print "Top $k elements: $topElements \t"
}

unorderedList = (1..numberCount).collect{random.nextInt()}
timeit("sort") {
    topElements = unorderedList.sort()[numberCount-1..numberCount-k]
    print "Top $k elements: $topElements \t"
}
