package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] elements;
    private int size;

    public SafetyList() {
        this.elements = new int[10]; // Изначальная емкость массива
        this.size = 0;
    }

    public synchronized void add(int value) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2); // Увеличиваем размер массива
        }
        elements[size++] = value;
    }

    public synchronized int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    public synchronized int getSize() {
        return size;
    }
    // END
}
