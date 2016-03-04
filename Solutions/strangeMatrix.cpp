#include <iostream>

using namespace std;

void fillMatrix(int** matrix, int dimension)
{
    int counter, left, up, right, down;
    counter = left = up = 0;
    right = down = dimension - 1;
    while (counter < dimension * dimension)
    {
        int i = left;
        while(i <= right)
        {
            matrix[up][i] = ++counter;
            i++;
        }
        up++;
        i = up;
        while (i <= down)
        {
            matrix[i][right] = ++counter;
            i++;
        }
        right--;
        i = right;
        while(i >= left)
        {
            matrix[down][i] = ++counter;
            i--;
        }
        down--;
        i = down;
        while(i >= up)
        {
            matrix[i][left] = ++counter;
            i--;
        }
        left++;
    }
}

void printMatrix(int** matrix, int rows, int cols)
{
    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < cols; j++)
        {
            if (j == cols -1)
            {
                cout << matrix[i][j];
                continue;
            }
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}

int main()
{
    int dimension;
    cin >> dimension;
    int** matrix = new int* [dimension];
    for(int i = 0; i < dimension; i++)
    {
        matrix[i] = new int [dimension];
    }
    fillMatrix(matrix, dimension);
    printMatrix(matrix, dimension, dimension);
}

