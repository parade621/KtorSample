# KtorSample
### Ktor를 업무에 적용하기 전, 테스트를 위해 만든 Sample Code

왜 Ktor인가.
Ktor는 호출에 실패한 API 통신 코드를 자동으로 재시도 해주는 로직이 있다.
회사 앱이 서비스 중인 지역의 무선 통신 환경이 한국 만큼 좋지 않아서 언제든지 통신에 장애가 있을 수 있다.
때문에 자동으로 재시도 하는 로직을 추가하면 사용자들에게 좀더 편의를 제공할 수 있지 않을까 싶어서 미리 적용해 보려한다.
