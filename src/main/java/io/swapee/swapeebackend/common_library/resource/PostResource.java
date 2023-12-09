package io.swapee.swapeebackend.common_library.resource;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostResource {
    private String title;
    private String description;
    private Long categoryId;
    private Long typeId;
    private Long sellerId;
    private boolean isActive;
    private boolean isVerified;
    @Column(columnDefinition="text")
    private String imageArray;
    private LocalDateTime postDate;
}
