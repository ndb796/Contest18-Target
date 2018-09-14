[ Virtual Box 계정 정보 ]

dowon
test1357

[ 기존 서버 동작 중지 ]

ps -aux  | grep tomcat
sudo kill pid

(프로세스가 꺼진 뒤에도 계속 실행됨)

[ STS 실행 ]

UserMapper.xml에서 MAX(USER_ID)의 #{keyword} 부분의 오탈자를 수정하고 실행하면 정상적으로 작동합니다.