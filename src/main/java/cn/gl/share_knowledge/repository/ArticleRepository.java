package cn.gl.share_knowledge.repository;

import cn.gl.share_knowledge.bean.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    List<Article> getAllByEmail(String email);

    Article findByEmailAndTitle(String email, String title);
}
