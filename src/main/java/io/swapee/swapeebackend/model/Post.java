package io.swapee.swapeebackend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long categoryId;
    private Long typeId;
    private Long sellerId;
    private boolean isActive;
    private boolean isVerified;
    @Column(columnDefinition="text")
    private String imageArray;
    private LocalDateTime date;

}
