import java.util.Arrays;

class DynamicArray<T> {
    private Object[] data;
    private int size;

    public DynamicArray() {
        this.data = new Object[2];
        this.size = 0;
    }

    public void add(T value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    public void add(int index, T value) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        checkIndex(index);
        T old = (T) data[index];
        data[index] = value;
        return old;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) data[index];
        int moveCount = size - index - 1;
        if (moveCount > 0) {
            System.arraycopy(data, index + 1, data, index, moveCount);
        }
        data[--size] = null; // 移除後最後一個無效格設為 null
        return removed;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = data.length * 2;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        System.out.println("--- Testing String DynamicArray ---");
        DynamicArray<String> strArr = new DynamicArray<>();
        strArr.add("A");
        strArr.add("B");
        System.out.println("Capacity before resize: " + strArr.capacity());
        strArr.add("C"); // 觸發擴容為兩倍 (4)
        System.out.println("Capacity after resize: " + strArr.capacity());
        strArr.add(1, "INSERTED");
        System.out.println("Data: " + strArr);
        strArr.remove(1);
        System.out.println("After remove: " + strArr);

        System.out.println("\n--- Testing Integer DynamicArray & Boundary Cases ---");
        DynamicArray<Integer> intArr = new DynamicArray<>();
        intArr.add(10);
        intArr.add(20);

        // 測試邊界例外: -1, size, 以及空結構刪除
        try { intArr.get(-1); } catch (Exception e) { System.out.println("Caught: " + e.getMessage()); }
        try { intArr.remove(intArr.size()); } catch (Exception e) { System.out.println("Caught: " + e.getMessage()); }

        DynamicArray<Integer> emptyArr = new DynamicArray<>();
        try { emptyArr.remove(0); } catch (Exception e) { System.out.println("Caught Empty Remove: " + e.getMessage()); }
    }
}