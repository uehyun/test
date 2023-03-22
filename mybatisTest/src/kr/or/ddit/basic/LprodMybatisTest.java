package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// myBatis를 이용하여 DB자료를 처리하는 순서 및 방법
public class LprodMybatisTest {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        // 1. myBatis의 환경 설정 파일을 읽어와서 실행한다. (mybatis-config.xml)
        
        Reader rd = null;
        SqlSessionFactory sqlSessionFactory = null;
        try {
            // 1-1. 환경 설정 파일을 읽어 올 스트림 객체를 생성한다.
            //      (이때 읽어올 환경설정 파일을 지정해 준다.)
            rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
            
            // 1-2. 환경 설정 파일을 읽어와 환경 설정을 완성한 후 SQL문을 호출해서 실행할 수 있는
            //      객체를 생성하는 SqlSessionFactory객체를 생성한다.
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
        } catch (Exception e) {
            System.out.println("myBatis 초기화 실패");
            e.printStackTrace();
        } finally {
            if(rd != null) try {rd.close();} catch (IOException e) {}
        }
        // -----------------------------------------------------------------------------------------
        
        // 2. mapper에 등록된 SQL문 중에서 실행할 SQL문을 호출해서 원하는 작업을 수행한다.
        
        SqlSession session = null;  // 실제 SQL문을 읽어와 처리하고 결과를 얻어오는 객체
        
        // SqlSession객체 ==> 실제 SQL문을 읽어와 처리하고 결과를 얻어오는 객체
        /*
        // 2-1. insert 작업
        System.out.println("insert 작업 시작..");
        
        System.out.print("Lprod_id 입력 >> ");
        int lprodId = scan.nextInt();
        
        System.out.print("Lprod_gu 입력 >> ");
        String lprodGu = scan.next();
        
        System.out.print("Lprod_nm 입력 >> ");
        String lprodNm = scan.next();
        
        // 입력한 데이터를 VO에 저장한다.
        LprodVO vo = new LprodVO();
        vo.setLprod_id(lprodId);
        vo.setLprod_gu(lprodGu);
        vo.setLprod_nm(lprodNm);
        
        try {
            // SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
            //      ==> openSession() 메서드를 이용한다.
            // 형식) SqlSessionFactory객체.openSession(논리값)
            //     ==> '논리값'이 true면 'AutoCommit'이 활성화된 상태이고,
            //     ==> '논리값'이 false면 'AutoCommit'이 비활성화된 상태 (기본값)
            session = sqlSessionFactory.openSession();
            
            // SqlSession객체변수를 이용하여 처리할 SQL문을 호출해서 실행한다.
            // 형식) session.insert("namespace속성값.id속성값", 파라미터클래스)
            //          ==> 반환값 : 작업에 성공한 레코드 수 
            int insertCnt = session.insert("lprod.insertLprod", vo);
            
            if(insertCnt>0) {
                System.out.println("insert 작업 성공");
            } else {
                System.out.println("insert 작업 실패");
            }
        } finally {
            // SqlSession을 생성할 때 AutoCommit이 비활성화된 상태일 때는 commit을 직접 실행해야 한다.
            session.commit();
            
            // 작업이 완료되면 SqlSession객체를 닫아준다.
            session.close();
        }
        */
        // ----------------------------------------------------------------------
        
        /*
        // 2-2. update 연습
        System.out.println("update 연습 시작..");
        
        System.out.print("수정할 Lprod_gu 입력 >> ");
        String lprodGu2 = scan.next();
        
        System.out.print("새로운 Lprod_id 입력 >> ");
        int lprodId2 = scan.nextInt();
        
        System.out.print("새로운 Lprod_nm 입력 >> ");
        String lprodNm2 = scan.next();
        
        // 입력 받은 데이터를 VO객체에 저장한다.
        LprodVO vo2 = new LprodVO();
        vo2.setLprod_gu(lprodGu2);
        vo2.setLprod_id(lprodId2);
        vo2.setLprod_nm(lprodNm2);
        
        try {
            // SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
            session = sqlSessionFactory.openSession();
            
            // SqlSession객체변수를 이용하여 처리할 SQL문을 호출해서 실행한다.
            // 형식) session.update("namespace속성값.id속성값", 파라미터클래스);
            //     ==> 반환값 : 작업에 성공한 레코드 수
            int updateCnt = session.update("lprod.updateLprod", vo2);
            
            if(updateCnt>0) {
                System.out.println("update 작업 성공");
            } else {
                System.out.println("update 작업 실패");
            }
        } finally {
            session.commit();
            session.close();
        }
        */
        //------------------------------------------------------------------
        
        /*
        // 2-3. delete 연습
        System.out.println("delete 연습 시작..");
        
        System.out.print("삭제할 Lprod_gu 입력 >> ");
        String lgu = scan.next();
        
        try {
            session = sqlSessionFactory.openSession();
            int deleteCnt = session.delete("lprod.deleteLprod",lgu);
            if(deleteCnt>0) {
                System.out.println("delete 작업 성공");
            } else {
                System.out.println("delete 작업 실패");
            }
        } finally {
            session.commit();
            session.close();
        }
        */
        
        // ------------------------------------------------------------------
        
        /*
        // 2-4. select 연습
        
        // 1) 처리한 결과가 여러개의 레코드일 경우
        System.out.println("select 연습 시작 (결과가 여러개일 경우)..");
         try {
             // SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
             session = sqlSessionFactory.openSession();
             
             // SqlSession객체변수를 이용하여 처리할 SQL문을 호출해서 실행한다.
             // select문의 처리 결과가 여러개일 경우에는 selectList()메서드를 사용하는데
             // 이 메서드는 여러개의 레코드 각각을 VO객체에 담은 후 이 VO객체를 List에 추가해 주는
             // 작업을 자동으로 수행한다.
             // 형식) session.selectList("namespace속성값.id속성값", 파라미터클래스)
             //     반환값 : resultType에 지정한 객체가 저장된 List객체
              
             List<LprodVO> lprodList = session.selectList("lprod.getAlllprod");
             
             System.out.println(" === 결과 출력 ===");
             for(LprodVO lp : lprodList) {
                 System.out.println("lprod_id : " + lp.getLprod_id());
                 System.out.println("lprod_gu : " + lp.getLprod_gu());
                 System.out.println("lprod_nm : " + lp.getLprod_nm());
                 System.out.println("--------------------------------------");
             }
             System.out.println("출력 끝..");
        } finally {
            session.close();
        }
        */
        
        // 2) 처리한 결과가 1개의 레코드일 경우
        System.out.println("select 작업 시작 (결과가 1개일 경우)");
        
        System.out.print("검색할 Lprod_gu 입력 >> ");
        String lgu2 = scan.next();
        
        try {
            // SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다.
            session = sqlSessionFactory.openSession();
            
            // SqlSession객체변수를 이용하여 처리할 SQL문을 호출해서 실행한다.
            // select문의 처리 결과가 1개일 경우에는 selectOne()메서드를 사용한다.
            // 형식) session.selectOn("namespace속성값.id속성값", 파라미터클래스)
            //     반환값 : resultType에 설정한 자료형으로 반환한다.
            //             검색한 자료가 없으면 null을 반환한다.
            LprodVO lvo2 = session.selectOne("lprod.getLprod", lgu2);
            
            if(lvo2 == null) {
                System.out.println("검색한 데이터가 하나도 없습니다..");
            } else {
                System.out.println("lprod_id : " + lvo2.getLprod_id());
                System.out.println("lprod_gu : " + lvo2.getLprod_gu());
                System.out.println("lprod_nm : " + lvo2.getLprod_nm());
                System.out.println("--------------------------------------");
                System.out.println("출력 끝...");
            }
        } finally {
            // SqlSession객체 닫기
            session.close();
        }
    }
}
