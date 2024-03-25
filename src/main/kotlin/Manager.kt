open class Manager<T : Item>(val itemName: String) {
    var items: MutableList<T> = mutableListOf()

    fun createItem(isNote: Boolean) {
        print("Создать $itemName\nВведите название: ")
        val input = readLine().orEmpty().trim()

        if (items.any { it.name == input } or input.isEmpty()) {
            println("$itemName уже существует или вы ничего не ввели.")
            return
        }

        val item = if (isNote) {
            var content: String
            do { print("Введите текст заметки(не пустое содержание): ")
                content = readLine().orEmpty().trim()
            } while (content.isEmpty())
            Note(input, content)
        } else {
            Archive(input)
        }
        items.add(item as T)
        println(items)
    }

    fun delete(item: T) {
        items.remove(item)
        println("Удален(а) '${item.name}'.")
    }

    fun select(): T? {
        if (items.isEmpty()) {
            println("Список пуст.")
            return null
        }
        items.forEachIndexed { index, item -> println("$index. ${item.name}") }

        var input: Int
        do {
            println("Выберите $itemName, необходимо ввести цифру:")
            input = readLine()?.toIntOrNull() ?: -1
        } while (input !in 0 until items.size)

        println("Выбран(а) '${items[input].name}'.")
        return items[input]
    }
}