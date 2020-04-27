package seol.study.deploy.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seol.study.deploy.post.dto.PostDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Post {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents", length = 1000)
    private String contents;

    @Builder
    public Post(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public Post(PostDto postDto) {
        this.id = postDto.getId();
        this.title = postDto.getTitle();
        this.contents = postDto.getContents();
    }
}
