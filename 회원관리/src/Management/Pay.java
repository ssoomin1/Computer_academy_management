//����Ŭ����
package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Pay {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String URL = "jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	public static final String USER ="root";
	public static final String PASSWD ="tlstnals123!";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PAY();
	}
	public static void PAY() {
		Scanner scan = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	con=DriverManager.getConnection(URL,USER,PASSWD);
        	
        	String sql = "select p.id, p.Name, p.PhoneNum, c.classroom,c.fee,c.description FROM personal AS p JOIN curriculum AS c ON p.classroom = c.id AND p.PhoneNum=?";
        	//curriculum�� id�� personal�� classroom�� ��ġ�� ��, 
        	//personal ���̺�� curriculum ���̺��� JOIN�Ͽ� 
        	//personal ���̺��� id, name, PhoneNum��
        	//curriculum ���̺��� classroom, fee, description ����
        	
        	System.out.println("============== ������ ���� ==============");
        	System.out.print("ȸ��(�л�)���� ��ȭ��ȣ�� �Է��ϼ���.>>>");
        	String PhoneNumber=scan.next();
        	
        	pstmt = con.prepareStatement(sql);
			pstmt.setString(1, PhoneNumber); //�Է¹��� ��ȭ��ȣ�� ���ϴ� �� ã��
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				int id = rs.getInt("p.id");		//����� ȸ���� id ã��
				String Name = rs.getString("p.Name");	//����� ȸ���� �̸� ã��
				String PhoneNum = rs.getString("p.PhoneNum"); //����� ȸ���� ��ȭ��ȣ ã��

				System.out.printf("ID : %s �̸� : %s ��ȭ��ȣ : %s ���� �½��ϱ�?(y/n) >> ", id, Name, PhoneNum);
			}//�Է¹��� ��ȭ��ȣ�� ȸ�� ���� ��� �� ������ �´� �� Ȯ���ϱ�
			String answer = scan.next();
			System.out.println("");
			switch(answer) {
			case "y":	//���� �ϰڴٰ� �ϸ�(answer = y) ����

				rs = pstmt.executeQuery();
				while(rs.next()) {		//ȸ���� �����ϴ� �� �������
					String classroom = rs.getString("c.classroom");
					String fee = rs.getString("c.fee");
					String description = rs.getString("c.description");

					System.out.println("������  : " + classroom);
					System.out.println("������  : " + fee);
					System.out.println("�� ���� : " + description);
				}
				System.out.println("");
				System.out.print("�п��� �����ϰڽ��ϱ� ? (y/n) >> ");	
				String answer2 = scan.next();
				System.out.println("");
				switch(answer2) {
				case "y":	//���� �ϰڴٰ� �ϸ�(answer2 = y) ����
					String sql2 = "UPDATE personal P INNER JOIN curriculum C ON P.classroom = C.id SET P.Check_pay = ? WHERE p.PhoneNum = ? ";
					//
					pstmt = con.prepareStatement(sql2);
					SimpleDateFormat format1=new SimpleDateFormat("MM��");
					Date time=new Date();
					String time1=format1.format(time);
					String check_pay = time1+" ���� �Ϸ�";
        			pstmt.setString(1, check_pay);
        			pstmt.setString(2, PhoneNumber); 
        			System.out.println("������ �Ϸ��Ͽ����ϴ�.");
        			
        			pstmt.executeUpdate();
        			
        			con.close();
        			
				case "n" :
					System.out.println("���� �ý����� �����ϰڽ��ϴ�."); System.out.println("===================================="); break;
				default : System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				};break;
			case "n":
				System.out.println("�ٽ� �Է��Ͻʽÿ�.");break;
			default : System.out.println("�߸� �Է��ϼ̽��ϴ�."); break;
			};
        	
        }catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL Error : [" + e.getMessage()+"]");
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {} 
			//close ������ �߿��ϴ�.
			if(con!=null)try {con.close();}catch(SQLException sqle) {}
			
		}
	}
  }