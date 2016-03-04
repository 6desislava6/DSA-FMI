#include <iostream>
#include <stack>
#include <stdio.h>
#include <cmath>
using namespace std;

void fillMatrix(int** matrix, int rows, int cols)
{
    for(int i = 0; i < rows; i++)
    {
        matrix[i] = new int [cols];
        for(int j = 0; j < cols; j++)
        {
            cin >> matrix[i][j];
        }
    }
}

void printMatrix(long long** matrix, int rows, int cols)
{
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}

long long** multiplicate (int** matrixFirst, int** matrixSecond, int dimensionFirst, int dimensionSecond, int dimensionThird)
{
    long long** resulting = new long long* [dimensionFirst];
    for(int i = 0; i < dimensionFirst; i++)
    {
        resulting[i] = new long long [dimensionThird];
        for (int j = 0; j < dimensionThird; j++)
        {
            long long sum = 0;
            for(int k = 0; k < dimensionSecond; k++)
            {
                sum += (long long) matrixFirst[i][k] * matrixSecond[k][j];
            }
            resulting[i][j] = sum;
        }
    }
    return resulting;
}
int main()
{
    int dimensionFirst, dimensionSecond, dimensionThird;
    cin >> dimensionFirst >> dimensionSecond >> dimensionThird;
    int** matrixFirst = new int* [dimensionFirst];
    int** matrixSecond = new int* [dimensionSecond];
    fillMatrix(matrixFirst, dimensionFirst, dimensionSecond);
    fillMatrix(matrixSecond, dimensionSecond, dimensionThird);
    printMatrix(multiplicate(matrixFirst, matrixSecond,dimensionFirst, dimensionSecond, dimensionThird), dimensionFirst, dimensionThird);
}
