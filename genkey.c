#include <time.h>

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>

#include "DES.h"

void printbits(uint64_t v)
{
    int ii;
    for(ii = 0; ii < 64; ii++)
    {
        if( ((v << ii) & FIRSTBIT) == (uint64_t)0)
            printf("0");
        else
            printf("1");
    }
}

/* Generate a 64bit random DES key
   Add parity bits (last bit of each byte)
   Check for weak keys using key_schedule of DES.c
*/
static void genkey(uint64_t* key)
{
    srand(100); /* srand(time(NULL)); */


    int parity_bit = 0;

    int ii;
    int jj;

    for(ii = 0; ii < 64; ii++) 
    {
        if(ii % 8 == 7)
        {
            if(parity_bit == 1)
                *key += (FIRSTBIT >> ii);
            parity_bit = 0; 
        }
        else
        {
            if(rand() % 2 == 1)
            {
                *key += (FIRSTBIT >> ii);
                parity_bit = parity_bit == 0 ? 1 : 0;
            }
        }
    }

    uint64_t a_key[16];
    a_key[0] = *key;
    uint64_t next_key;

    for(ii = 0; ii < 16; ii++)
    {
        key_schedule(&a_key[ii], &next_key, ii);
        if(ii != 15)
            a_key[ii + 1] = next_key;
    }

    bool weak = false;

    for(ii = 0; ii < 16; ii++)
    {
        for(jj = 0; jj < 16; jj++)
        {
            if(jj != ii)
            {
                if(a_key[ii] == a_key[jj])
                    weak = true;
            }
        }
    }

    if(weak)
    {
        genkey(key);
    }
}

int genkeymain()
{
    uint64_t key = 0;

    genkey(&key);
    
    printf("Here's one key for you: \n");
    printbits(key);
    printf("\n");

    return 1;
}

