# Interceptor 

- 데이터를 가로채서 처리하는 영역이다.
- 컨트롤러에 접근하기 전, request와 response 처리가 가능하다.
- Filter와의 차이는 **Dispatch Servlet**이전에 처리하는지, 이후에 처리하는지가 다르다.

![img](https://t1.daumcdn.net/cfile/tistory/992590395ABF406F18)



> 1. Filter는 Dispatcher servlet의 앞단에서 정보를 처리하고, Interceptor는 Dispatcher servlet에서 Handler(Controller)로 가기 전에 정보를 처리한다.
>
> 2. 또한 필터는 J2EE 표준 스펙에 정의 되어 있는 기능이며, 인터셉터의 경우는 Spring Framework에서 자체적으로 제공하는 기능이라고 한다.
>
> 정확히 어떤 상황에 어떤 기능을 사용해야 하는가에 대해서는 갑론을박이 많지만, 인코딩이나 보안 관련 처리와 같은 web app의 전역적으로 처리해야 하는 로직은 필터로 구현하고 클라이언트에서 들어오는 디테일한 처리(인증, 권한 등)에 대해서는 주로 인터셉터에서 처리하는듯 하다.
>
> 그렇다면, 위에서 예를 든 로그인 세션 인증에 관해서는 인터셉터에서 처리하는 것이 더 나을 것이다. 
>
> 출처 : https://www.leafcats.com/39

![https://user-images.githubusercontent.com/48986787/122791845-8fb83780-d2f4-11eb-9207-9576609c24a5.png](https://user-images.githubusercontent.com/48986787/122791845-8fb83780-d2f4-11eb-9207-9576609c24a5.png)

> ```
> Request -> Servlet Filter -> Dispatcher Servlet -> HandlerInterceptor -> Controller
> ```
>
> 출처: https://sjh836.tistory.com/163 [빨간색코딩:티스토리]



## 코드 설정

https://www.leafcats.com/40

```java
@Component
public class CertificationInterceptor implements HandlerInterceptor{
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 클라이언트의 요청을 컨트롤러에 전달하기 전에 호출된다. 여기서 false를 리턴하면 다음 내용(Controller)을 실행하지 않는다.
        HttpSession session = request.getSession();
        UserVO loginVO = (UserVO) session.getAttribute("loginUser");
 
        if(ObjectUtils.isEmpty(loginVO)){
            response.sendRedirect("/moveLogin.go");
            return false;
        }else{
            session.setMaxInactiveInterval(30*60);
            return true;
        }
        
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // 클라이언트의 요청을 처리한 뒤에 호출된다. 컨트롤러에서 예외가 발생되면 실행되지 않는다.
        
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 클라이언트 요청을 마치고 클라이언트에서 뷰를 통해 응답을 전송한뒤 실행이 된다. 
        // 뷰를 생성할 때에 예외가 발생할 경우에도 실행이 된다.
        
    }
 
}
```



WebConfig 파일 설정

```java
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    
    /*
     * 로그인 인증 Interceptor 설정
     * */
    @Autowired
    CertificationInterceptor certificationInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(certificationInterceptor).order(0) // 순서 설정 가능
                .addPathPatterns("/**/*.do") // 해당 패턴으로 들어온 요청에만 인터셉터를 설정한다.
        		.excludePathPatterns("???"); // 제외하고싶은 패턴 설정
        registry.addInterceptor(다른 인터셉터).order(1); //보통은 등록된 순서대로 진행됨
    }
 
}
```

**pattern**
\* : 이름 하나를 의미하며 글자수, 글자 등 제한이 없습니다.
? : 글자하나를 의미
** : 하위 이름까지 포함하여 글자수, 글자 등 제한이 없습니다.









## Interceptor return redirect

```java
if (userSession == null) {
    response.sendRedirect("/login");
}
```

⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇

```java
if (userSession == null) {
    response.sendRedirect("/login");
    return false; // 컨트롤러에 가지 않고 끝남
}
```









---

[Interceptor & @Auth](https://victorydntmd.tistory.com/177?category=698080)

[Interceptor 설정](https://eunoia3jy.tistory.com/84)