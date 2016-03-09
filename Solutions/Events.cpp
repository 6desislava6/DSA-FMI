
#include <cstdio>
#include <iostream>


int comparison(int* dateFirst, int* dateSecond, int index)
{
    if (dateFirst[index] > dateSecond[index])
        return 1;
    else if (dateFirst[index] < dateSecond[index])
        return -1;
    return 0;
}
bool isBigger(int* dateFirst, int* dateSecond)
{
    int cmp;
    int indexes[] = { 5, 4, 3, 0, 1, 2 };
    for (auto i: indexes)
    {
        cmp = comparison(dateFirst, dateSecond, i);
        if (cmp == 1)
            return true;
        else if (cmp == -1)
            return false;
    }
    return false;
}

void printArr(long long** mapped, int count)
{
    for (int i = 0; i < count; i++)
        std::cout << mapped[i][0] << "    ->  " << mapped[i][1] << "\n";

}

void insertionSortDate(short count, int** dates)
{

    int j;
    for (int i = 1; i < count; i++)
    {
        int* valueDate = new int[7];
        for(int k = 0; k < 7; k++)
            valueDate[k] = dates[i][k];

        j = i - 1;
        while (j >= 0 && isBigger(dates[j], valueDate))
        {
            dates[j + 1] = dates[j];
            --j;
        }
        dates[j + 1] = valueDate;
    }
}


int main()
{
    int countDates;
    std::cin >> countDates;
    int** dates = new int*[countDates];
    for (int i = 0; i < countDates; i++)
    {
        dates[i] = new int[7];
        std::cin >> dates[i][0];
        std::cin.ignore(1);
        std::cin >> dates[i][1];
        std::cin.ignore(1);
        std::cin >> dates[i][2];
        std::cin.ignore(1);
        std::cin >> dates[i][3];
        std::cin.ignore(1);
        std::cin >> dates[i][4];
        std::cin.ignore(1);
        std::cin >> dates[i][5];
        dates[i][6] = i + 1;
    }
    insertionSortDate(countDates, dates);

    for (int i = 0; i < countDates; i++)
         std::cout << dates[i][6] << "\n";


    //system("pause");
}
