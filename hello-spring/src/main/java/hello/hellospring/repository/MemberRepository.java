package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // tip) Option+Enter : 자동 import
    Member save(Member member); // 회원 정보 저장
    Optional<Member> findById(Long id); // id로 회원 찾기
    Optional<Member> findByName(String name);   // 이름으로 회원 찾기
    List<Member> findAll(); // 모든 멤버 리스트 반환
}
