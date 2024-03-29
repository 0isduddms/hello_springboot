package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
     MemoryMemberRepository repository = new MemoryMemberRepository();

     //각 메서드가 실행되고 난 후 실행되는 어노테이션
     @AfterEach
     public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
         Member member = new Member();
         member.setName("Spring");

         repository.save(member);

         Member result = repository.findById(member.getId()).get();
         //Assertions.assertEquals(기댓값, 테스트값);
         //Assertions.assertEquals(member, result);
         assertThat(member).isEqualTo(result);
     }

     @Test
    public void findByName() {
         Member member1 = new Member();
         member1.setName("Spring1");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("Spring2");
         repository.save(member2);

         Member result = repository.findByName("Spring1").get();

         assertThat(result).isEqualTo(member1);
     }

     @Test
    public void findAll() {
         Member member1 = new Member();
         member1.setName("Spring1");
         repository.save(member1);

         Member member2 = new Member();
         member2.setName("Spring2");
         repository.save(member2);

         List<Member> result = repository.findAll();

         assertThat(result.size()).isEqualTo(2);
     }
}
