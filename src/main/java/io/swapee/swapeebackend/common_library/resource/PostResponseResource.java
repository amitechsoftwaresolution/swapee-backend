package io.swapee.swapeebackend.common_library.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostResponseResource {
    private String title;
    private String description;
    @JsonProperty("category_type")
    private String categoryType;
    @JsonProperty("post_date")
    private LocalDateTime postDate;
    @JsonProperty("image_links")
    private List<String> imageLinks;
}
