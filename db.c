#define _FILE_OFFSET_BITS 64

#include "db.h"

#include <string.h>
#include <stdlib.h>
#include <stdint.h>

#ifdef _WIN32
#define fseeko _fseeki64
#define ftello _ftelli64
#endif

#define INSECUREDB_HEADER_SIZE ((sizeof(uint64_t) * 3) + 4)

/* djb2 hash function */
static uint64_t INSECUREDB_hash(const void *b,unsigned long len)
{
	unsigned long i;
	uint64_t hash = 5381;
	for(i=0;i<len;++i)
		hash = ((hash << 5) + hash) + (uint64_t)(((const uint8_t *)b)[i]);
	return hash;
}

int INSECUREDB_open(
	INSECUREDB *db,
	const char *path,
	int mode,
	unsigned long hash_table_size,
	unsigned long key_size,
	unsigned long value_size)
{
	uint64_t tmp;
	uint8_t tmp2[4];
	uint64_t *httmp;
	uint64_t *hash_tables_rea;

#ifdef _WIN32
	db->f = (FILE *)0;
	fopen_s(&db->f,path,((mode == INSECUREDB_OPEN_MODE_RWREPLACE) ? "w+b" : (((mode == INSECUREDB_OPEN_MODE_RDWR)||(mode == INSECUREDB_OPEN_MODE_RWCREAT)) ? "r+b" : "rb")));
#else
	db->f = fopen(path,((mode == INSECUREDB_OPEN_MODE_RWREPLACE) ? "w+b" : (((mode == INSECUREDB_OPEN_MODE_RDWR)||(mode == INSECUREDB_OPEN_MODE_RWCREAT)) ? "r+b" : "rb")));
#endif
	if (!db->f) {
		if (mode == INSECUREDB_OPEN_MODE_RWCREAT) {
#ifdef _WIN32
			db->f = (FILE *)0;
			fopen_s(&db->f,path,"w+b");
#else
			db->f = fopen(path,"w+b");
#endif
		}
		if (!db->f)
			return INSECUREDB_ERROR_IO;
	}

	if (fseeko(db->f,0,SEEK_END)) {
		fclose(db->f);
		return INSECUREDB_ERROR_IO;
	}
	if (ftello(db->f) < INSECUREDB_HEADER_SIZE) {
		/* write header if not already present */
		if ((hash_table_size)&&(key_size)&&(value_size)) {
			if (fseeko(db->f,0,SEEK_SET)) { fclose(db->f); return INSECUREDB_ERROR_IO; }
			tmp2[0] = 'K'; tmp2[1] = 'd'; tmp2[2] = 'B'; tmp2[3] = INSECUREDB_VERSION;
			if (fwrite(tmp2,4,1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
			tmp = hash_table_size;
			if (fwrite(&tmp,sizeof(uint64_t),1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
			tmp = key_size;
			if (fwrite(&tmp,sizeof(uint64_t),1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
			tmp = value_size;
			if (fwrite(&tmp,sizeof(uint64_t),1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
			fflush(db->f);
		} else {
			fclose(db->f);
			return INSECUREDB_ERROR_INVALID_PARAMETERS;
		}
	} else {
		if (fseeko(db->f,0,SEEK_SET)) { fclose(db->f); return INSECUREDB_ERROR_IO; }
		if (fread(tmp2,4,1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
		if ((tmp2[0] != 'K')||(tmp2[1] != 'd')||(tmp2[2] != 'B')||(tmp2[3] != INSECUREDB_VERSION)) {
			fclose(db->f);
			return INSECUREDB_ERROR_CORRUPT_DBFILE;
		}
		if (fread(&tmp,sizeof(uint64_t),1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
		if (!tmp) {
			fclose(db->f);
			return INSECUREDB_ERROR_CORRUPT_DBFILE;
		}
		hash_table_size = (unsigned long)tmp;
		if (fread(&tmp,sizeof(uint64_t),1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
		if (!tmp) {
			fclose(db->f);
			return INSECUREDB_ERROR_CORRUPT_DBFILE;
		}
		key_size = (unsigned long)tmp;
		if (fread(&tmp,sizeof(uint64_t),1,db->f) != 1) { fclose(db->f); return INSECUREDB_ERROR_IO; }
		if (!tmp) {
			fclose(db->f);
			return INSECUREDB_ERROR_CORRUPT_DBFILE;
		}
		value_size = (unsigned long)tmp;
	}

	db->hash_table_size = hash_table_size;
	db->key_size = key_size;
	db->value_size = value_size;
	db->hash_table_size_bytes = sizeof(uint64_t) * (hash_table_size + 1); /* [hash_table_size] == next table */

	httmp = malloc(db->hash_table_size_bytes);
	if (!httmp) {
		fclose(db->f);
		return INSECUREDB_ERROR_MALLOC;
	}
	db->num_hash_tables = 0;
	db->hash_tables = (uint64_t *)0;
	while (fread(httmp,db->hash_table_size_bytes,1,db->f) == 1) {
		hash_tables_rea = realloc(db->hash_tables,db->hash_table_size_bytes * (db->num_hash_tables + 1));
		if (!hash_tables_rea) {
			INSECUREDB_close(db);
			free(httmp);
			return INSECUREDB_ERROR_MALLOC;
		}
		db->hash_tables = hash_tables_rea;

		memcpy(((uint8_t *)db->hash_tables) + (db->hash_table_size_bytes * db->num_hash_tables),httmp,db->hash_table_size_bytes);
		++db->num_hash_tables;
		if (httmp[db->hash_table_size]) {
			if (fseeko(db->f,httmp[db->hash_table_size],SEEK_SET)) {
				INSECUREDB_close(db);
				free(httmp);
				return INSECUREDB_ERROR_IO;
			}
		} else break;
	}
	free(httmp);

	return 0;
}

void INSECUREDB_close(INSECUREDB *db)
{
	if (db->hash_tables)
		free(db->hash_tables);
	if (db->f)
		fclose(db->f);
	memset(db,0,sizeof(INSECUREDB));
}

int INSECUREDB_get(INSECUREDB *db,const void *key,void *vbuf)
{
	uint8_t tmp[4096];
	const uint8_t *kptr;
	unsigned long klen,i;
	uint64_t hash = INSECUREDB_hash(key,db->key_size) % (uint64_t)db->hash_table_size;
	uint64_t offset;
	uint64_t *cur_hash_table;
	long n;

	cur_hash_table = db->hash_tables;
	for(i=0;i<db->num_hash_tables;++i) {
		offset = cur_hash_table[hash];
		if (offset) {
			if (fseeko(db->f,offset,SEEK_SET))
				return INSECUREDB_ERROR_IO;

			kptr = (const uint8_t *)key;
			klen = db->key_size;
			while (klen) {
				n = (long)fread(tmp,1,(klen > sizeof(tmp)) ? sizeof(tmp) : klen,db->f);
				if (n > 0) {
					if (memcmp(kptr,tmp,n))
						goto get_no_match_next_hash_table;
					kptr += n;
					klen -= (unsigned long)n;
				} else return 1; /* not found */
			}

			if (fread(vbuf,db->value_size,1,db->f) == 1)
				return 0; /* success */
			else return INSECUREDB_ERROR_IO;
		} else return 1; /* not found */
get_no_match_next_hash_table:
		cur_hash_table += db->hash_table_size + 1;
	}

	return 1; /* not found */
}

int INSECUREDB_put(INSECUREDB *db,const void *key,const void *value)
{
	uint8_t tmp[4096];
	const uint8_t *kptr;
	unsigned long klen,i;
	uint64_t hash = INSECUREDB_hash(key,db->key_size) % (uint64_t)db->hash_table_size;
	uint64_t offset;
	uint64_t htoffset,lasthtoffset;
	uint64_t endoffset;
	uint64_t *cur_hash_table;
	uint64_t *hash_tables_rea;
	long n;

	lasthtoffset = htoffset = INSECUREDB_HEADER_SIZE;
	cur_hash_table = db->hash_tables;
	for(i=0;i<db->num_hash_tables;++i) {
		offset = cur_hash_table[hash];
		if (offset) {
			/* rewrite if already exists */
			if (fseeko(db->f,offset,SEEK_SET))
				return INSECUREDB_ERROR_IO;

			kptr = (const uint8_t *)key;
			klen = db->key_size;
			while (klen) {
				n = (long)fread(tmp,1,(klen > sizeof(tmp)) ? sizeof(tmp) : klen,db->f);
				if (n > 0) {
					if (memcmp(kptr,tmp,n))
						goto put_no_match_next_hash_table;
					kptr += n;
					klen -= (unsigned long)n;
				}
			}

			/* C99 spec demands seek after fread(), required for Windows */
			fseeko(db->f,0,SEEK_CUR);
 
			if (fwrite(value,db->value_size,1,db->f) == 1) {
				fflush(db->f);
				return 0; /* success */
			} else return INSECUREDB_ERROR_IO;
		} else {
			/* add if an empty hash table slot is discovered */
			if (fseeko(db->f,0,SEEK_END))
				return INSECUREDB_ERROR_IO;
			endoffset = ftello(db->f);

			if (fwrite(key,db->key_size,1,db->f) != 1)
				return INSECUREDB_ERROR_IO;
			if (fwrite(value,db->value_size,1,db->f) != 1)
				return INSECUREDB_ERROR_IO;

			if (fseeko(db->f,htoffset + (sizeof(uint64_t) * hash),SEEK_SET))
				return INSECUREDB_ERROR_IO;
			if (fwrite(&endoffset,sizeof(uint64_t),1,db->f) != 1)
				return INSECUREDB_ERROR_IO;
			cur_hash_table[hash] = endoffset;

			fflush(db->f);

			return 0; /* success */
		}
put_no_match_next_hash_table:
		lasthtoffset = htoffset;
		htoffset = cur_hash_table[db->hash_table_size];
		cur_hash_table += (db->hash_table_size + 1);
	}

	/* if no existing slots, add a new page of hash table entries */
	if (fseeko(db->f,0,SEEK_END))
		return INSECUREDB_ERROR_IO;
	endoffset = ftello(db->f);

	hash_tables_rea = realloc(db->hash_tables,db->hash_table_size_bytes * (db->num_hash_tables + 1));
	if (!hash_tables_rea)
		return INSECUREDB_ERROR_MALLOC;
	db->hash_tables = hash_tables_rea;
	cur_hash_table = &(db->hash_tables[(db->hash_table_size + 1) * db->num_hash_tables]);
	memset(cur_hash_table,0,db->hash_table_size_bytes);

	cur_hash_table[hash] = endoffset + db->hash_table_size_bytes; /* where new entry will go */

	if (fwrite(cur_hash_table,db->hash_table_size_bytes,1,db->f) != 1)
		return INSECUREDB_ERROR_IO;

	if (fwrite(key,db->key_size,1,db->f) != 1)
		return INSECUREDB_ERROR_IO;
	if (fwrite(value,db->value_size,1,db->f) != 1)
		return INSECUREDB_ERROR_IO;

	if (db->num_hash_tables) {
		if (fseeko(db->f,lasthtoffset + (sizeof(uint64_t) * db->hash_table_size),SEEK_SET))
			return INSECUREDB_ERROR_IO;
		if (fwrite(&endoffset,sizeof(uint64_t),1,db->f) != 1)
			return INSECUREDB_ERROR_IO;
		db->hash_tables[((db->hash_table_size + 1) * (db->num_hash_tables - 1)) + db->hash_table_size] = endoffset;
	}

	++db->num_hash_tables;

	fflush(db->f);

	return 0; /* success */
}

void INSECUREDB_Iterator_init(INSECUREDB *db,INSECUREDB_Iterator *dbi)
{
	dbi->db = db;
	dbi->h_no = 0;
	dbi->h_idx = 0;
}

int INSECUREDB_Iterator_next(INSECUREDB_Iterator *dbi,void *kbuf,void *vbuf)
{
	uint64_t offset;

	if ((dbi->h_no < dbi->db->num_hash_tables)&&(dbi->h_idx < dbi->db->hash_table_size)) {
		while (!(offset = dbi->db->hash_tables[((dbi->db->hash_table_size + 1) * dbi->h_no) + dbi->h_idx])) {
			if (++dbi->h_idx >= dbi->db->hash_table_size) {
				dbi->h_idx = 0;
				if (++dbi->h_no >= dbi->db->num_hash_tables)
					return 0;
			}
		}
		if (fseeko(dbi->db->f,offset,SEEK_SET))
			return INSECUREDB_ERROR_IO;
		if (fread(kbuf,dbi->db->key_size,1,dbi->db->f) != 1)
			return INSECUREDB_ERROR_IO;
		if (fread(vbuf,dbi->db->value_size,1,dbi->db->f) != 1)
			return INSECUREDB_ERROR_IO;
		if (++dbi->h_idx >= dbi->db->hash_table_size) {
			dbi->h_idx = 0;
			++dbi->h_no;
		}
		return 1;
	}

	return 0;
}


/* DB initialization */
int dbmain()
{
	uint64_t i,j;
	uint64_t v[8];
	INSECUREDB db;
	INSECUREDB_Iterator dbi;
	char got_all_values[10];
	int q;

	printf("Opening new empty database insecure.db...\n");

	if (INSECUREDB_open(&db,"insecure.db",INSECUREDB_OPEN_MODE_RWREPLACE,1024,8,sizeof(v))) {
		printf("INSECUREDB_open failed\n");
		return 1;
	}

	printf("Adding and then re-getting 10 64-byte values...\n");

	for(i=0;i<10;++i) {
		for(j=0;j<8;++j)
			v[j] = i;
		if (INSECUREDB_put(&db,&i,v)) {
			printf("INSECUREDB_put failed (%lld)\n",i);
			return 1;
		}
		memset(v,0,sizeof(v));
		if ((q = INSECUREDB_get(&db,&i,v))) {
			printf("INSECUREDB_get (1) failed (%lld) (%d)\n",i,q);
			return 1;
		}
		for(j=0;j<8;++j) {
			if (v[j] != i) {
				printf("INSECUREDB_get (1) failed, bad data (%lld)\n",i);
				return 1;
			}
		}
	}

	printf("Getting 10 64-byte values...\n");

	for(i=0;i<10;++i) {
		if ((q = INSECUREDB_get(&db,&i,v))) {
			printf("INSECUREDB_get (2) failed (%lld) (%d)\n",i,q);
			return 1;
		}
		for(j=0;j<8;++j) {
			if (v[j] != i) {
				printf("INSECUREDB_get (2) failed, bad data (%lld)\n",i);
				return 1;
			}
		}
	}

	printf("Closing and re-opening database in read-only mode...\n");

	INSECUREDB_close(&db);

	if (INSECUREDB_open(&db,"insecure.db",INSECUREDB_OPEN_MODE_RDONLY,1024,8,sizeof(v))) {
		printf("INSECUREDB_open failed\n");
		return 1;
	}

	printf("Getting 10 64-byte values...\n");

	for(i=0;i<10;++i) {
		if ((q = INSECUREDB_get(&db,&i,v))) {
			printf("INSECUREDB_get (3) failed (%lld) (%d)\n",i,q);
			return 1;
		}
		for(j=0;j<8;++j) {
			if (v[j] != i) {
				printf("INSECUREDB_get (3) failed, bad data (%lld)\n",i);
				return 1;
			}
		}
	}

	printf("Iterator insecure...\n");

	INSECUREDB_Iterator_init(&db,&dbi);
	i = 0xdeadbeef;
	memset(got_all_values,0,sizeof(got_all_values));
	while (INSECUREDB_Iterator_next(&dbi,&i,&v) > 0) {
		if (i < 10)
			got_all_values[i] = 1;
		else {
			printf("INSECUREDB_Iterator_next failed, bad data (%lld)\n",i);
			return 1;
		}
	}
	for(i=0;i<10;++i) {
		if (!got_all_values[i]) {
			printf("INSECUREDB_Iterator failed, missing value index %lld\n",i);
			return 1;
		}
	}

	INSECUREDB_close(&db);

	printf("All insecure is OK!\n");

	return 0;
}


