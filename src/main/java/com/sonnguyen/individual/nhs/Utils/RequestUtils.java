package com.sonnguyen.individual.nhs.Utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Splitter;
import org.glassfish.jaxb.core.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        BufferedReader br = null;
        Map<String, String> dataMap = null;
        try {
            InputStreamReader reader = new InputStreamReader(
                    request.getInputStream());
            br = new BufferedReader(reader);
            String data = br.readLine();

            dataMap = Splitter.on('&')
                    .trimResults()
                    .withKeyValueSeparator(
                            Splitter.on('=')
                                    .limit(2)
                                    .trimResults())
                    .split(data);
            return dataMap;
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Utils.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }

        return dataMap;
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
