import java.util.*

class GenericStack<T> {
    private val map = Stack<T>()
    fun push(item: T) {
        map.push(item)
    }

    fun pop(): T? = if (isEmpty()) null else map.pop()

    fun isEmpty(): Boolean = map.isEmpty()
}