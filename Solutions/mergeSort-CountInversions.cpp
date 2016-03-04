#include <iostream>
#include <math.h>

int merge(int* numbers, int left, int right, int mid)
{
    int inversionsCounter = 0;
    int* firstArr = new int[mid - left + 1];
    int* secondArr = new int[right - mid];
    for(int i = 0; i < mid - left + 1; i++)
        firstArr[i] = numbers[left + i];
    for(int i = 0; i < right - mid; i++)
        secondArr[i] = numbers[mid + 1 + i];

    int i = 0, j = 0, k = left;
    while (i < mid - left + 1 && j < right - mid)
    {
        if(firstArr[i] <= secondArr[j])
        {
            numbers[k++] = firstArr[i++];
        } else
        {
            numbers[k++] = secondArr[j++];
            inversionsCounter += mid  - (left + i) + 1;
        }

    }
   while (i <  mid - left + 1)
        numbers[k++] = firstArr[i++];

    while (j < right - mid)
        numbers[k++] = secondArr[j++];

    delete[] firstArr;
    delete[] secondArr;
    return inversionsCounter;
}

int mergeSort(int* numbers, int left, int right)
{

    if (right <= left)
        return 0;
    int mid = left +(right - left) / 2;
    int inversionsCounter = mergeSort(numbers, left, mid);
    inversionsCounter += mergeSort(numbers, mid + 1, right);
    inversionsCounter += merge(numbers, left, right, mid);
    return inversionsCounter;
}
void printArr(int* arr, int count)
{
    for(int i = 0; i < count; i++)
    {
        std :: cout << "***" << arr[i] << "\n";
    }
}
int main()
{
    int count;
    std :: cin >> count;
    int* numbers = new int[count];
    for (int i = 0; i < count; i++)
        std :: cin >> numbers[i];

    std :: cout << mergeSort(numbers, 0, count - 1);


}
