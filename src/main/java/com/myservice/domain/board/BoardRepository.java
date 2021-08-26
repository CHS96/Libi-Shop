package com.myservice.domain.board;

import com.myservice.domain.item.Item;
import com.myservice.domain.member.Member;
import com.myservice.domain.review.ItemReview;
import com.myservice.web.paging.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public List<Board> findAllOfUser(Member user) {
        return em.createQuery("select b from Board b where b.member = :user order by b.id asc", Board.class)
                .setParameter("user", user)
                .getResultList();
    }

    public void remove(Board board) {
        em.remove(board);
    }

    public List<Board> findBoardsByPaging(int startIndex) {
        return em.createQuery("select b from Board b order by b.id asc", Board.class)
                .setFirstResult(startIndex)
                .setMaxResults(Paging.MAX_SIZE)
                .getResultList();
    }

    public Long findBoardsTotalSize() {
        return (Long) em.createQuery("select count(*) from Board b").getSingleResult();
    }
}
