#ifndef DES_H
#define DES_H

#define FIRSTBIT 0x8000000000000000

void addbit(uint64_t *block, uint64_t from,
            int position_from, int position_to);

void Permutation(uint64_t* data, bool initial);

bool key_parity_verify(uint64_t key);

void key_schedule(uint64_t* key, uint64_t* next_key, int round);

void rounds(uint64_t *data, uint64_t key);

#endif
