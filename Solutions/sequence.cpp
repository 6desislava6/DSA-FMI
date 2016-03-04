#include <iostream>
#include <math.h>

int RMQUtil(int *st, int start, int end, int qs, int qe, int index)
{
    if (qs <= start && qe >= end)
        return st[index];

    if (end < qs || start > qe)
        return 1000000;

    int mid = start + (end - start)/2;
    int first = RMQUtil(st, start, mid, qs, qe, 2 * index +1);
    int second = RMQUtil(st, mid + 1, end, qs, qe, 2 * index + 2);
    return first < second ? first : second;
}
int constructSTUtil(int* numbers, int start, int end, int* segmentTree, int index)
{
    if (start == end)
    {
        segmentTree[index] = numbers[start];
        return numbers[start];
    }

    int mid = start + (end - start) / 2;
    //
    int first = constructSTUtil(numbers, start, mid, segmentTree, index * 2 + 1);
    int second = constructSTUtil(numbers, mid + 1, end, segmentTree, index * 2 + 2);
    segmentTree[index] = first < second ? first : second;
    return segmentTree[index];
}
int* constructST(int* numbers, int count)
{
    int height = (int)(ceil(log2(count)));
    int maxSize = 2*(int)pow(2, height) - 1;
    int* segmentTree = new int[maxSize];

    constructSTUtil(numbers, 0, count - 1, segmentTree, 0);
    return segmentTree;
}
int RMQ(int *st, int n, int qs, int qe)
{
    return RMQUtil(st, 0, n-1, qs, qe, 0);
}
int* makeMinInCons(int* numbers, int consecutive, int count)
{
    int* st = constructST(numbers, count);
    delete[] numbers;
    int* minInCons = new int[count - consecutive];
    for(int i = 0; i < count - consecutive; i++)
        minInCons[i] = RMQ(st, count, i, i + consecutive - 1);

    return minInCons;
}
int main()
{
    int count, consecutive, max = 0;
    std :: cin >> count >> consecutive;
    int* numbers = new int[count];

    for(int i = 0; i < count; i++)
        std :: cin >> numbers[i];
    int* minInCons = makeMinInCons(numbers, consecutive, count);
    for(int i = 0; i < count - consecutive; i++)
        max = minInCons[i] > max ? minInCons[i] : max;
    std :: cout << max;

}
