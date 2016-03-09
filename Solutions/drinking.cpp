#include <stdio.h>

class Heap
{

public:
    int count;
    int* heap;
    Heap()
    {
        this->heap = new int[1 << 20];
        this->count = 0;
    }

    inline void swap(int first, int second)
    {
        int swap = heap[first];
        heap[first] = heap[second];
        heap[second] = swap;

    }
    inline int getParent(int i)
    {
        return (i - 1) / 2;
    }
    inline int getLeft(int i)
    {
        return 2 * i + 1;
    }
    inline int getRight(int i)
    {
        return 2 * i + 2;
    }
    // ,     max-heap-!
    inline void maxHeapify(int index)
    {
        int largest = index;
        int left = getLeft(index), right = getRight(index);
        if (left < count && heap[left] > heap[largest])
            largest = left;
        if (right < count && heap[right] > heap[largest])
            largest = right;

        if (largest != index)
        {
            this->swap(index, largest);
            maxHeapify(largest);
        }
    }


    inline int removeBiggest()
    {
        int biggest = heap[0];
        count--;
        heap[0] = heap[count];
        maxHeapify(0);
        return biggest;
    }
    void insert(int value)
    {
        int index = count;
        count++;
        while (index > 0 && heap[getParent(index)] < value)
        {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        heap[index] = value;
    }


};

int main()
{
    int number;
    Heap* heap = new Heap();
    char operation = 'A';

    while(operation != 'Q')
    {
        scanf("%c", &operation);
        if (operation == 'A')
        {
            scanf(" %d", &number);
            heap->insert(number);
        } else if (operation == 'R')
        {
            if(heap->count == 0)
            {
                printf("Not available\n");
                continue;
            }
            printf("%d\n", heap->removeBiggest());

        } else if (operation == 'L')
        {
            if(heap->count == 0)
            {
                printf("Not available\n");
                continue;
            }
            printf("%d\n", heap->heap[0]);
        }
        //printHeap(heap, count);
    }
}
