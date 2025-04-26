package eci.arcn.library.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixedSizeList<T> {
    private final int maxSize;
    private final List<T> items;

    public FixedSizeList(int maxSize) {
        if (maxSize <= 0) throw new IllegalArgumentException("Size must be greater than 0");
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }

    public boolean add(T item) {
        if (items.size() >= maxSize) return false;
        return items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public List<T> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int size() {
        return items.size();
    }

    public boolean isFull() {
        return items.size() == maxSize;
    }

    public boolean contains(T item) {
        return items.contains(item);
    }
}
