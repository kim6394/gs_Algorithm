## 서로소 집합(Disjoint-sets)



##### 서로소 or 상호배타 집합

> 서로 중복 포함된 원소가 없는 집합
>
> (= 즉, 교집합이 없는 것)



##### 대표자(representative)

> 집합에 속한 하나의 특정 멤버를 통해서 각 집합들을 구분



##### 상호배타 집합 표현 방법

> 연결 리스트
>
> 트리



##### 상호배타 집합 연산

> Make-Set(x), Find-Set(x), Union(x,y)



##### 트리를 이용해 상호배타 집합 표현된 모습





> 부모는 해당 노드의 Find-Set 값



#### 연산

Make_Set(x) : 유일한 멤버 x를 포함하는 새로운 집합을 생성하는 연산

Find_set(x) : x를 포함하는 집합을 찾는 연산(해당 노드의 부모 정보 갱신)

Union(x,y) : x와 y를 포함하는 두 집합을 통합하는 연산



