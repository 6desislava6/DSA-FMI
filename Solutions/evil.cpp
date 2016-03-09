#include <stdio.h>
using namespace std;

void fillIn(bool* all, int count)
{
    for (int i = 0; i < count; i++)
    {
        long long first;
        long long second;
        scanf("%lld %lld", &first, &second);
        for (long long j = first - 1; j < second; j++)
        {
            all[j] = true;
        }
    }
}
long long goOver(bool* all, long long n)
{
    long long points = 0;
    for (long long i = 0; i < n; i++)
    {
        if (all[i])
            points++;
    }
    return points;
}
int main()
{
    long long n;
    int count;
    scanf("%lld %d", &n, &count);
    bool* all = new bool[n];
    fillIn(all, count);
    n = goOver(all, n);
    //printf("%ll", n);
    printf("%lld", n);
    return 0;
}
