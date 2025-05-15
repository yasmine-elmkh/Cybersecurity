package com.revature.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HexFormat;

@Converter
public class ByteArrayConverter implements AttributeConverter<byte[], String> {
    @Override
    public String convertToDatabaseColumn(byte[] bytes) {
        return "\\x" + HexFormat.of().formatHex(bytes);
    }

    @Override
    public byte[] convertToEntityAttribute(String dbData) {
        return HexFormat.of().parseHex(dbData.substring(2));
    }
}