# 2주차 실습과제

- GET
    - ID로 멤버 조회
        
        # 1️⃣ 어떤 API 인가요?
        
        Member의 ID로 해당 Member 정보를 조회하는 API 입니다.
        
        # 2️⃣ Request
        
        요청 : `/api/v1/member/{memberId}`
        
        ## Path Parameter
        
        | 자료형  | 이름  | 값(예시) |
        | --- | --- | --- |
        | Long | memberId | 1 |
        
        ---
        
        # 3️⃣ Response
        
        # 성공 시
        
        ### ✨ Response Body
        
        | status | 상태 코드 |
        | --- | --- |
        | id | Long |
        | name | String |
        | part | String |
        | age | Integer |
        
        JSON 
        
        ```json
        {
            "id": 2,
            "name": "나님",
            "part": "SERVER",
            "age": 25
        }
        ```
        
        # 실패 시
        
        ### ✨ Response Body
        
        | status | 상태 코드 |
        | --- | --- |
        | errorCode | String |
        | errorMessage | String |
        | date | Date |
        
        JSON 
        
        ```json
        {
        		"errorCode": "NOT_FOUND",
            "errorMessage": "해당 ID 유저가 존재하지 않습니다.",
            "date": "2024-04-14T10:24:16.377+00:00"
        }
        ```
        
    - 모든 멤버 조회
        
        # 1️⃣ 어떤 API 인가요?
        
        모든 Member 정보를 조회하는 API 입니다.
        
        # 2️⃣ Request
        
        요청 : `/api/v1/member/all`
        
        ---
        
        # 3️⃣ Response
        
        # 성공 시
        
        ### ✨ Response Body → List
        
        | status | 상태 코드 |
        | --- | --- |
        | id | Long |
        | name | String |
        | part | String |
        | age | Integer |
        
        JSON 
        
        ```json
        [
            {
                "id": 2,
                "name": "나님",
                "part": "SERVER",
                "age": 25
            },
            {
                "id": 3,
                "name": "나님",
                "part": "SERVER",
                "age": 25
            },
            {
                "id": 4,
                "name": "나님",
                "part": "SERVER",
                "age": 25
            },
            {
                "id": 5,
                "name": "나님",
                "part": "SERVER",
                "age": 25
            },
            {
                "id": 6,
                "name": "나님",
                "part": "SERVER",
                "age": 25
            }
        ]
        ```
        
        # 실패 시
        
        ### ✨ Response Body
        
        | status | 상태 코드 |
        | --- | --- |
        | errorCode | String |
        | errorMessage | String |
        | date | Date |
        
        JSON 
        
        ```json
        {
            "errorCode": "NOT_FOUND",
            "errorMessage": "존재하는 유저가 없습니다.",
            "date": "2024-04-14T10:27:30.513+00:00"
        }
        ```
        
- POST
    - 유저 추가
        
        # 1️⃣ 어떤 API 인가요?
        
        유저 정보 입력을 통해 유저를 추가합니다. 
        
        # 2️⃣ Request
        
        요청 : `/api/v1/member/`
        
        ## Request Body
        
        | 이름 | 타입 | Description |
        | --- | --- | --- |
        | name | String | 이름 |
        | part | String | 부서 {”IOS”|”SERVER”|”ANDROID”} |
        | age | Integer | 나이 |
        
        ---
        
        # 3️⃣ Response
        
        # 성공 시
        
        ### ✨ Response Body
        
        | status | 상태 코드 |
        | --- | --- |
        | id | Long |
        | name | String |
        | part | String |
        | age | Integer |
        
        JSON 
        
        ```json
        {
            "id": 2,
            "name": "현욱님",
            "part": "SERVER",
            "age": 25
        }
        ```
        
- DELETE
    - 멤버 삭제
        
        # 1️⃣ 어떤 API 인가요?
        
        Member의 ID로 해당 Member를 삭제하는 API 입니다.
        
        # 2️⃣ Request
        
        요청 : `/api/v1/member/{memberId}`
        
        ## Path Parameter
        
        | 자료형  | 이름  | 값(예시) |
        | --- | --- | --- |
        | Long | memberId | 2 |
        
        ---
        
        # 3️⃣ Response
        
        # 성공 시
        
        ### ✨ Response Body
        
        | status | 상태 코드 |
        | --- | --- |
        | String | Long |
        
        JSON 
        
        ```json
        {
        	"message": "삭제가 완료되었습니다."
        }
        ```
        
        # 실패 시
        
        ### ✨ Response Body
        
        | status | 상태 코드 |
        | --- | --- |
        | errorCode | String |
        | errorMessage | String |
        | date | Date |
        
        JSON 
        
        ```json
        {
        		"errorCode": "NOT_FOUND",
            "errorMessage": "해당 ID 유저가 존재하지 않습니다.",
            "date": "2024-04-14T10:33:55.450+00:00"
        }
        ```