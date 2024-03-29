# Board-Study
ATDD(Acceptance Test Driven Development)로 게시판 만들기
: ATTD(Acceptance Test Driven Development) & TDD(Test Driven Development) 진행

## 진행 순서

1. application 에서 acceptance test 먼저 만들기 
2. 작성한 인수테스트를 바탕으로 최종으로 통과해야할 test 생성
    
    given) url,request주어졌을때
    
    when) 만들어놓은 MockMvc가 perform(post)하고, 그 결과인 perform(get)으로
    
    then) status와 내가 대입한 given의 request 값과 실제 jsonPath상 저장된 값이 같은지 비교 
    
3. accpetnace test 작성 과정에서 request와 response 혹은 특정 객체에 대한 정의가 요구 될 것이다. 그건 내부 클래스로 생성하면서 만든다.
4. domain에 관련된 내부 클래스만 일단 옮겨놓고
5. service에서 unit test를 생성한다. @Service 작성과 동시에 필요한 interface들 미리 세팅
    
    given) 입력값
    
    when) Mockito.when(행동정의).thenReturn(입력값)
    
    테스트에서 할 행동 수행 후 결과값을 가지고
    
    then) Assertions.assertAll() → 결과 확인
    
6. test에 필요한 dto, spi, defaultService 등을 구현
    1. ******************************************************************************************************************************************************************근데 왜 UserServie는 주입받고, UserServiceRepository는 Mocking 하는건가??******************************************************************************************************************************************************************
        
        →UserServiceTest에서 테스트하고자 하는 부분은 UserService에 관한 부분이다. 즉, DefaultUserService가 있고, 그렇기에 주입받아서 사용하는 것이 가능하다. 반면, UserServiceRepository의 구현체는 MemoryRepository에 존재하며 이 부분에 대한건 UserServiceTest에서 테스트하고자 하는 부분이 아닐 뿐더러 의존성 방향에서도 UserService 상에서 Repository에 관한 부분은 구현되어 있지 않다. 따라서 가짜 객체로 사용해야 하는 부분인 것이다.
        
7. MemoryTest 작성
    
    given) UserDto
    
    when) repository에서 메소드가 해야할 행동들 수행
    
    then) 원하는 대로 결과 나왔는지 확인(Assert)
    
8. MemoryDatabase랑 MemoryUserRepository(spi implemented) 구현
9. application으로 돌아와 request, response, controller 구현
