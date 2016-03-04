#include <string>
#include <iostream>
#include <unordered_map>

void swap(int numbers[][3], int i, int j)
{
    int temp[3] = { numbers[i][0], numbers[i][1], numbers[i][2] };
    numbers[i][0] = numbers[j][0];
    numbers[i][1] = numbers[j][1];
    numbers[i][2] = numbers[j][2];
    numbers[j][0] = temp[0];
    numbers[j][1] = temp[1];
    numbers[j][2] = temp[2];
}
long long convertTime(int time[3]){
    return time[0] * 60 * 100 + time[1] * 100 + time[2];
}
void bubbleSort(int numbers[][3], int count)
{
   int i, j;
   for (i = 0; i < count - 1; i++)
       // избутва най-тежките накрая.
       for (j = 0; j < count - i - 1; j++)
           if (convertTime(numbers[j]) > convertTime(numbers[j + 1]))
              swap(numbers, j, j + 1);
    return;
}
void insertionSort(int numbers[][3], int count)
{
   int i, j, key3[3];
   long long key;
   for (i = 1; i < count; i++)
   {
        key3[0] = numbers[i][0];
        key3[1] = numbers[i][1];
        key3[2] = numbers[i][2];
        key = convertTime(numbers[i]);
        j = i-1;

        while (j >= 0 && convertTime(numbers[j]) > key)
        {
           numbers[j+1][0] = numbers[j][0];
           numbers[j+1][1] = numbers[j][1];
           numbers[j+1][2] = numbers[j][2];
           j = j-1;
        }
        numbers[j+1][0] = key3[0];
        numbers[j+1][1] = key3[1];
        numbers[j+1][2] = key3[2];
   }
}


void printArr(char arr[][21], int count)
{
    for(int i = 0; i < count; i++)
    {
        std :: cout << "***" << arr[i] << "\n";
    }
}
void printArr(int arr[][3], int count)
{
    for(int i = 0; i < count; i++)
    {
        std :: cout << "***" << arr[i][0] << " " << arr[i][1] << " " << arr[i][2] << "\n";
    }
}
int findPlace(int times[][3], int time[3], int count)
{
    for(int i = 0; i < count; i++)
        if(times[i][0] == time[0] && times[i][1] == time[1] && times[i][2] == time[2])
            return i + 1;
}
int main() {
    int count, place, currentCount = 0;
    std :: cin >> count;
    std::unordered_map<std::string, int> namesPlaces;
    int times[count][3];
    char names[count][21];
    char string[21];
    for(int i = 0; i < count; i++)
    {
        std :: cin >> names[i];
        scanf(" %d:%d.%d", &times[i][0], &times[i][1], &times[i][2]);
        int time[3] = {times[i][0], times[i][1], times[i][2]};
        insertionSort(times, ++currentCount);
        namesPlaces.insert (std::make_pair<std::string, int>(names[i], findPlace(times, time, currentCount)));
    }
    for(int i = 0; i < count; i++)
        std :: cout << names[i] << " " << namesPlaces[names[i]] << "\n";
}
