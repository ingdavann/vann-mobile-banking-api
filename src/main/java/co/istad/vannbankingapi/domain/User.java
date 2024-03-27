package co.istad.vannbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)

    private String uuid;
    @Column(nullable = false, length = 50)

    private String name;
    @Column(length = 10)

    private String gender;
    @Column(unique = true)

    private Integer oneSignalId;
    @Column(unique = true)

    private String studentIdCard;
    private Boolean isDeleted;
    private Boolean isStudent;
}
