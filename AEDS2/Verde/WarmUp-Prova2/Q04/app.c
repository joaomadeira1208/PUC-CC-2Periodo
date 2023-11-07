#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int trainingTime;
    int finePerDay;
    int index;
} Dragon;

// Comparator function for sorting dragons
int compare(const void *a, const void *b) {
    Dragon *dragonA = (Dragon *)a;
    Dragon *dragonB = (Dragon *)b;
    return (dragonA->trainingTime * dragonB->finePerDay) - (dragonB->trainingTime * dragonA->finePerDay);
}

int main() {
    int n, i;
    long long totalFine = 0, currentTime = 0;

    scanf("%d", &n); // Read the number of dragons

    Dragon dragons[n];

    for (i = 0; i < n; i++) {
        scanf("%d %d", &dragons[i].trainingTime, &dragons[i].finePerDay);
        dragons[i].index = i;
    }

    // Sort the dragons based on the custom comparator
    qsort(dragons, n, sizeof(Dragon), compare);

    for (i = 0; i < n; i++) {
        currentTime += dragons[i].trainingTime; // Increase current time by training time of the dragon
        totalFine += currentTime * dragons[i].finePerDay; // Add to the total fine
    }

    printf("%lld\n", totalFine); // Print the total fine

    return 0;
}
