package org.javaboy.vhr.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConvert implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        final SimpleDateFormat[] formats = {
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                new SimpleDateFormat("yyyy-MM-dd")
        };
        Date date = null;
        synchronized (formats) {
            for (SimpleDateFormat format : formats) {
                try {
                    date = format.parse(source);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return date;
    }

}
