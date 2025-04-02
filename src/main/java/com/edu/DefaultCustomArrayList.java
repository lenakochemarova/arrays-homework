package com.edu;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DefaultCustomArrayList<E> implements CustomArrayList<E> {
    private E[] array;
    private int size;
    private int capacity;

    public DefaultCustomArrayList() {
        capacity = 1;
        array = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public boolean add(E element) {
        if (size == capacity) {
            capacity *= 2;
            E[] newArray = (E[]) new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size++] = element;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private void remove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        for (Object el : array) {
            if (el.equals(element))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[currentIndex++];
        }

        @Override
        public void remove() {
            if (currentIndex <= 0) {
                throw new IllegalStateException();
            }
            DefaultCustomArrayList.this.remove(--currentIndex);
        }

    }
}