package seol.study.deploy.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seol.study.deploy.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
