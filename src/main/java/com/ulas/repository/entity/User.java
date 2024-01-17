package com.ulas.repository.entity;

import com.ulas.repository.enums.ERole;
import com.ulas.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "userDb")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Builder.Default
    private ERole role = ERole.USER;
    @Builder.Default
    private EStatus status=EStatus.ACTIVE;
}
