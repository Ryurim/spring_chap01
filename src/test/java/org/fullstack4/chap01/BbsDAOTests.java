package org.fullstack4.chap01;

import org.fullstack4.chap01.dao.BbsDAO;
import org.fullstack4.chap01.domain.BbsVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class BbsDAOTests {
    private BbsDAO dao;

    @BeforeEach
    public void ready() {
        //test 클래스를 구동하기 전에 반드시 선 실행되어 있어야 하는 놈들이 필요하다! 할 때 사용
        dao = new BbsDAO();
    }

    // test 객체는 무조건 @Test 사용하고, 무조건 public void, 매개변수 없음!

    @Test
    public void testGetTime() throws Exception{
        System.out.println("BbsDAO.getTime() : " + dao.getTime());
    }

    @Test
    public void testGetTime2() throws Exception {
        System.out.println("BbsDAO.getTime2() : " + dao.getTime2());
    }

    //등록 테스트
    @Test
    public void testRegist() throws Exception {
        //vo.set어쩌구 한 다음에 return vo 한거랑 똑같애
        BbsVO vo = BbsVO.builder()
                        .user_id("test")
                        .title("게시글 타이틀 테스트")
                        .content("게시글 내용 테스트")
                        .display_date(LocalDate.now().toString())
                        .read_cnt(0)
                        .build();
        dao.regist(vo);
    }

    //리스트 테스트
    @Test
    public void testList() throws Exception{
        List<BbsVO> list = dao.list();
        list.forEach(vo->System.out.println("vo : " + vo));
    }

    //상세조회 테스트
    @Test
    public void testView() throws Exception {
        int idx = 1;
        BbsVO vo = dao.view(idx); // 내가 임의로 idx 값 넣는거야
        // 서비스 객체에서는 정상적으로 동작이 된다는 가정 하에 하는거임
        // 서비스 객체로 가기 전에 컨트롤러에서 밸리데이터 체크 등, 정상 구동 안되는 것들 처리해주는 것임!
        System.out.println("vo : " + vo);
        //BbsVO 에서 @ToString 해줘서 잘 나오는것임!
    }

    //수정 테스트
    @Test
    public void testModify() throws Exception {
        BbsVO vo = BbsVO.builder()
                .idx(1)
                .user_id("test")
                .title("게시글 타이틀 테스트 수정")
                .content("게시글 내용 테스트 수정")
                .display_date(LocalDate.now().toString())
                .read_cnt(0)
                .build();
        dao.modify(vo);
    }

    //삭제 테스트
    @Test
    public void testDelete() throws Exception {
        int idx = 1;
        dao.delete(idx);
    }
}
