package org.jeongmo.heap;

import org.jeongmo.sorting.UtilForSort;

public class Heap {

    Integer[] data;
    int count;

    public Heap(int size) {
        data = new Integer[size + 1];
        data[0] = null;
        count = 0;
    }

    private void bottomUp(int index) {
        if (index == 1) {
            return;
        }

        if (data[index] > data[index / 2] ) {
            UtilForSort.swap(data, index, index / 2);
            bottomUp(index / 2);
        }
    }

    private void topDown(int index) {
        if (2 * index > count) {
            return;
        }
        else if (2 * index == count && data[index] < data[2 * index]) {
            UtilForSort.swap(data, index, 2 * index);
            return;
        }

        if (data[2 * index] > data[2 * index + 1] && data[2 * index] > data[index]) {
            UtilForSort.swap(data, index, 2 * index);
            topDown(2 * index);
        }
        else if (data[2 * index] < data[2 * index + 1] && data[2 * index + 1] > data[index]) {
            UtilForSort.swap(data, index, 2 * index + 1);
            topDown(2 * index + 1);
        }
    }

    public void addElement(Integer element) {
        data[++count] = element;
        bottomUp(count);
    }

    public Integer pop() {
        Integer temp = data[1];
        data[1] = data[count];
        topDown(1);
        return temp;
    }

    public void heapSort() {
        Integer[] temp = new Integer[count + 1];
        for (int i = 1; i < count + 1; i++) {
            temp[i] = this.pop();
        }

        for (int i = 1; i < count + 1; i++) {
            this.addElement(temp[i]);
        }
    }
}
