package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private transient Node<E> first;
    private transient Node<E> last;
    private int size = 0;
    private int modCount = 0;

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        private Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> latest = last;
        Node<E> newNode = new Node<>(latest, value, null);
        last = newNode;
        if (latest == null) {
            first = newNode;
        } else {
            latest.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> rsl = first;
        int count = 0;
        while (Objects.checkIndex(index, size) != count) {
            rsl = rsl.next;
            count++;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> cursor = first;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = cursor.item;
                cursor = cursor.next;
                return rsl;
            }
        };
    }
}
