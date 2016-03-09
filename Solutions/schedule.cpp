#include <iostream>
#include <algorithm>
void merge(int** numbers, int left, int right, int mid, int index, bool three)
{
    int n1 = mid - left + 1, n2 = right - mid;
    int indexes = three ? 3 : 2;
    int** firstArr = new int*[n1];
    int** secondArr = new int*[n2];
    for(int i = 0; i < n1; i++)
    {
        firstArr[i] = new int[indexes];
        firstArr[i][0] = numbers[left + i][0];
        firstArr[i][1] = numbers[left + i][1];
        if (three)
            firstArr[i][2] = numbers[left + i][2];
    }

    for(int i = 0; i < n2; i++)
    {
        secondArr[i] = new int[indexes];
        secondArr[i][0] = numbers[mid + 1 + i][0];
        secondArr[i][1] = numbers[mid + 1 + i][1];
        if (three)
            secondArr[i][2] = numbers[mid + 1 + i][2];
    }

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2)
    {
        if(firstArr[i][index] <= secondArr[j][index])
        {
            numbers[k][0] = firstArr[i][0];
            numbers[k][1] = firstArr[i][1];
            if(three)
                numbers[k][2] = firstArr[i][2];
            ++i;
            ++k;
        }

        else
        {
            numbers[k][0] = secondArr[j][0];
            numbers[k][1] = secondArr[j][1];
            if(three)
                numbers[k][2] = secondArr[j][2];
            ++j;
            ++k;
        }
    }

    while (i < n1)
    {
        numbers[k][0] = firstArr[i][0];
        numbers[k][1] = firstArr[i][1];
         if(three)
            numbers[k][2] = firstArr[i][2];
        ++i;
        ++k;
    }

    while (j < n2)
    {
        numbers[k][0] = secondArr[j][0];
        numbers[k][1] = secondArr[j][1];
        if(three)
            numbers[k][2] = secondArr[j][2];
        ++j;
        ++k;

    }
}

void mergeSort(int** numbers, int left, int right, int index, bool three=false)
{

    if (right <= left)
        return;
    int mid = left +(right - left) / 2;
    mergeSort(numbers, left, mid, index, three);
    mergeSort(numbers, mid + 1, right, index, three);
    merge(numbers, left, right, mid, index, three);
}
void printArr(int** arr, int count)
{
    for(int i = 0; i < count; i++)
    {
        std :: cout << "***" << arr[i][0] << " " << arr[i][1] << " " << arr[i][2] << "\n";
    }
}
int makeSmallest(int** events, int** newEvents, int count)
{
    int counter = 0, lastNumber = -1;
    for(int i = 0; i < count; i++)
    {
        if(lastNumber == events[i][0])
            continue;
        newEvents[counter] = new int[3];
        newEvents[counter][0] = events[i][0];
        newEvents[counter][1] = events[i][1];
        newEvents[counter][2] = events[i][0] + events[i][1];
        lastNumber = newEvents[counter][0];
        counter++;
    }
    return counter;

}
int getMid(int left, int right)
{
    return left + (right - left) / 2;
}
int binarySearch(int** arr, int count, int value)
{
    int left = 0, right = count;
    int mid = getMid(left, right);
    while(true)
    {
        if (right - left <= 1)
            return right;
        if (arr[mid][0] == value)
            return mid;
        if (arr[mid][0] > value)
            right = mid - 1;
        else
            left = mid + 1;
        mid = getMid(left, right);
    }

    // А ако го няма?
}
int search(int** events, int count, int value)
{
    for(int i = 0; i < count; i++)
        if(events[i][0] == value)
            return i;

    return count - 1;
}
void countEvents(int** events, int count, int beginIndex, int currentCount, int& maxN)
{
    if(count == 0)
        return;

    int endHour = events[beginIndex][2];
    int index = search(events, count, endHour);

    for(int i = beginIndex + 1; i < index; i++)
    {
        countEvents(events, count, i, currentCount, maxN);
    }
    endHour = events[index][2];
    if (endHour > events[count - 1][0])
        return;

    maxN = std :: max(currentCount + 1, maxN);
    countEvents(events, count, index, currentCount + 1, maxN);
}
int main()
{
    //int* bestSchedule = new int[1<<20];
    int** events = new int*[1<<20];
    int counter = 0;
    while(std :: cin)
    {
        events[counter] = new int[2];
        std :: cin >> events[counter][0] >> events[counter][1];
        counter++;
    }
    counter--;

    mergeSort(events, 0, counter - 1, 1);
    mergeSort(events, 0, counter - 1, 0);
    int** newEvents = new int*[counter];
    int newCounter = makeSmallest(events, newEvents, counter);
    mergeSort(newEvents, 0, newCounter - 1, 2, true);
    // begin duration end
    //std :: cout << binarySearch(newEvents, newCounter, 5);
    printArr(newEvents, newCounter);
    int max = 0;
    countEvents(newEvents, newCounter, 0, 0, max);
    std :: cout << max;
}
