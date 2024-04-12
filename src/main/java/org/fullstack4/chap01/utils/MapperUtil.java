package org.fullstack4.chap01.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public enum MapperUtil {
    INSTANCE;
    //의존성 사용 -> private 으로 변수 설정, 생성자로 객체 주입해서 사용
    private ModelMapper modelMapper;
    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE) //프라이빗 수준까지 필드들을 읽어와서
                .setMatchingStrategy(MatchingStrategies.STRICT); //동일한 수준으로 매핑 시켜줘
    }
    public ModelMapper getModerMapper() {
        return modelMapper;
    }
}
