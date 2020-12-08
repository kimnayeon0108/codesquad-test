# codesquad-test   
## 2단계 평면 큐브 구현하기   
3 * 3의 2차원 배열이 있다.

```java
R R W
G C W
G B B
```

사용자 입력을 받아서 아래의 동작을 하는 프로그램을 구현하시오

>U     가장 윗줄을 왼쪽으로 한 칸 밀기 RRW → RWR

>U'    가장 윗줄을 오른쪽으로 한 칸 밀기 RRW → WRR

>R      가장 오른쪽 줄을 위로 한 칸 밀기 WWB → WBW

>R'     가장 오른쪽 줄을 아래로 한 칸 밀기 WWB → BWW

>L      가장 왼쪽 줄을 아래로 한 칸 밀기 RGG → GRG (L의 경우 R과 방향이 반대임을 주의한다.)

>L'      가장 왼쪽 줄을 위로 한 칸 밀기 RGG → GGR

>B      가장 아랫줄을 오른쪽으로 한 칸 밀기 GBB → BGB (B의 경우도 U와 방향이 반대임을 주의한다.)

>B'      가장 아랫줄을 왼쪽으로 한 칸 밀기 GBB → BBG

>Q       Bye~를 출력하고 프로그램을 종료한다.

## 요구사항

- 처음 시작하면 초기 상태를 출력한다.
- 간단한 프롬프트를 표시해준다.
- 한 번에 여러 문자를 입력받은 경우 순서대로 처리해서 매 과정을 화면에 출력한다.

---

- FlatCube 생성과 동시에 기능이 실행되도록 하였다.
- 입력받은 값은 문자열 배열(alphaArr) 에 값을 다.

## 함수별 역할과 특징

### printCube()

- cube를 화면에 출력하는 메소드

### start()

- 사용자 입력을 받고, 변수 input에 값을 담는다.
- input의 값이 "Q" 일 경우 "Bye Bye~"를 출력하고, 함수를 종료한다.
- input의 값으로 alphArr를 생성하는 메소드 (getAlphaNum(), getAlphaArr() )를 호출한다.
- 생성된 alphArr의 값에 따라 move() 메소드를 실행한다.

### getAlphaNum()

- 입력받은 값 input 중 알파벳 "U", "L", "R", "B"  이 있는 개수를 크기로 하는 배열(alphaArr) 을 생성하기 위해 정의한 메소드
- 위의 알파벳 중 하나가 있을 경우 alphabet의 값에 1을 더한다.
- alphabet의 값을 크기로 하는 배열 alphaArr 을 생성한다.

### getAlphaArr()

- alphArr를 생성하기 위한 메소드
- for문을 사용해서 input의 모든 글자를 확인한다.
- input의 해당 글자가 " **'** " 이면 다음 글자로 넘어간다.
- input의 해당 글자가 마지막 글자가 아니고, 다음 글자에 " **'** " 이 아닐경우

    → alphaArr 의 값에 해당 글자만 넣는다.

- 마찬가지로 input의 해당 글자가 마지막 글자가 아니고, 다음 글자에 " **'** "인 경우

    → alphaArr 의 값에 해당 글자와 다음글자인 " **'** " 를 같이 넣는다.

- 마지막 글자가 " **'** " 이 아닌 알파벳인 경우에 해당 글자를 alphaArr의 값에 넣는다.

### move()

- alphaArr의 값에 해당하는 메소드(moveL(), moveR(), moveU(), moveD() 중 하나)를 호출한다.

### moveL()

- 매개변수 idx행의 값을 왼쪽으로 한 열 옮기는 메소드

### moveR()

- 매개변수 idx행의 값을 오른쪽으로 한 열 옮기는 메소드

### moveU()

- 매개변수 idx열의 값을 위로 한 행 옮기는 메소드

### moveD()

- 매개변수 idx열의 값을 아래로 한 행 옮기는 메소드
