package com.ssafy.iNine.Document.common.entity.api;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="api_data")
public class ApiData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;
    @JoinColumn(name = "api_docs_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Api api;
    private String title;
    private String type;
    private String detail;
    @Column(name="is_essential")
    private Boolean isEssential;
    @Column(name="is_request")
    private Boolean isRequest;
    @Column(name="is_parameter")
    private Boolean isParameter;
}
