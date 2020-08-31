package com.suaki.functionalspring.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.StringReader;

public class XmlUtils {

    private static final JacksonXmlModule module;
    private static final ObjectMapper xmlMapper;

    static {
        module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);

        xmlMapper = new XmlMapper(module)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public static <T> T unmarshall(final byte[] bytes, final Class<T> clazz) {
        try {
            return xmlMapper.readValue(new StringReader(new String(bytes)), clazz);
        } catch (Exception ex) {
            throw new RuntimeException("Could not read value:" + ex.getMessage());
        }
    }
}