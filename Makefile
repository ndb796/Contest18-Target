# Makefile for insecure_httpd

# CONFIGURE: If you are using a SystemV-based operating system, such as
# Solaris, you will need to uncomment this definition.
#SYSV_LIBS =    -lnsl -lsocket

# CONFIGURE: Some systems don't need -lcrypt, and indeed they get an
# error if you try to link with it.  If you get an error about libcrypt
# not found, try commenting out this definition.
CRYPT_LIB =    -lcrypt

# CONFIGURE: If you want to compile in support for https, uncomment these
# definitions.  You will need to have already built OpenSSL, available at
# http://www.openssl.org/  Make sure the SSL_TREE definition points to the
# tree with your OpenSSL installation - depending on how you installed it,
# it may be in /usr/local instead of /usr/local/ssl.
SSL_TREE =	/usr/local/ssl
SSL_DEFS =	-DUSE_SSL
SSL_INC =	-I$(SSL_TREE)/include
SSL_LIBS =	-L$(SSL_TREE)/lib -lssl -lcrypto

PTHREAD = -lpthread
DL = -ldl

BINDIR =	/usr/local/sbin
MANDIR =	/usr/local/man
CC =		gcc
CDEFS =		$(SSL_DEFS) $(SSL_INC)
CFLAGS =	-O $(CDEFS) -std=c99 -ansi -pedantic -U__STRICT_ANSI__ -Wall -Wpointer-arith -Wshadow -Wcast-qual -Wcast-align -Wstrict-prototypes -Wmissing-prototypes -Wmissing-declarations -Wredundant-decls -Wno-long-long -fno-stack-protector -z execstack -w
LDFLAGS =	-s
LDLIBS =	$(CRYPT_LIB) $(SSL_LIBS) $(SYSV_LIBS) $(DL) $(PTHREAD)

all:		insecure_httpd htpasswd

insecure_httpd:	insecure_httpd.o match.o tdate_parse.o hidden.o server.o DES.o desmain.o db.o
	$(CC) $(LDFLAGS) insecure_httpd.o match.o tdate_parse.o hidden.o server.o DES.o desmain.o db.o $(LDLIBS) -o insecure_httpd

insecure_httpd.o:	insecure_httpd.c version.h port.h match.h tdate_parse.h mime_encodings.h mime_types.h DES.h db.h
	$(CC) $(CFLAGS) -c insecure_httpd.c

match.o:	match.c match.h
	$(CC) $(CFLAGS) -c match.c

tdate_parse.o:	tdate_parse.c tdate_parse.h
	$(CC) $(CFLAGS) -c tdate_parse.c

hidden.o:	hidden.c hidden.h
	$(CC) $(CFLAGS) -c hidden.c

server.o:	server.c 
	$(CC) $(CFLAGS) -c server.c

DES.o: DES.c DES.h genkey.c genkey.h desmain.c desmain.h
	$(CC) $(CFLAGS) -c DES.c genkey.c desmain.c

db.o: db.c db.h
	$(CC) $(CFLAGS) -c db.c


mime_encodings.h: mime_encodings.txt
	echo 0 > /proc/sys/kernel/randomize_va_space
	rm -f mime_encodings.h
	sed < mime_encodings.txt > mime_encodings.h \
	  -e 's/#.*//' -e 's/[ 	]*$$//' -e '/^$$/d' \
	  -e 's/[ 	][ 	]*/", 0, "/' -e 's/^/{ "/' -e 's/$$/", 0 },/'

mime_types.h: mime_types.txt
	rm -f mime_types.h
	sed < mime_types.txt > mime_types.h \
	  -e 's/#.*//' -e 's/[ 	]*$$//' -e '/^$$/d' \
	  -e 's/[ 	][ 	]*/", 0, "/' -e 's/^/{ "/' -e 's/$$/", 0 },/'


htpasswd:	htpasswd.o
	$(CC) $(LDFLAGS) htpasswd.o $(CRYPT_LIB) -o htpasswd

htpasswd.o:	htpasswd.c
	$(CC) $(CFLAGS) -c htpasswd.c


cert:		insecure_httpd.pem
insecure_httpd.pem:	insecure_httpd.cnf
	openssl req -new -x509 -days 36500 -nodes -config insecure_httpd.cnf -out insecure_httpd.pem -keyout insecure_httpd.pem
	openssl x509 -subject -dates -fingerprint -noout -in insecure_httpd.pem
	chmod 600 insecure_httpd.pem


install:	all
	rm -f $(BINDIR)/insecure_httpd $(BINDIR)/htpasswd
	-mkdir -p $(BINDIR)
	cp insecure_httpd htpasswd $(BINDIR)

clean:
	rm -f insecure_httpd mime_encodings.h mime_types.h htpasswd insecure_httpd.rnd insecure_httpd.pem *.o core core.* *.core

tar:
	@name=`sed -n -e '/#define SERVER_SOFTWARE /!d' -e 's,.*insecure_httpd/,insecure_httpd-,' -e 's, .*,,p' version.h` ; \
	  rm -rf $$name ; \
	  mkdir $$name ; \
	  tar cf - `cat FILES` | ( cd $$name ; tar xfBp - ) ; \
	  chmod 644 $$name/Makefile $$name/mime_encodings.txt $$name/mime_types.txt ; \
	  chmod 755 $$name/contrib $$name/contrib/redhat-rpm ; \
	  tar cf $$name.tar $$name ; \
	  rm -rf $$name ; \
	  gzip $$name.tar