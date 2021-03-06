#### 자바스크립트란?

웹 브라우저에서 동작하는 스크립트 언어

웹의 발전에 따라, 웹 아키텍처에 변화가 일어났음. 서버에서 담당했던 역할들이 대부분 웹 브라우저로 이동하게 되었는데 이 중심 위치한 언어가 '자바스크립트'

또한, jQuery가 등장하여 쉽게 DOM을 핸들링할 수 있게 되면서 폭발적인 성장을 보임

현재는 브라우저 기반 자바스크립트 라이브러리뿐만 아니라, Node.js와 같은 서버 기반 환경에서도 사용할 수 있는 라이브러리들이 나오고 있음

```
이제 '자바스크립트'만으로 클라이언트와 서버 개발을 동시에 할수있게 된 것!
```



#### 함수

---

함수는, 코드를 묶고 이름을 붙여 재사용하기 위해 만듬



##### 익명 함수

> 이름이 없는 함수

익명 함수는 이름이 없으므로 변수에 넣어 사용해야 함

```
var 변수 = function() { ... };
```



##### 선언적 함수

> 이름이 존재

함수에 이름이 주어지므로 이름으로 호출함

```
function 함수() { ... };
```



웹브라우저는 script 태그 내부에서 우선적으로 `선언적 함수`부터 읽음. (따라서 선언적 함수에 활용한 이름을 함수 위에서 불러와도 실행이 가능함)



##### 내부 함수

프로그램 규모가 커질 때, 충돌을 방지하기 위함

```
function 외부 함수() {
    
    function 내부 함수1() {
        ...
    }
    function 내부 함수2() {
        ...
    }
}
```

이렇게 함수 안에서 내부 함수를 활용해서 구현하면, 외부에 같은 이름의 함수가 있어도 내부 함수를 우선 실행하게 됨 (협업하는 사람과 예기치 않게 다른 역할을 하는 같은 이름의 함수를 만들어도 내부 함수를 이용하면 문제가 발생하지 않음!)

또한, 내부 함수는 내부 함수를 포함하는 함수에서만 실행이 가능함 (jQuery는 대부분 내부 함수로 작성됨)



##### 자기 호출 함수

> 함수를 생성하자마자 호출

다른 개발자에게 영향을 주지 않도록 하기 위해 사용

```
<script>
	(function() {
        ...
        ...
	})();
</script>
```



##### 콜백 함수(★)

> 매개변수로 전달하는 함수

함수도 하나의 자료형이므로, 매개변수로 전달하는 것이 가능함

※보통 익명함수로 사용됨 (익명 콜백 함수)

```
<script>
	function 함수1(함수2) {
        for(var i = 0; i < 5; i++) {
            함수2();
        }
	}
	
	함수1(function() {
      alert("함수호출!");  
	});
</script>
```

콜백 함수를 통해 `alert("함수호출!");`이 5번 출력된다.



나중에 콜백 안에 콜백 안에 콜백 이런식으로 구현이 이루어질 수도 있음. 이러면 가독성이 좋지 않고 유지보수에 어려움이 생김 ( 콜백 지옥이라고 말함 )

이를 해결하기 위해 promise나 async와 같은 문법이 생김 ( 추후 정리 )



##### 함수를 리턴하는 함수

> 함수를 호출할 때 한번더 괄호를 사용해 해당 return 함수를 호출할 수 있음
>
> `함수()();`





##### 클로저(★)

> 사용범위가 끝난 데이터를 scope 밖에서 사용 가능하게 하는 것
>
> 함수를 return하는 방식을 이용함



함수 안에서 선언된 변수는 지역 변수다. 이 변수는 일반적으로 함수 외부에서 사용이 불가능함

(지역 변수는 함수가 실행될 때 생성되고, 함수가 종료되면 사라지기 때문)



하지만, 때로 이 지역변수를 외부에서 가져오고 싶을 때가 있음. 이때 사용하는 것이 클로저!

```
<script>

	function test(name) {
        var output = 'Hello' + name;
        return function() {
            alert(output);
        };
	}
	
	test('JavaScript!'); // 출력하는 호출문

</script>
```

이런식으로 test 함수에 return문을 통한 익명함수를 활용하면, test 함수 안에 구현한 output이라는 변수에 담긴 값을 사용하는 것이 가능함

(자바스크립트가 해당 변수가 이후에 활용될 가능성이 있다고 판단하고 제거하지 않고 남겨둠)



test()함수에서 output을 클로저를 통해 가져오면, 그 함수의 고유한 지역 변수가 됨



##### 반복문과 콜백 함수를 활용한 실행 순서 파악

반복문과 콜백 함수로 구현할 때 실행 순서와 출력물에 대해 파악해보기

```
<script>
	for (var i = 0; i < 3; i++) {
        setTimeout(function() {
            alert(i);
        }, 0);
	}
</script>
```

0, 1, 2가 출력될 것 같지만 3, 3, 3이 출력됨

> setTimeout() 함수를 호출하는 시점이 반복문이 모두 끝난 이후이기 때문

0, 1, 2를 출력하려면 **자기호출 함수와 클로저를 활용**해야 함

```
<script>
	for (var i = 0; i < 3; i++) {
        (function(closed_i) {
            setTimeout(function() {
                alert(i);
            }, 0);
        })(i);
	}
</script>
```

반복문 실행동안 클로저가 생성되어 변수 closed_i에 값을 저장할 수 있음



#### 객체

---

객체 만들기

```
<script>
    var product = {
		제품명 : '7D 건조 망고',
		유형 : '당절임',
		성분 : '망고, 설탕, 메타중아황산나트륨, 치자황색소',
		원산지: '필리핀'
    };
</script>
```

이처럼 key와 value값으로 이루어져 있음



##### 값에 접근 하는 방법

```
product.제품명
product['제품명']
```



자료형 안에 메소드도 넣을 수 있음

```
var person = {
  name: '윤인성',
  eat: function (food) {} // 메서드
};
```



호출하는 방법

```
person.eat();
```



자기 자신이 가진 속성을 출력하고 싶을 때는 'this 키워드'를 이용한다.

```
eat : function(food) {
    alert(this.name + '이 ' + food + '를 먹습니다');
}
```



##### 객체와 반복문을 통한 출력

```
var product = {
    name: '제품A',
    price: '15000',
    language: '한국어'
};

var output = '';

for(var key in product) {
    output += key + ': ' + product[key] + '\n';
}

alert(output);
```

객체 요소 개수만큼 반복문이 실행되고, output에 담겨서 출력된다.



##### in 키워드

해당 키가 객체 안에 있는지 true/false로 확인이 가능함

```
('이름' in student)
```



##### with 키워드

객체의 속성 사용을 쉽게 할 수 있도록 도와줌

```
with(<객체>) {
    <코드>
}
```



##### 동적으로 속성 추가/제거

```
student.번호 = '1234'; //추가

delete (student.번호);
```

객체의 속성을 동적으로 추가와 제거를 할 수 있음



##### 함수를 통한 객체 생성

```
var students = [];

students.push({
   이름: '윤인성', 국어: 87,
   수학: 98, 영어: 88, 장래희망: '생명공학자'
});
```





#### 생성자 함수(프로토타입)

---

객체를 생성할 때 사용하는 함수



##### 생성자 함수 선언

```
function <생성자 함수 이름>() {
    this.<속성 이름>
    this.<속성 이름>
}

<생성자 함수 이름>.prototype.<메서드 이름>
```



##### 생성자 함수를 사용한 객체 생성

```
new <생성자 함수 이름>()
```





#### 기본 내장 객체

---

ES5부터 JSON 객체 지원

JSON : 자바스크립트 객체의 형태를 갖는 문자열



```
JSON.stringify() : 자바스크립트 객체를 JSON 문자열로 변환
JSON.parse() : JSON 문자열을 자바스크립트 객체로 변환
```



stringify 메서드에 매개변수에 넣은 객체에서 메소드 안에 toJson()을 구현해두면, 객체 전체를 JSON으로 변환하지 않고 해당 메소드 안의 내용만 변환시킨다.



#### 이벤트

----

이벤트 종류

- 마우스 이벤트
- 키보드 이벤트
- HTML 프레임 이벤트
- HTML 입력 양식 이벤트
- 유저 인터페이스 이벤트
- 구조 변화 이벤트
- 터치 이벤트

