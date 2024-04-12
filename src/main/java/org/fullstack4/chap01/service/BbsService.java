package org.fullstack4.chap01.service;
import java.util.List;

import org.fullstack4.chap01.dao.BbsDAO;
import org.fullstack4.chap01.domain.BbsVO;
import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.utils.MapperUtil;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public enum BbsService {
    INSTANCE;
    private BbsDAO dao;
    private ModelMapper modelMapper;
    BbsService() {
        dao = new BbsDAO();
        modelMapper = MapperUtil.INSTANCE.getModerMapper();
    }
    public List<BbsDTO> bbsList() throws Exception {
//        List<BbsDTO> bbsDTOS = IntStream.range(0, 10).mapToObj(i->{
//            BbsDTO dto = new BbsDTO();
//            dto.setIdx(i);
//            dto.setUser_id("webuser");
//            dto.setTitle("Bbs Title ... " + i);
//            dto.setContent("Bbs Content ... " + i);
//            dto.setDisplay_date(LocalDate.now().toString());
//            dto.setRead_cnt(0);
//
//            return dto;
//        }).collect(Collectors.toList());
        List<BbsVO> bbsVOList = dao.list();

        //순환
        List<BbsDTO> bbsDTOList = bbsVOList.stream()
                .map(vo -> modelMapper.map(vo, BbsDTO.class))
                .collect(Collectors.toList());


//                vo->{
//                    BbsDTO dto = new BbsDTO();
//                    dto.setContent(vo);
//                    ....
//                } 매퍼 안쓰면 이렇게 한땀한땀 해줘야함! 위에서처럼
//


       return bbsDTOList;
    }

    public BbsDTO view(int idx) throws Exception {
//        BbsDTO dto = new BbsDTO();
//        dto.setIdx(idx);
//        dto.setUser_id("webuser");
//        dto.setTitle("Bbs Title ... " + idx);
//        dto.setContent("Bbs Content ... " + idx);
//        dto.setDisplay_date(LocalDate.now().toString());
//        dto.setRead_cnt(0);

        BbsVO bbsVO = dao.view(idx);
        BbsDTO bbsDTO = modelMapper.map(bbsVO, BbsDTO.class); //BbsDTO의 클래스 필드에다가 매핑 시켜줘

        return bbsDTO;
    }

    public int regist(BbsDTO bbsDTO) throws Exception {
        //BbsDAO dao = new BbsDAO();
        BbsVO bbsVO = modelMapper.map(bbsDTO, BbsVO.class);
//        System.out.println("regist bbsVO : " + bbsVO.toString()); 서비스 쪽에선 로그 남기면 안돼
        return dao.regist(bbsVO);
        // 리턴값이 있다면 성공했다 안했다 처리해주면 됨!
    }

    public int modify(BbsDTO bbsDTO) throws Exception {
        //BbsDAO dao = new BbsDAO();
        BbsVO bbsVO = modelMapper.map(bbsDTO, BbsVO.class);
//        System.out.println("modify bbsVO : " + bbsVO.toString());
        return dao.modify(bbsVO);
        // 리턴값이 있다면 성공했다 안했다 처리해주면 됨!
    }

    public int delete(int idx) throws Exception {

        return dao.delete(idx);
    }
}
//배포할 때 주석은 다 없애!
//프로세스에 대한 주석만 남기기
// 이전 작업분에 대한것은 삭제하고 배포하기