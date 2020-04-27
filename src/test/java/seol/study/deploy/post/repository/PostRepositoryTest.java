package seol.study.deploy.post.repository;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import seol.study.deploy.entity.Post;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void after() {
        postRepository.deleteAll();
    }

    @Test @Ignore
    public void post등록() {
        Post post = new Post().builder()
                .title("title11")
                .contents("한글테스트")
                .build();
        Post savePost = postRepository.save(post);
        Post findPost = postRepository.findById(savePost.getId()).orElseThrow(IllegalArgumentException::new);

        assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(findPost.getContents()).isEqualTo(post.getContents());
    }

}