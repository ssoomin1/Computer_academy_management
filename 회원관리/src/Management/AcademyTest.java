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
			System.out.println("��ǻ�� �п��� ���� ���� ȯ���մϴ�!");
			System.out.println("    1. �л� ���� �ý���              ");
			System.out.println("    2. �кθ� ���� �ý���              ");
			System.out.println("    3. ���α׷� ����                   ");
			System.out.println("***********************");
			int program = scan.nextInt();
			switch(program) {
			case 1 :{
				System.out.println("==================== �п� �л� ���� �ý��� =====================");
				System.out.println("==  �п� �л��� ������ �����ϴ� �ý����Դϴ�.");
				System.out.println("==  ������ ��� ����");
				System.out.println("==  101. �л� �⺻���� ���� ���α׷� (�߰�, �˻�, ����)");
				System.out.println("==  102. �л� �⼮ ó�� ���� ���α׷�");
				System.out.println("==  103. �л� ������ ���� ���� ���α׷�");
				System.out.println("==  104. �л� ��� ����  ���α׷� ");
				System.out.println("==  105. ����");
				System.out.println("==  ���ϴ� ���� �ý����� ��ȣ�� �Է����ּ���.");
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
						System.out.println("�����մϴ�.");testing();break;
					}
					default : System.out.println("�ٽ� �Է����ּ���.");testing();
				}
			}
			case 2:
			{
				System.out.println("=========================================================");
				System.out.println("�кθ��� �����͸� �����ϴ� ���Դϴ�.");
				System.out.println("==  ������ ��� ����");
				System.out.println("==  201. �кθ��� �⺻���� �߰�(�л��� �⺻���� �߰��� �߰����ֽñ� �ٶ��ϴ�.)");
				System.out.println("==  202. ����");
				System.out.println("==  ���ϴ� ���� �ý����� �Է����ּ���.");
				System.out.println("=========================================================");
				System.out.print(">>>");
				int input_n = scan.nextInt();
				switch(input_n) {
				case 201:{
					parentworking();break;
				}
				case 202:{
					System.out.println("�����մϴ�.");testing();break;
				}
				default : System.out.println("�ٽ� �Է����ּ���");testing();
				}
			}case 3:
			{
				n = 1;System.out.println("�п����� �ý����� ������ �����ϰڽ��ϴ�.");break;
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
		System.out.println("�л� �⼮ ó�� ���� �ý���");
		AttendanceCheck.attendance();
		testing();
	}
	public static void Paying() throws ClassNotFoundException {
		System.out.println("�л� ������ ���� ���� �ý���");
		Pay p1=new Pay();
		p1.PAY();
		testing();
	}
	public static void Grade() throws ClassNotFoundException {
		System.out.println("�л� ��� ���� �ý���");
		GradeManagement gmt1 = new GradeManagement();
		testing();
	}
	public static void parentworking() throws ClassNotFoundException {
		System.out.println("�кθ��� �⺻������ �߰��մϴ�.");
		Parent person1=new Parent();
		person1.Parentworking();
		testing();
	}
}
