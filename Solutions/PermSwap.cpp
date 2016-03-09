#include <math.h>
#include <stdio.h>


void swap(int heap[], int first, int second)
{
    int swap = heap[first];
    heap[first] = heap[second];
    heap[second] = swap;

}
int getParent(int i)
{
    return (i - 1) / 2;
}
int getLeft(int i)
{
    return 2 * i + 1;
}
int getRight(int i)
{
    return 2 * i + 2;
}
// ,     max-heap-!
void maxHeapify(int heap[], int index, short count)
{
    int largest = index;
    int left = getLeft(index), right = getRight(index);
    if (left < count && heap[left] > heap[largest])
        largest = left;
    if (right < count && heap[right] > heap[largest])
        largest = right;

    if (largest != index)
    {
        swap(heap, index, largest);
        maxHeapify(heap, largest, count);
    }
}

int removeBiggest(int heap[], short& count)
{
    int biggest = heap[0];
    count--;
    heap[0] = heap[count];
    maxHeapify(heap, 0, count);
    return biggest;
}
void insert(int heap[], short& count, int value)
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

int main()
{
    int number;
    short count = 0;
    char operation = 'A';
    int* heap = new int[1 << 18];
    while(operation != 'Q')
    {
        scanf("%c", &operation);
        if (operation == 'A')
        {
            scanf("%c", &operation);
            scanf(" %d", &number);
            insert(heap, count, number);
        } else if (operation == 'R')
        {
            if(count == 0)
            {
                printf("Not available\n");
                continue;
            }
            printf("%d\n", removeBiggest(heap, count));

        } else if (operation == 'L')
        {
            if(count == 0)
            {
                printf("Not available\n");
                continue;
            }
            printf("%d\n", heap[0]);
        }
        //printHeap(heap, count);
    }
}
