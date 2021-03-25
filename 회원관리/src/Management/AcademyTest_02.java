package Management;

import java.sql.SQLException;
import java.util.Scanner;
public class AcademyTest_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testing();
	}
	public static void testing() {
		Scanner scan=new Scanner(System.in);
		System.out.println("***********************");
		System.out.println("컴퓨터 학원에 오신 것을 환영합니다!");
		System.out.println("    1. 학생 관리 시스템              ");
		System.out.println("    2. 학부모 관리 시스템              ");
		System.out.println("    3. 프로그램 종료                   ");
		System.out.println("***********************");
		int program=scan.nextInt();
		if(program==1) {
			//학생관리
			System.out.println("==================== 학원 학생 관리 시스템 =====================");
			System.out.println("==  학원 학생의 정보를 관리하는 시스템입니다.");
			System.out.println("==  수행할 기능 선택");
			System.out.println("==  101. 학생 기본정보 관리 프로그램 (추가, 검색, 수정)");
			System.out.println("==  102. 학생 출석 처리 관리 프로그램");
			System.out.println("==  103. 학생 수강료 납부 관리 프로그램");
			System.out.println("==  104. 학생 등급 산출  프로그램 ");
			System.out.println("==  105. 종료");
			System.out.println("==  원하는 관리 시스템의 번호를 입력해주세요.");
			System.out.println("=========================================================");
			System.out.print(">>>");
			int Choose=scan.nextInt();
			if(Choose==101) {
				
			}else if(Choose==102) {
				
			}else if(Choose==103) {
				
			}else if(Choose == 104) {
				
			}else {
				//Choose == 105 -> 다시 초기화면으로
				testing();
			}
		}else if(program==2) {
			//학부모
			System.out.println("=========================================================");
			System.out.println("학부모의 데이터를 관리하는 곳입니다.");
			System.out.println("==  수행할 기능 선택");
			System.out.println("==  201. 학부모의 기본정보 추가(학생의 기본정보 추가시 추가해주시기 바랍니다.)");
			System.out.println("==  202. 종료");
			System.out.println("==  원하는 관리 시스템을 입력해주세요.");
			System.out.println("=========================================================");
			System.out.print(">>>");
			int ch=scan.nextInt();
			if(ch==201) {
				
			}else {
				//ch==202
				System.out.println("종료합니다.");
				testing();
			}
			
		}else {
			//program==3
			System.out.println("학원 관리 시스템을 완전히 종료하겠습니다.");
		}	
	}
	
	//102 학생출석처리
	public static void Attendance() {
		System.out.println("학생 출석 처리 관리 시스템");
		AttendanceCheck.attendance();
		testing();
	}
	
	//103 수강료 납부
	
	//104
	public static void Grade() {
		
	}
		

}


/*if(answer.equals("y")) {
	System.out.println("수정하고 싶은 항목이 무엇인가요?");
	System.out.println("1. 학생 정보 \t 2.학부모 정보");
	int i=scan.nextInt();
	switch (i){
	case 1: System.out.println("수정하고 싶은 항목이 무엇인가요?");
	}
	System.out.println("1.학생 전화번호  \t 2.학부모 전화번호 \t 3.수강과목 \t 4.현재 상태 \t");
	System.out.print(">>>");
	int ChooseN=scan.nextInt();

	switch(ChooseN) {
	case 1:
		System.out.print("수정할 전화번호를 입력하세요: ");
		String SPhoneNum=scan.next(); //학생 전화번호 입력 받음
		String sql2="UPDATE personal SET PhoneNum='"+SPhoneNum+"' WHERE id="+s_id+";";
		pst=conn.prepareStatement(sql2);
		
		System.out.println(SPhoneNum+" 이(가) 맞나요?? (y/n) >>");
		String Answer=scan.next();
		if(Answer.equals("y")){
			System.out.println("수정이 완료되었습니다.");
			pst.executeUpdate();
			conn.close();
		}else {
			System.out.println("다시 입력해주세요.");working();
		}
		break;
	case 2:
		System.out.print("수정할 학부모 전화번호를 입력하세요: ");
		String P_PhoneNum=scan.next(); //학부모 전화번호 입력받기
		String sql3="UPDATE personal SET parentPN='"+P_PhoneNum+"' WHERE id="+s_id+";";
		pst=conn.prepareStatement(sql3);
		
		System.out.println(P_PhoneNum+" 이(가) 맞나요?? (y/n) >>");
		String ANswer=scan.next();
		if(ANswer.equals("y")){
			System.out.println("수정이 완료되었습니다.");
			pst.executeUpdate();
			conn.close();
		}else {
			System.out.println("다시 입력해주세요.");working();
		}
		break;
	case 3:
		System.out.print("수정할 과목을 입력하세요: ");
		int classroom=scan.nextInt(); //과목 입력받기
		String sql4="UPDATE personal SET classroom='"+classroom+"' WHERE id="+s_id+";";
		pst=conn.prepareStatement(sql4);
		
		System.out.println(classroom+" 이(가) 맞나요?? (y/n) >>");
		String ANSwer=scan.next();
		if(ANSwer.equals("y")){
			System.out.println("수정이 완료되었습니다.");
			pst.executeUpdate();
			conn.close();
		}else {
			System.out.println("다시 입력해주세요.");working();
		}
		break;
		
	case 4: 
		System.out.println("참고 - 현재 상태");
        String consql="SELECT * FROM conditions";
        pst=conn.prepareStatement(consql);
        rs=pst.executeQuery();
        while(rs.next()) {
        	int CNUM=rs.getInt("CNUM");
        	String CurrentS=rs.getString("CurrentS");
        	System.out.println("CNUM : "+CNUM+", 상태:"+CurrentS);
        }System.out.println("");
		System.out.print("수정할 학생의 현재 상태를 입력하세요: ");
		int s_condition=scan.nextInt();
		//String sql5="UPEATE personal SET condition_now="+s_condition+" WHERE id="+s_id+";";
		String sql5="UPDATE personal SET condition_now = ? WHERE id = ?";
		pst=conn.prepareStatement(sql5);
		pst.setInt(1, s_condition);
		pst.setInt(2, s_id);
		
		System.out.println(s_condition+" 이(가) 맞나요?? (y/n) >>");
		String ANSWER=scan.next();
		if(ANSWER.equals("y")){
			pst.executeUpdate();
			conn.close();
			System.out.println("수정이 완료되었습니다.");
		}else {
			System.out.println("다시 입력해주세요.");working();
		}
		break;
	}
	working();
}
}*/
