#include <stdbool.h>
#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>

#include <getopt.h> 

#include "DES.h"

static FILE * output = NULL;

static void desusage(int status)
{
    if(status == EXIT_SUCCESS)
    {
        fprintf(stdout,"Usage: desbox [OPTION] -k=KEY FILE\n"
                "Encrypt or Descrypt with DES.\n\n"
                " -k, --key=KEY     required 64bits key\n"
                " -d, --decrypt     decrypt DES from input file\n"
                " -e, --encrypt     encrypt DES from input file\n"
                " -o, --output=FILE write result to FILE\n"
                " -h, --help        display this help\n");
    }
    else
    {
        fprintf(stderr, "Try 'desmain --help' for more information.\n");
    }
    exit(status);
}

int desmain()
{
    uint64_t key = 0;
    bool encrypt = true;
    FILE * input = NULL;

    /* real key */
    char optarg[64] = { '0', '1', '1', '1', '1', '1', '1', '1', 
                        '1', '1', '1', '1', '1', '1', '1', '1', 
                        '1', '1', '1', '1', '1', '1', '1', '1', 
                        '1', '1', '1', '1', '1', '1', '1', '1', 
                        '1', '1', '1', '1', '1', '1', '1', '1', 
                        '1', '1', '1', '1', '1', '1', '1', '1', 
                        '1', '1', '1', '1', '1', '1', '1', '1', 
                        '1', '1', '1', '1', '1', '1', '1', '1' };
 
    int ii;
    for(ii = 0; optarg[ii] != '\0'; ii++)
    {
        if(ii > 63)
        {
            printf("Error: key is longer than 64bits \n");
            return 0;
        }
        if(optarg[ii] == '1')
        {
            key += (FIRSTBIT >> ii);
        }
    }
    
    /* Check if we can open the input file */
    input = fopen("DESinput.txt", "rb");

    if(input == NULL)
    {
        fprintf(stderr, "Error: can't open input file\n");
        return 0;
    }

    output = fopen("DESoutput.txt", "w");
    if(output == NULL)
    {
        fprintf(stderr, "Error: don't have permission to write output file");
        return 0;
    }

    /* 1. Verify parity bits of the key */
    
    if(!key_parity_verify(key))
    {
        printf("The key you used is malformated\n"); /* More error msg in function */
	printf( "key value: %llx \n", key );
    }
    
    /* 2. Get the 16 subkeys */

    uint64_t a_key[16];
    a_key[0] = key;
    uint64_t next_key;

    for(ii = 0; ii < 16; ii++)
    {
        key_schedule(&a_key[ii], &next_key, ii);
        if(ii != 15)
            a_key[ii + 1] = next_key;
    }

    /* 3. 16 Rounds of enc/decryption */

    size_t amount; /* Used for fwrite */
    uint64_t data;

    while((amount = fread(&data, 1, 8, input)) > 0)
    {
        if(amount != 8)
            data = data << (8 * (8 - amount));

        /* Initial permutation */
        Permutation(&data, true);

        /* Encrypt rounds */
        if(encrypt)
        {
            for(ii = 0; ii < 16; ii++)
                rounds(&data, a_key[ii]);
        }
        /* Decrypt rounds */
        else
        {
            /* Switching blocks */
            data = (data << 32) + (data >> 32);

            for(ii = 15; ii >= 0; ii--)
                rounds(&data, a_key[ii]);
            
            /* Switching blocks back */
            data = (data << 32) + (data >> 32);
        }

        /* Final permutation */
        Permutation(&data, false);

        if(amount != 8)
            data = data << (8 * (8 - amount));


        /* Write output */
        fwrite(&data, 1, amount, output);
        data = 0;
    }

    fclose(input);
    fclose(output);

    return 1;
}
