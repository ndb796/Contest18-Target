[ Virtual Box 계정 정보 ]

* 아이디: dowon
* 비밀번호: test1357

[ 기존 서버 동작 중지 ]

```
ps -aux  | grep tomcat
sudo kill pid
```

(프로세스가 꺼진 뒤에도 계속 실행되는데, 이는 무시함.)

[ STS 실행 ]

* UserMapper.xml에서 MAX(USER_ID)의 #{keyword} 부분의 오탈자를 수정하고 실행하면 정상적으로 작동합니다.