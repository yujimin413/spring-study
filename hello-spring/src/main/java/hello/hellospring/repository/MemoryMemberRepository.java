package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // tip) ctrl+space or option+enter 로 import
    private static Map<Long, Member> store = new HashMap<>();   // id, Member 저장
    private static long sequence = 0L;  // 0,1,2,,같은 key값 생성해주는 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   // 고객 아이디 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
//        return store.get(id);
        return Optional.ofNullable(store.get(id));  // store.get(id) 값이 null이어도 optional로 감싸면 클라이언트쪽에서 뭔가를 할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        // lambda
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // member의 name과 parmeter로 넘어온 name이 같으면
                .findAny(); // 하나라도 찾으면 반환

        // Q. findById()는 return할 때 optional로 감싸야 클라이언트쪽에서 어쩌구..(그리고 안감싸면 error뜸)인데
        // findByName()은 optional로 안 감싸도 되네 람다함수 써서 그런가?

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();  // store를 싹 비움
    }
}
