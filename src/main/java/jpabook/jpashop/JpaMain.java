package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.*;

public class JpaMain {
    public static void main(String[] args) {

        //Application 로딩 시점에 딱 하나만 존재해야함.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션의 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            member.setName("Hello");
            member.setAddress(new Address("city","street","08888"));
            em.persist(member);


            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("정동인");
            em.persist(book);
            
            em.flush();
            em.clear();
            
            //쿼리 실행 시점

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            System.out.println("findMember's get full address : " + findMember.getAddress().getFullAddress());

            

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}
