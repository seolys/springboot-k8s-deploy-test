package seol.study.deploy.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import seol.study.deploy.entity.Post;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String contents;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }
}
