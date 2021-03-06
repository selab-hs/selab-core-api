package kr.ac.hs.selab.member.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import kr.ac.hs.selab.member.domain.vo.Avatar;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Getter
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "member_email", unique = true)
    private String email;

    @Embedded
    private Password password;

    @Column(name = "member_student_id", unique = true)
    private String studentId;

    @Column(name = "member_name")
    private String name;

    @Getter
    @Column(name = "member_nickname", unique = true)
    private String nickname;

    @Column(name = "member_avatar")
    private Avatar avatar;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private Member(String email, Password password, String studentId, String name,
        String nickname, Avatar avatar) {
        this.email = email;
        this.password = password;
        this.studentId = studentId;
        this.name = name;
        this.nickname = nickname;
        this.avatar = avatar;
        this.role = Role.USER;
    }

    public String getPasswordValue() {
        return password.getPassword();
    }

    public Collection<GrantedAuthority> getAuthority() {
        return Collections
            .singletonList(role.getGrantedAuthority());
    }
}