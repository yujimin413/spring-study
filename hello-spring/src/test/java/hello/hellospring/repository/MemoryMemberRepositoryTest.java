package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// test는 굳이 public으로 안 해도 됨
class MemoryMemberRepositoryTest {

    // 1. 테스트할 클래스의 인스턴스 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 케이스가 끝날 때마다 실행되어 store의 데이터를 지움
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // 2. @Test 어노테이션 작성 후 import
    @Test
    public void save() {
        // tip) 커서가 코드 중간에 있어도 Cmd+Shift+Enter 입력하면 개행됨
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        // Optional에서 값 꺼낼 때는 .get() 사용
        Member result = repository.findById(member.getId()).get();

        // 1. 직접 출력해서 결과 확인 -> 매번 출력값을 확인하는 것은 비효율적
//        System.out.println("result = " + (result == member));

        // 2. org.junit.jupiter.api: Assertions.assertEquals(expected, actual);
//        Assertions.assertEquals(member, result);

        // 3. org.assertj.core.api: Assertions.assertThat(actual).isEquslTo(expected);
//        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
        // tip) Assertions에서 option+Enter 쳐서 static import해주면 아래처럼 사용 가능
        assertThat(result).isEqualTo(member);

        /*
            expected: 나오기를 기대하는 값
            actual: 실제 테스트 결과로 나온 값

            Assertions.assertEquals(expected, actual); 의 예시로

            Assertions.assertEquals(8, Calc.add(3, 5)); 를 생각해보자.

            만약 Calc.add(3, 5)의 결과로 5가 나온 경우,
            "기대값이 8이지만 실제로는 5가 나왔다."라고 해석할 수 있다.
         */


    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // tip) shift+F6: 코드 복붙 후 변수명 한번에 변경하기
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}


/*
* 모든 테스트 케이스 한 번에 실행할 때 메소드별 실행 순서는 보장 되지 않음
* findAll()이 먼저 실행되고 findByName()이 실행되는 경우 findByName() 메소드에서 에러 발생
* findAll()에서 저장된 member1 객체를 findByName()에서 찾았기 때문
* 따라서 하나의 테스트 케이스가 끝나면 데이터를 clear 해줘야 함 => @AfterEach
* */

