package seol.study.deploy.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seol.study.deploy.post.dto.PostDto;
import seol.study.deploy.post.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long post(@RequestBody PostDto postDto) {
        return postService.save(postDto);
    }

}
