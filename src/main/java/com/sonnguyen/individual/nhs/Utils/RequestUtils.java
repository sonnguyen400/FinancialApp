package com.sonnguyen.individual.nhs.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
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
        return objectMapper.convertValue(param, clazz);
    }
}
