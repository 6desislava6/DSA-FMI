#include <iostream>
#include <cmath>
using namespace std;

int calcGrade(long beginning, long ending)
{
    long result = 0;
    if (beginning % 2 == 0)
    {
        result -= beginning;
        beginning++;
    }
    if (ending % 2 == 1)
    {
        result += ending;
        ending--;
    }
    result -= (ending - beginning + 1)/2;
    result = (int) fabs(result) % 5 + 2;
    return result;
}

int main()
{
    int count;
    cin >> count;
    int students[count][2];
    for (int i = 0; i < count; i++)
        cin >> students[i][0] >> students[i][1];

    for (int i = 0; i < count; i++)
        cout << calcGrade(students[i][0], students[i][1]) << "\n";

}
