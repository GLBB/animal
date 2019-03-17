package cn.gl.share_knowledge.repository;

import cn.gl.share_knowledge.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findUsersByEmail(String email);
}
