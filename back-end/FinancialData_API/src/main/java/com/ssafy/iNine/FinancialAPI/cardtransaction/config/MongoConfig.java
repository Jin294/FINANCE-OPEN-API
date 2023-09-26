package com.ssafy.iNine.FinancialAPI.cardtransaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Collections.singletonList(new DateToTimestampConverter()));
    }

    static class DateToTimestampConverter implements Converter<Date, Timestamp> {
        @Override
        public Timestamp convert(Date source) {
            return source != null ? new Timestamp(source.getTime()) : null;
        }
    }
}
