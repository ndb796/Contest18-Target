* Virtual Box 우분투에서 동작하며 네트워크 설정은 브릿지로 설정함.

```
# Open SSL 설치 및 환경 설정
apt-get install libcurl4-gnutls-dev libexpat1-dev gettext libz-dev libssl-dev
echo 0 > /proc/sys/kernel/randomize_va_space 
```
* root와 user 유저가 모두 사용되므로 user 계정을 미리 생성함.
* Build는 root 유저로 수행함.


```
# 동적 라이브러리 빌드
gcc -fPIC -c rc4.c
gcc -shared -o librc4.so rc4.o

# 웹 서버 엔진을 포함한 메인 바이너리 빌드
make clean
make     
chmod 4777 insecure_httpd
chmod 4777 htpasswd
```

* 실행은 user 유저로 수행함. (su user)

```
# 변수 설정 및 실행 (브릿지 형식이 아니면 바인딩 실패함.)
export SHAREDFILE=librc4.so
./insecure_httpd -r

# 동작 중인 서버 확인
ps -ef | grep insecure
```

* 이후에 웹 브라우저에서 실행.