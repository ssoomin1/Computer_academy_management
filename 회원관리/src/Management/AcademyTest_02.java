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
		System.out.println("��ǻ�� �п��� ���� ���� ȯ���մϴ�!");
		System.out.println("    1. �л� ���� �ý���              ");
		System.out.println("    2. �кθ� ���� �ý���              ");
		System.out.println("    3. ���α׷� ����                   ");
		System.out.println("***********************");
		int program=scan.nextInt();
		if(program==1) {
			//�л�����
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
			System.out.print(">>>");
			int Choose=scan.nextInt();
			if(Choose==101) {
				
			}else if(Choose==102) {
				
			}else if(Choose==103) {
				
			}else if(Choose == 104) {
				
			}else {
				//Choose == 105 -> �ٽ� �ʱ�ȭ������
				testing();
			}
		}else if(program==2) {
			//�кθ�
			System.out.println("=========================================================");
			System.out.println("�кθ��� �����͸� �����ϴ� ���Դϴ�.");
			System.out.println("==  ������ ��� ����");
			System.out.println("==  201. �кθ��� �⺻���� �߰�(�л��� �⺻���� �߰��� �߰����ֽñ� �ٶ��ϴ�.)");
			System.out.println("==  202. ����");
			System.out.println("==  ���ϴ� ���� �ý����� �Է����ּ���.");
			System.out.println("=========================================================");
			System.out.print(">>>");
			int ch=scan.nextInt();
			if(ch==201) {
				
			}else {
				//ch==202
				System.out.println("�����մϴ�.");
				testing();
			}
			
		}else {
			//program==3
			System.out.println("�п� ���� �ý����� ������ �����ϰڽ��ϴ�.");
		}	
	}
	
	//102 �л��⼮ó��
	public static void Attendance() {
		System.out.println("�л� �⼮ ó�� ���� �ý���");
		AttendanceCheck.attendance();
		testing();
	}
	
	//103 ������ ����
	
	//104
	public static void Grade() {
		
	}
		

}


/*if(answer.equals("y")) {
	System.out.println("�����ϰ� ���� �׸��� �����ΰ���?");
	System.out.println("1. �л� ���� \t 2.�кθ� ����");
	int i=scan.nextInt();
	switch (i){
	case 1: System.out.println("�����ϰ� ���� �׸��� �����ΰ���?");
	}
	System.out.println("1.�л� ��ȭ��ȣ  \t 2.�кθ� ��ȭ��ȣ \t 3.�������� \t 4.���� ���� \t");
	System.out.print(">>>");
	int ChooseN=scan.nextInt();

	switch(ChooseN) {
	case 1:
		System.out.print("������ ��ȭ��ȣ�� �Է��ϼ���: ");
		String SPhoneNum=scan.next(); //�л� ��ȭ��ȣ �Է� ����
		String sql2="UPDATE personal SET PhoneNum='"+SPhoneNum+"' WHERE id="+s_id+";";
		pst=conn.prepareStatement(sql2);
		
		System.out.println(SPhoneNum+" ��(��) �³���?? (y/n) >>");
		String Answer=scan.next();
		if(Answer.equals("y")){
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			pst.executeUpdate();
			conn.close();
		}else {
			System.out.println("�ٽ� �Է����ּ���.");working();
		}
		break;
	case 2:
		System.out.print("������ �кθ� ��ȭ��ȣ�� �Է��ϼ���: ");
		String P_PhoneNum=scan.next(); //�кθ� ��ȭ��ȣ �Է¹ޱ�
		String sql3="UPDATE personal SET parentPN='"+P_PhoneNum+"' WHERE id="+s_id+";";
		pst=conn.prepareStatement(sql3);
		
		System.out.println(P_PhoneNum+" ��(��) �³���?? (y/n) >>");
		String ANswer=scan.next();
		if(ANswer.equals("y")){
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			pst.executeUpdate();
			conn.close();
		}else {
			System.out.println("�ٽ� �Է����ּ���.");working();
		}
		break;
	case 3:
		System.out.print("������ ������ �Է��ϼ���: ");
		int classroom=scan.nextInt(); //���� �Է¹ޱ�
		String sql4="UPDATE personal SET classroom='"+classroom+"' WHERE id="+s_id+";";
		pst=conn.prepareStatement(sql4);
		
		System.out.println(classroom+" ��(��) �³���?? (y/n) >>");
		String ANSwer=scan.next();
		if(ANSwer.equals("y")){
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			pst.executeUpdate();
			conn.close();
		}else {
			System.out.println("�ٽ� �Է����ּ���.");working();
		}
		break;
		
	case 4: 
		System.out.println("���� - ���� ����");
        String consql="SELECT * FROM conditions";
        pst=conn.prepareStatement(consql);
        rs=pst.executeQuery();
        while(rs.next()) {
        	int CNUM=rs.getInt("CNUM");
        	String CurrentS=rs.getString("CurrentS");
        	System.out.println("CNUM : "+CNUM+", ����:"+CurrentS);
        }System.out.println("");
		System.out.print("������ �л��� ���� ���¸� �Է��ϼ���: ");
		int s_condition=scan.nextInt();
		//String sql5="UPEATE personal SET condition_now="+s_condition+" WHERE id="+s_id+";";
		String sql5="UPDATE personal SET condition_now = ? WHERE id = ?";
		pst=conn.prepareStatement(sql5);
		pst.setInt(1, s_condition);
		pst.setInt(2, s_id);
		
		System.out.println(s_condition+" ��(��) �³���?? (y/n) >>");
		String ANSWER=scan.next();
		if(ANSWER.equals("y")){
			pst.executeUpdate();
			conn.close();
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
		}else {
			System.out.println("�ٽ� �Է����ּ���.");working();
		}
		break;
	}
	working();
}
}*/
