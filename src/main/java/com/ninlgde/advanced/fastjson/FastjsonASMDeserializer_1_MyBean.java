package com.ninlgde.advanced.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;

import java.lang.reflect.Type;

public class FastjsonASMDeserializer_1_MyBean extends JavaBeanDeserializer {
    
    public char[] id_asm_prefix__ = "\"id\":".toCharArray();
    public char[] name_asm_prefix__ = "\"name\":".toCharArray();
    public char[] score_asm_prefix__ = "\"score\":".toCharArray();

    public FastjsonASMDeserializer_1_MyBean(ParserConfig config, Class<?> clazz) {
        super(config, clazz);
    }

    @Override
    public Object deserialze(DefaultJSONParser parser, Type type, Object fieldName, int features) {
        JSONLexerBase lexer = (JSONLexerBase) parser.lexer;
        BeanTest.MyBean localMyBean = new BeanTest.MyBean();
        String id = lexer.scanFieldString(this.id_asm_prefix__);
        if (lexer.matchStat > 0) {
            localMyBean.id = id;
        }
        String name = lexer.scanFieldString(this.name_asm_prefix__);
        if (lexer.matchStat > 0) {
            localMyBean.name = name;
        }
        Integer score = lexer.scanFieldInt(this.score_asm_prefix__);
        if (lexer.matchStat > 0) {
            localMyBean.score = score;
        }
        return localMyBean;
    }
}
