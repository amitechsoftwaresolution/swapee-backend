package io.swapee.swapeebackend.common_library.resource;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdvertisementResource {
    private String title;
    private String description;
    private String category;
    private String image;
    private Long userId;
    private boolean isActive;
    private String createdDate;
}
