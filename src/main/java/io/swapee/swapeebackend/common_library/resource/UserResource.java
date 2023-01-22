package io.swapee.swapeebackend.common_library.resource;

import lombok.*;

import java.time.LocalDateTime;


/**
 * @author Minoltan Issack on 01/03/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResource {
    private String name;
    private String email;
    private String role;
    private String type;
    private String msisdn;
    private String profileImage;
    private String uuid;
    private LocalDateTime createdAt;
    private Boolean status;
}
