package com.sonnguyen.individual.nhs.Utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestUtils {
    public static final String ERROR_MESSAGE = "error_message";
    public static <T> T parseEntity(HttpServletRequest request, Class<T> clazz) {
        Map<String,Object> param=request.getParameterMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, object->{
                    if(object.getValue().length==1) return object.getValue()[0];
                    else if(object.getValue().length==0) return null;
                    return object.getValue();
                }));
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().parseLenient()
                .parseDefaulting(ChronoField.YEAR_OF_ERA, 2016L)
                .appendPattern("[yyyy-MM-dd]")
                .appendPattern("[M/dd/yyyy]")
                .appendPattern("[M/d/yyyy]")
                .appendPattern("[MM/dd/yyyy]")
                .appendPattern("[MMM dd yyyy]");
        objectMapper.registerModule(new JavaTimeModule()) ;
        return objectMapper.convertValue(param, clazz);
    }
    public enum Flags{
        CREATE_LOAN("CREATE_LOAN"),
        CREATE_SAVINGS("CREATE_SAVINGS"),
        CREATE_ACCOUNT("CREATE_ACCOUNT"),
        CREATE_TRANSFER("TRANSFER");
        public String value;
        Flags(String value) {
            this.value = value;
        }
    }
}
