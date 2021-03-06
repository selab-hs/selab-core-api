package kr.ac.hs.selab.core_qa.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoreQa extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "core_qa_id", nullable = false)
    private Long id;

    @Column(name = "core_qa_title")
    private String title;

    @Column(name = "core_qa_content")
    private String content;

    @Column(name = "member_id")
    private Long memberId;

    @Builder
    public CoreQa(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}