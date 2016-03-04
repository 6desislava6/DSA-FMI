#include <iostream>
#include <cmath>
using namespace std;

signed long long findNumber(signed long long number)
{
    signed long long result = floor(sqrt(number * 2));
    if (result * (result + 1) / 2 < number)
        result++;
    return result;
}

int main()
{
    int count;
    cin >> count;
    signed long long numbers[count];
    for (int i = 0; i < count; i++)
        cin >> numbers[i];

   for (int i = 0; i < count; i++)
        cout << findNumber(numbers[i]) << "\n";
}
