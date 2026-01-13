package khan366kos.excel.handler

/**
 * Immutable, functional bubble sort implementation following Elegant Objects principles
 */

/**
 * Interface defining the contract for array sorting operations
 */
interface ArraySorter<T> {
    fun sort(): Array<T>
}

/**
 * Value object representing a sorted array that encapsulates the sorted elements
 */
data class SortedArray<T>(
    private val elements: Array<T>
) {
    fun get(): Array<T> = elements.copyOf()
    
    override fun toString() = elements.contentToString()
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return elements.contentEquals((other as SortedArray<T>).elements)
    }
    
    override fun hashCode(): Int = elements.contentHashCode()
}

/**
 * Functional bubble sort implementation that encapsulates the sorting algorithm
 */
class FunctionalBubbleSort<T : Comparable<T>>(
    private val array: Array<T>
) : ArraySorter<T> {
    
    init {
        require(array.isNotEmpty()) { "Array cannot be empty for sorting" }
    }
    
    override fun sort(): Array<T> = 
        array.copyOf().apply { bubbleSortInPlace(this) }
    
    private fun bubbleSortInPlace(arr: Array<T>) {
        val n = arr.size
        for (i in 0 until n - 1) {
            var swapped = false
            for (j in 0 until n - i - 1) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    arr.swap(j, j + 1)
                    swapped = true
                }
            }
            if (!swapped) break
        }
    }
    
    private fun Array<T>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}

/**
 * Specialized sorter for integer arrays with additional validation
 */
class IntArraySorter(
    private val original: IntArray
) {
    fun sort(): IntArray = 
        if (original.isEmpty()) original.copyOf()
        else original.copyOf().apply { bubbleSortInPlace(this) }
    
    private fun bubbleSortInPlace(arr: IntArray) {
        val n = arr.size
        for (i in 0 until n - 1) {
            var swapped = false
            for (j in 0 until n - i - 1) {
                if (arr[j] > arr[j + 1]) {
                    // Kotlin idiom for swapping
                    arr[j] = arr[j + 1].also { arr[j + 1] = arr[j] }
                    swapped = true
                }
            }
            if (!swapped) break
        }
    }
}

/**
 * Generic sorter that works with any comparable type
 */
class GenericArraySorter<T : Comparable<T>>(
    private val original: Array<T>
) : ArraySorter<T> {
    
    override fun sort(): Array<T> = FunctionalBubbleSort(original).sort()
}

/**
 * More efficient alternative implementations
 */

/**
 * A merge sort implementation as a more efficient alternative to bubble sort
 */
class MergeSort<T : Comparable<T>>(
    private val array: Array<T>
) : ArraySorter<T> {
    
    override fun sort(): Array<T> = 
        if (array.size <= 1) array.copyOf()
        else mergeSort(array.copyOf())
    
    private fun mergeSort(arr: Array<T>): Array<T> {
        if (arr.size <= 1) return arr
        
        val mid = arr.size / 2
        val left = arr.sliceArray(0 until mid)
        val right = arr.sliceArray(mid until arr.size)
        
        return merge(
            mergeSort(left),
            mergeSort(right)
        )
    }
    
    private fun merge(left: Array<T>, right: Array<T>): Array<T> {
        val result = arrayOfNulls<T>(left.size + right.size) as Array<T>
        var i = 0
        var j = 0
        var k = 0
        
        while (i < left.size && j < right.size) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++]
            } else {
                result[k++] = right[j++]
            }
        }
        
        while (i < left.size) result[k++] = left[i++]
        while (j < right.size) result[k++] = right[j++]
        
        return result
    }
}

/**
 * Example usage demonstrating the improved implementations
 */
fun main() {
    // Example with integer array using the new implementation
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original int array: ${numbers.joinToString(", ")}")
    
    val intSorter = IntArraySorter(numbers)
    val sortedNumbers = intSorter.sort()
    println("Sorted int array: ${sortedNumbers.joinToString(", ")}")
    
    // Example with string array using generic implementation
    val strings = arrayOf("banana", "apple", "cherry", "date")
    println("\nOriginal string array: ${strings.joinToString(", ")}")
    
    val stringSorter = GenericArraySorter(strings)
    val sortedStrings = stringSorter.sort()
    println("Sorted string array: ${sortedStrings.joinToString(", ")}")
    
    // Example using merge sort for better efficiency
    val mergeSorter = MergeSort(strings)
    val mergeSortedStrings = mergeSorter.sort()
    println("Merge sorted string array: ${mergeSortedStrings.joinToString(", ")}")
}