package com.ssafy.iNine.StockAPI.key;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class TransactionRecordKey implements Serializable {
    private String recordId;
    private LocalDateTime tradedAt;

    // getters, setters, equals(), hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRecordKey that = (TransactionRecordKey) o;
        return Objects.equals(recordId, that.recordId) && Objects.equals(tradedAt, that.tradedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, tradedAt);
    }
}
