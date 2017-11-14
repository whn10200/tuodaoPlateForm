package format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tuodao.bp.utils.BigDecimalUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @description: BigDecimal输出格式转换类
 * @author: mif
 * @date: 2017/11/13
 * @time: 10:07
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public  class BigDecimalFormat extends JsonSerializer {

    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        String text = "0.00";
        BigDecimal minute = (BigDecimal) value;
        if (Objects.equals(minute, BigDecimal.ZERO)) {
            gen.writeString(text);
        }

        //分转元
        BigDecimal money = BigDecimalUtils.centToYuan(minute);
        DecimalFormat format = new DecimalFormat("#,##0.00");
        text = format.format(money);
        gen.writeString(text);
    }


}