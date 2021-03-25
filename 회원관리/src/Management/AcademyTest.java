package Management;

import java.sql.SQLException;
import java.util.Scanner;

public class AcademyTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		testing();
	}
	public static void testing() throws ClassNotFoundException {
		int n = 0;
		while(n == 0) {
			Scanner scan = new Scanner(System.in);
			System.out.println(" ");
		    System.out.println("***********************");
			System.out.println("컴퓨터 학원에 오신 것을 환영합니다!");
			System.out.println("    1. 학생 관리 시스템              ");
			System.out.println("    2. 학부모 관리 시스템              ");
			System.out.println("    3. 프로그램 종료                   ");
			System.out.println("***********************");
			int program = scan.nextInt();
			switch(program) {
			case 1 :{
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
				int program2=scan.nextInt();
				switch(program2) {
					case 101:{
						personworking();break;
					}
					case 102:{
						Attendance();break;
					}
					case 103:{
						Paying();break;
					}
					case 104:{
						Grade();break;
					}
					case 105:{
						System.out.println("종료합니다.");testing();break;
					}
					default : System.out.println("다시 입력해주세요.");testing();
				}
			}
			case 2:
			{
				System.out.println("=========================================================");
				System.out.println("학부모의 데이터를 관리하는 곳입니다.");
				System.out.println("==  수행할 기능 선택");
				System.out.println("==  201. 학부모의 기본정보 추가(학생의 기본정보 추가시 추가해주시기 바랍니다.)");
				System.out.println("==  202. 종료");
				System.out.println("==  원하는 관리 시스템을 입력해주세요.");
				System.out.println("=========================================================");
				System.out.print(">>>");
				int input_n = scan.nextInt();
				switch(input_n) {
				case 201:{
					parentworking();break;
				}
				case 202:{
					System.out.println("종료합니다.");testing();break;
				}
				default : System.out.println("다시 입력해주세요");testing();
				}
			}case 3:
			{
				n = 1;System.out.println("학원관리 시스템을 완전히 종료하겠습니다.");break;
			}
			}
		}
	}
	public static void personworking() throws ClassNotFoundException  {
		Person person=new Person();
		person.working();
		testing();
	}
	public static void Attendance() throws ClassNotFoundException {
		System.out.println("학생 출석 처리 관리 시스템");
		AttendanceCheck.attendance();
		testing();
	}
	public static void Paying() throws ClassNotFoundException {
		System.out.println("학생 수강료 납부 관리 시스템");
		Pay p1=new Pay();
		p1.PAY();
		testing();
	}
	public static void Grade() throws ClassNotFoundException {
		System.out.println("학생 등급 관리 시스템");
		GradeManagement gmt1 = new GradeManagement();
		testing();
	}
	public static void parentworking() throws ClassNotFoundException {
		System.out.println("학부모의 기본정보를 추가합니다.");
		Parent person1=new Parent();
		person1.Parentworking();
		testing();
	}
}
