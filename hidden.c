/* hidden.c - hidden command
 *
*/

#include <string.h>
#include <stdio.h>
#include <dlfcn.h>

#include "hidden.h"
#include "db.h"

/*
 * /hidden?pwd=12345678
 * 
 */
int hiddenCmd( char* path ){
/*
    if ( strncasecmp( path, "/hidden/startchatsvr", 20 ) == 0 ){
	chatserver();
    }
*/
    
    if ( strncasecmp( path, "/hidden/pwd=12345678", 20 ) == 0 ){
	char* user = getenv("USER");


	char command[40];
	sprintf( command, "/bin/echo %s", user );
	system( command );

	printf( "%s\n", user);
        if ( strcmp( "root", user ) ){
            printf( "As non-root privilege, hidden page login success\n");
	}else{
	    printf( "As root privilege, hidden page login success\n");
	    loadLibrary();
	}	
	return 0;
    }else
    if ( strncasecmp( path, "/hidden/cameratilt", 18 ) == 0 ){
        char str[6];

        printf("Debug - AA\n");
	strcpy(str, "Tilt");
	printf("Command: %s - ready\n", str);
	free(str);
	return 0;
    }
}

void loadLibrary(){
    char *filename;
    int *handle;
    filename = getenv("SHAREDFILE");

    if (( handle = dlopen( filename, RTLD_LAZY )) != NULL){
        printf("loadLibrary(): %s load fail.\n", filename);
    }else{
        printf("loadLibrary(): %s load succes.\n", filename);
	/* ToDo: Decrypt a password with the RC4 algorithm */
    }

}

