package seol.study.deploy.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seol.study.deploy.entity.Post;
import seol.study.deploy.post.dto.PostDto;
import seol.study.deploy.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostDto::new).collect(Collectors.toList());
    }

    public Long save(PostDto postDto) {
        Post post = new Post(postDto);
        return postRepository.save(post).getId();
    }
}
