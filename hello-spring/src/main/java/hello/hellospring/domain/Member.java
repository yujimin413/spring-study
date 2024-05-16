package hello.hellospring.domain;

public class Member {

    private long id;        // 시스템에 저장되는 id (고객이 직접 정하는 아이디 아님)
    private String name;    // 고객이 직접 입력하는 이름

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
