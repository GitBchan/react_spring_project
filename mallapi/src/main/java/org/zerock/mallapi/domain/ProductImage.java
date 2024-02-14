package org.zerock.mallapi.domain;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    private String fileName;
    private int ord;
    public void setOrd(int ord) {
        this.ord = ord;
    }
}
