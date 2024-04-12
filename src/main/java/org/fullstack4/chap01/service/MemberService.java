package org.fullstack4.chap01.service;

import org.fullstack4.chap01.dao.MemberDAO;
import org.fullstack4.chap01.domain.MemberVO;
import org.fullstack4.chap01.dto.MemberDTO;
import org.fullstack4.chap01.utils.MapperUtil;
import org.modelmapper.ModelMapper;

public enum MemberService {
    INSTANCE;
    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.getModerMapper();
    }

    public MemberDTO login(String user_id, String pwd) throws Exception {
        MemberVO vo = dao.login(user_id, pwd);
        MemberDTO dto = vo != null ? modelMapper.map(vo, MemberDTO.class) : null;

        return dto;
    }

}
