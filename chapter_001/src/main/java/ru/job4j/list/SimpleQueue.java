package ru.job4j.list;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int index = 0;

    //    Метод poll() - должен возвращать первое значение и удалять его из коллекции.
    public T poll() {
        while (index > 0) {
            out.push(in.pop());
            index--;
        }
        return out.pop();
    }

    //    Метод push(T value) - помещает значение в конец.
    public void push(T value) {
        in.push(value);
        index++;
    }
}