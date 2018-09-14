INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('ADM0000001', 'admin@test.com', '875d45c75b1c92b2c3504d23f0b2f7a1', 4, 9, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('STD0000001', 'std1@test.com', '698d51a19d8a121ce581499d7b701668', 4, 1, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('STD0000002', 'std2@test.com', '698d51a19d8a121ce581499d7b701668', 4, 1, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('STD0000003', 'std3@test.com', '698d51a19d8a121ce581499d7b701668', 4, 1, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('STD0000004', 'std4@test.com', '698d51a19d8a121ce581499d7b701668', 4, 1, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('STD0000005', 'std5@test.com', '698d51a19d8a121ce581499d7b701668', 4, 1, 110000, now());

INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('SCH0000001', 'sch1@test.com', '698d51a19d8a121ce581499d7b701668', 4, 2, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('SCH0000002', 'sch2@test.com', '698d51a19d8a121ce581499d7b701668', 4, 2, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('SCH0000003', 'sch3@test.com', '698d51a19d8a121ce581499d7b701668', 4, 2, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('SCH0000004', 'sch4@test.com', '698d51a19d8a121ce581499d7b701668', 4, 2, 110000, now());
INSERT INTO DS_USER_TX(USER_ID, EMAIL, PWD, JOIN_TYPE, MEM_TYPE, REG_STATUS, UPD_DATE) 
VALUES('SCH0000005', 'sch5@test.com', '698d51a19d8a121ce581499d7b701668', 4, 2, 110000, now());

INSERT INTO DS_STUDENT_TX(STUDENT_ID, FNAME, LNAME, GENDER, CURRENT_TYPE, RCMMD_TYPE, ZIPCODE, ADDRESS1, ADDRESS2, ADDRESS3, BIRTH_Y, BIRTH_M, BIRTH_D, INS_DATE) 
VALUES('STD0000001', 'FNAME', 'LNAME', 'M', 2, 3, '12345', '서울특별시', '동작구', '사당동2-2-28', 2001, 5, 5, now());
INSERT INTO DS_STUDENT_TX(STUDENT_ID, FNAME, LNAME, GENDER, CURRENT_TYPE, RCMMD_TYPE, ZIPCODE, ADDRESS1, ADDRESS2, ADDRESS3, BIRTH_Y, BIRTH_M, BIRTH_D, INS_DATE) 
VALUES('STD0000002', 'FNAME', 'LNAME', 'F', 1, 2, '12228', '경기도', '남양주시', '다산동2-2-28', 2001, 5, 5, now());
INSERT INTO DS_STUDENT_TX(STUDENT_ID, FNAME, LNAME, GENDER, CURRENT_TYPE, RCMMD_TYPE, ZIPCODE, ADDRESS1, ADDRESS2, ADDRESS3, BIRTH_Y, BIRTH_M, BIRTH_D, INS_DATE) 
VALUES('STD0000003', 'FNAME', 'LNAME', 'M', 3, 4, '12334', '서울특별시', '강남구', '서초동2-2-28', 2001, 5, 5, now());
INSERT INTO DS_STUDENT_TX(STUDENT_ID, FNAME, LNAME, GENDER, CURRENT_TYPE, RCMMD_TYPE, ZIPCODE, ADDRESS1, ADDRESS2, ADDRESS3, BIRTH_Y, BIRTH_M, BIRTH_D, INS_DATE) 
VALUES('STD0000004', 'FNAME', 'LNAME', 'F', 1, 2, '12532', '경기도', '안양시', '만안동2-2-28', 2001, 5, 5, now());
INSERT INTO DS_STUDENT_TX(STUDENT_ID, FNAME, LNAME, GENDER, CURRENT_TYPE, RCMMD_TYPE, ZIPCODE, ADDRESS1, ADDRESS2, ADDRESS3, BIRTH_Y, BIRTH_M, BIRTH_D, INS_DATE) 
VALUES('STD0000005', 'FNAME', 'LNAME', 'M', 1, 2, '12423', '서울특별시', '송파구', '잠실동2-2-28', 2001, 5, 5, now());

INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000001', 1, '나름대로 기준을 가지고 학교 행사에 참여', 
'중학교는 사람으로서의 기초를 만드는 시기라고 생각하고 있기 때문에, 자유롭게 자신을 높이면서 다른 사람도 소중히 할 생각할 수 있는 환경을 갖고 있는 학교를 찾았습니다. 설명회를 통해 학교가 가장 소중히하고 있는 교육과 방침에 대해 공감 할 수 있는 것에 확인할 수 있었습니다.', 
NOW(), 'admin');
INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000002', 1, '중, 고등학교는 인생에서 가장 중요한시기', 
'진학 학교 정보 수집, 이벤트 참여, 최근 실적이 좋은 입시학원의 WEB에서 리뷰 검색, 학교 선생님과의 면담 등으로 아이들과 함께 참여하거나 웹사이트에서 정보를 활용하기도했습니다. 마지막은 직접 학교에 가서보고, 학교의 분위기와 여러가지를 검토한 후에 결정했습니다. 중, 고등학교는 인생에서 가장 중요한시기라고 생각합니다.', 
NOW(), 'admin');
INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000003', 1, '우선, 사립 또는 국립도 하나에서부터 시작', 
'우선, 사립 또는 국립을 위해 주로 엄마끼리 정보 교환하고있는 상황에서 중학교 시험을 어떻게 할까 고민하다가 앞으로 학교의 정보를 수집하는 데에 있어서 사립 또는 국립학교까지 갈지를 결정하는 중요한 포인트가된다고 생각합니다. 역시 다른 사람의 흐름보다 자녀의 미래를 생각하고 결정해야 한다고 생각합니다.', 
NOW(), 'admin');
INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000004', 1, '축구의 재능을 살릴 수 있는 학교 선택', 
'맞벌이로 부부끼리 의견을 맞추는 것이 매우 어렵다고 느끼고 있었기에 아이가 좋아하는 축구의 재능을 살릴 수 있는 학교의 선택은 어려웠습니다. 실제로 학교의 의견을 듣고 싶습니다. 지금은 수영과 영어를하고 있지만 그것도 좋은 것인지 솔직히 모르는 상황입니다.', 
NOW(), 'admin');
INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000005', 1, '왕따 방지를 위한 대책을 세우는 학교, 그 학교의 브랜드 가치', 
'아직 아이가 어리지만 사립학교에 진학을 결정하고있습니다. 실제 정보는 대부분 집 주변의 엄마들과의 정보 교환을 통해 수집하고있는 상황입니다만, 중요한 것은 여자아이이기 때문에 학교의 브랜드와 건전성 등도 확인하고 있습니다. 특히 왕따 대책에 대해 학교 정책 등이 있는 곳인지 확인하고 있습니다.', 
NOW(), 'admin');
INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000006', 1, '자립 할 수있는 아이가 되었으면', 
'실제 직장에서 신입 사원의 모습을 보면, 자립에 일 수 있는지 커뮤니케이션을 잘 할 수 있는지는 어린 시절의 영향이 크다고 생각합니다. 학교의 주요 결정은 아이에게 맡기고 있지만, 학교 선택에 도움이 될 책과 이벤트를 확인하고 함께 실제로 이야기 하고 있습니다.', 
NOW(), 'admin');
INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000007', 1, '그다지 높지 않은 성적으로도 선택할 수 있는 학교들', 
'학교 정보 중 학교 환경, 집과의 거리, 동아리, 실제 학교의 생생한 정보를 원하고 있지만, 학교의 대응이 잘 되지 않네요. 교육 방침을 가지고 학교의 정보를 갖고 있으며, 실제 재학중인 학생과 학부모의 정직한 목소리가 교환되고 있는 커뮤니티에도 가입 싶습니다.', 
NOW(), 'admin');
INSERT INTO DS_BLOG_TX(STUDENT_ID, BLOG_TYPE, TITLE, CONTENTS, UPD_DATE, UPD_ID) 
VALUES ('STD0000008', 1, '맞벌이 부모의 경력 활용', 
'학부모로서 학교의 교육 방침이 얼마나 심각하게 아이들에 대해 생각하고 있는지, 자연이 환경이 풍부한지, 도서관을 갖추고 있는지, 현재 다니고있는 학생이 생기 발랄하게 생활하고 있는지 등의 원시 정보가 교환 할 수 있기를 바랍니다. 학교는 진지하게 학생들 마주하고 고민하고 있는지, 기초 공부에 충실한지 등 학교의 교육방침에 대해서도 정보를 얻고 싶습니다.', 
NOW(), 'admin');
