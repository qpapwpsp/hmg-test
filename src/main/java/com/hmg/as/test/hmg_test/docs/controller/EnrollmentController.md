# 📘 EnrollmentController

수강 등록 및 정보 조회에 관련된 컨트롤러입니다

- URL Prefix: `/`
- 서비스 클래스: `EnrollmentService`
- Cross-Origin: `http://localhost:5173`

## 📌 주요 API 목록

### GET /{studentId}/courses
- 설명: 학생 ID로 수강 과목 목록 조회
- 응답: `List<EnrollVo>`

### GET /{courseId}
- 설명: 과목 ID로 과목 상세 조회
- 응답: `CourseVo`

### GET /course/{title}
- 설명: 과목 제목으로 과목 리스트 조회
- 응답: `List<CourseVo>`

### POST /student/enroll
- 설명: 학생 등록
- 입력: `StudentVo`
- 응답: 문자열 "학생 등록 완료"

### POST /course/enroll
- 설명: 강좌 등록
- 입력: `CourseVo`
- 응답: 문자열 "학생 등록 완료"

### POST /professor/enroll
- 설명: 교수 등록
- 입력: `ProfessorVo`
- 응답: 문자열 "교수 등록 완료"

### POST /enroll
- 설명: 수강 신청
- 입력: `EnrollVo`
- 응답: 문자열 "수강 신청 완료"

### PUT /course/enroll/{courseId}
- 설명: 과목 정보(제목 등) 수정
- 입력: `CourseVo`
- 응답: 문자열 "과목명 수정 완료"

### DELETE /{courseId}
- 설명: 과목 삭제
- 응답: 문자열 "Course deleted: ID = {id}"

### POST /course/studentCount
- 설명: 과목당 학생 수 조회
- 입력: `CourseVo`
- 응답: `List<CourseVo>` (학생 수 포함)

### GET /course/studentCountWithSubQuery
- 설명: subQuery 사용하여 과목당 학생 수 조회
- 응답: `List<CourseVo>`
