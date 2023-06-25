package com.hanhae.hanhae99.certification.model.entity;

import com.hanhae.hanhae99.global.model.entity.AuditingFields;
import com.hanhae.hanhae99.certification.model.type.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column
    private String userId;
    @Column
    private String userPw;
    @Column
    private String username;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

}
