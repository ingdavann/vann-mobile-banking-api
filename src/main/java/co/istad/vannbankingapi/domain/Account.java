package co.istad.vannbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 9)
    private String actNo;

    @Column(nullable = true, length = 100)
    private String actName;

    @Column(length = 100)
    private String alias;

    @Column(nullable = false)
    private BigDecimal balance;

    private BigDecimal transferLimit;

    // Account has a type
    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<UserAccount> userAccountList;

    @OneToOne
    private Card card;

    private Boolean isHidden; // uses to hide account on mobile app

}
