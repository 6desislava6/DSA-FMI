#include <iostream>
#include <math.h>

unsigned long long merge(int numbers[], int left, int right, int mid)
{
    int n1 = mid - left + 1, n2 = right - mid;

    unsigned long long inversionsCounter = 0;
    int firstArr[n1];
    int secondArr[n2];
    for(int i = 0; i < n1; i++)
        firstArr[i] = numbers[left + i];
    for(int i = 0; i < n2; i++)
        secondArr[i] = numbers[mid + 1 + i];

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2)
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
   while (i < n1)
        numbers[k++] = firstArr[i++];

    while (j < n2)
        numbers[k++] = secondArr[j++];


    return inversionsCounter;
}

unsigned long long mergeSort(int numbers[], int left, int right)
{

    if (right <= left)
        return 0;
    int mid = left +(right - left) / 2;
    unsigned long long inversionsCounter = mergeSort(numbers, left, mid);
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
    int numbers[count];
    for (int i = 0; i < count; i++)
        std :: cin >> numbers[i];

    std :: cout << mergeSort(numbers, 0, count - 1);
    //printArr(numbers, count);

}
