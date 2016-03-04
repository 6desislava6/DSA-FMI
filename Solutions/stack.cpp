#include <iostream>
#include <stack>
#include <stdio.h>
#include <cmath>
using namespace std;


int main()
{
    stack <double> numbers;

    cin.exceptions(ios_base::failbit);
    double input;
    try {
        while (cin >> input)
        {
            numbers.push(input);
        }
    } catch(ios_base::failure & bf)
    {
        while(!numbers.empty()){
            printf ("%f\n", sqrt(numbers.top()));
            numbers.pop();
        }
    }
 }
