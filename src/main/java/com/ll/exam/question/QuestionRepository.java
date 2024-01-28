package com.ll.exam.question;

import com.ll.exam.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer>, RepositoryUtil {
    Question findBySubject(String subject);
    Question findByContent(String content);
    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String s);

    //mariadb 쿼리 사용
    @Modifying
    @Transactional
    @Query(value ="ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)
    void truncate();


}
