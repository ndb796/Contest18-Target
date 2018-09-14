INSERT INTO DS_SCHMID_BASE(SCH_ID, ID, SCHOOL_NAME, SCH_CATEGORY, SCH_GUBUN, HIRAGANA, PREFECTURE_ID, ZIPCODE, ADDRESS, GOOGLE, TEL, URL, INS_DATE)
VALUES('MSCH0000001','40010','제일중학교','국립','공학','제일중학교',10,'11111','서울시 강남구 도곡동 1','<a href="https://www.google.co.kr/maps/place/%E5%BE%80%E5%8D%81%E9%87%8C%E9%A7%85/@37.562522,127.0315208,17z/data=!4m8!1m2!2m1!1z7JmV7Iut66as7Jet!3m4!1s0x357ca359391d9747:0x3f7f791155a18366!8m2!3d37.5611284!4d127.035505" target="_blank">google 지도 표시</a>','03-5978-5865','http://www.google.co.kr',NOW());
INSERT INTO DS_SCHMID_BASE(SCH_ID, ID, SCHOOL_NAME, SCH_CATEGORY, SCH_GUBUN, HIRAGANA, PREFECTURE_ID, ZIPCODE, ADDRESS, GOOGLE, TEL, URL, INS_DATE)
VALUES('MSCH0000002','40020','서울중학교','국립','공학','서울중학교',10,'112-0012','서울 동작구 1-9-1','<a href="http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e6%96%87%e4%ba%ac%e5%8c%ba%e5%a4%a7%e5%a1%9a1-9-1" target="_blank">google 지도 표시</a>','03-3945-3231','http://www.google.co.kr',NOW());
INSERT INTO DS_SCHMID_BASE(SCH_ID, ID, SCHOOL_NAME, SCH_CATEGORY, SCH_GUBUN, HIRAGANA, PREFECTURE_ID, ZIPCODE, ADDRESS, GOOGLE, TEL, URL, INS_DATE)
VALUES('MSCH0000003','40030','서울시립중학교','시립','남자','서울시립중학교',10,'154-0001','서울 용산구 1-7-1','<a href="http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e4%b8%96%e7%94%b0%e8%b0%b7%e5%8c%ba%e6%b1%a0%e5%b0%bb4-7-1" target="_blank">google 지도 표시</a>','03-3411-8521','http://www.google.co.kr',NOW());
INSERT INTO DS_SCHMID_BASE(SCH_ID, ID, SCHOOL_NAME, SCH_CATEGORY, SCH_GUBUN, HIRAGANA, PREFECTURE_ID, ZIPCODE, ADDRESS, GOOGLE, TEL, URL, INS_DATE)
VALUES('MSCH0000004','40040','시청중학교','국립','공학','시청중학교',10,'184-8501','서울 종로구 2-1-1','<a href="http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e5%b0%8f%e9%87%91%e4%ba%95%e5%b8%82%e8%b2%ab%e4%ba%95%e5%8c%97%e7%94%ba4-1-1" target="_blank">google 지도 표시</a>','042-329-7833','http://www.google.co.kr',NOW());
INSERT INTO DS_SCHMID_BASE(SCH_ID, ID, SCHOOL_NAME, SCH_CATEGORY, SCH_GUBUN, HIRAGANA, PREFECTURE_ID, ZIPCODE, ADDRESS, GOOGLE, TEL, URL, INS_DATE)
VALUES('MSCH0000005','40050','한국중학교','국립','공학','한국중학교',10,'178-0063','서울 광진구 2-22-1','<a href="http://maps.google.co.jp/maps?f=q&hl=ja&q=%e6%9d%b1%e4%ba%ac%e9%83%bd%e7%b7%b4%e9%a6%ac%e5%8c%ba%e6%9d%b1%e5%a4%a7%e6%b3%895-22-1" target="_blank">google 지도 표시</a>','03-5905-1326','http://www.google.co.kr',NOW());

INSERT INTO DS_SCHMID_BASEINFO(SCH_ID, ID, SCHOOL_NAME, SCHOOL_COPY, PRINCIPAL, HISTORY, SCH_COUNT, STATION, CURRICULUM, START_TIME, END_TIME, SCH_EXPLAIN1, HENSACHI, GAKUHI, INS_DATE) 
VALUES('MSCH0000001', '40010', '제일중학교', '대한민국 제일 중학교로...', '신나게 놀고, 공부하고', '1924년', '1000', '왕십리', '영어특기생', '09:00', '17:30', '좋은학교', '20%', '10%', NOW());
INSERT INTO DS_SCHMID_BASEINFO(SCH_ID, ID, SCHOOL_NAME, SCHOOL_COPY, PRINCIPAL, HISTORY, SCH_COUNT, STATION, CURRICULUM, START_TIME, END_TIME, SCH_EXPLAIN1, HENSACHI, GAKUHI, INS_DATE) 
VALUES('MSCH0000002', '40020', '서울중학교', '모두가 행복한 서울 미래 교육', '배움과 배려의 열정으로 모두가 행복한 교육', '1951년 개교 이래...', '500', '서울역', '교육장학생', '09:00', '17:30', '좋은학교', '20%', '10%', NOW());
INSERT INTO DS_SCHMID_BASEINFO(SCH_ID, ID, SCHOOL_NAME, SCHOOL_COPY, PRINCIPAL, HISTORY, SCH_COUNT, STATION, CURRICULUM, START_TIME, END_TIME, SCH_EXPLAIN1, HENSACHI, GAKUHI, INS_DATE) 
VALUES('MSCH0000003', '40030', '서울시립중학교', '미래사회를 주도할 바른 인성을 지닌 창의적인 인재육성', '배움의 즐거움이 있는 행복한 학교', '2000년 개교', '1000', '삼성역', '운동학생', '09:00', '17:30', '좋은학교', '20%', '10%', NOW());
INSERT INTO DS_SCHMID_BASEINFO(SCH_ID, ID, SCHOOL_NAME, SCHOOL_COPY, PRINCIPAL, HISTORY, SCH_COUNT, STATION, CURRICULUM, START_TIME, END_TIME, SCH_EXPLAIN1, HENSACHI, GAKUHI, INS_DATE) 
VALUES('MSCH0000004', '40040', '시청중학교', '지역사회아ㅗ 함꼐하는 인터넷중독 예방 프로그램 운영 학교', '자기주도적으로 공부하며 꿈이 있는 학생', '2005년 중고등학교 통합', '1000', '도봉역', '특별활동 활발', '09:00', '17:30', '좋은학교', '20%', '10%', NOW());
INSERT INTO DS_SCHMID_BASEINFO(SCH_ID, ID, SCHOOL_NAME, SCHOOL_COPY, PRINCIPAL, HISTORY, SCH_COUNT, STATION, CURRICULUM, START_TIME, END_TIME, SCH_EXPLAIN1, HENSACHI, GAKUHI, INS_DATE) 
VALUES('MSCH0000005', '40050', '한국중학교', '꿈을 키워가는 행복키움터 운영', '학교를 신뢰하며 교사를 존경하는 학부모, 보람과 긍지를 가지고 교육하는 존경받는 선생니므 최선을 다하고 꾸준히 노력하여 실력을 키우는 학생', '1924년', '1000', '신도림', '일본재매학교', '09:00', '17:30', '좋은학교', '20%', '10%', NOW());


