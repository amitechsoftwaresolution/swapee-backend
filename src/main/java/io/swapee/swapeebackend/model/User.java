package io.swapee.swapeebackend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Minoltan Issack on 8/14/2022
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
