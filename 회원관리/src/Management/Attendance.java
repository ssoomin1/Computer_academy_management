//�⼮ Ŭ����
package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Attendance {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	public static final String USER ="root";
	public static final String PASSWD ="111111";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");	//���� ���� ��½� ����  - yyyy-MM-dd
        
        String format_time = format.format (System.currentTimeMillis());
        
        try {
        	con=DriverManager.getConnection(URL,USER,PASSWD);
        	
        	System.out.println("==============�⼮ Ȯ��==============");
        	while(true) {
            	System.out.println("�⼮�ϰ� ���� ȸ���� ��ȭ��ȣ�� �Է��ϼ���.");
            	String pnum = scan.next();   //ȸ���� ��ȭ��ȣ �Է� �ޱ�
            	
            	String sql = "select * from personal where PhoneNum = ?";  //personal ���̺�, ���ϴ� ��ȭ��ȣ�� �� ��������
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pnum);	//�Է¹��� ��ȭ��ȣ�� ���ϴ� �� ã��
				rs = pstmt.executeQuery(); 
				
				while(rs.next()) {
					String id = rs.getString("id");		//����� ȸ���� id ã��
					String Name = rs.getString("Name");	//����� ȸ���� �̸� ã��
					String PhoneNum= rs.getString("PhoneNum");	//����� ȸ���� ��ȭ��ȣ ã��
					
					System.out.print("ȸ�� ��ȣ: "+id+", �̸�: "+Name+", ��ȭ��ȣ: "+PhoneNum+" ���� �½��ϱ�?(y/n) >> ");
				}//�Է¹��� ��ȭ��ȣ�� ȸ�� ���� ��� �� ������ �´� �� Ȯ���ϱ�
            	String answer = scan.next();
            	if(answer.equals("y")) { //ȸ���� ������(answer = y) ����
            		System.out.print("�⼮�ϰڽ��ϱ�?(y/n) >> ");
            		String answer2 = scan.next();           		
            		if(answer2.equals("y")) { //�⼮�ϰڴٰ� �ϸ�(answer2 = y) ����
            			con=DriverManager.getConnection(URL,USER,PASSWD);
            			String sql1 = "update personal set attendance = ? where PhoneNum = ?"; //�Է��� ��ȭ��ȣ�� ���� ���� �⼮ ������Ʈ�ϱ�
            			pstmt = con.prepareStatement(sql1);
            			pstmt.setString(1, format_time); 	//2 ��¥�� ���� ��¥�� ������Ʈ �ϱ�
            			pstmt.setString(2, pnum); 			//1 �Է��� ��ȭ��ȣ�� ���� ����
            			
            			pstmt.executeUpdate();
            			
            			con.close();
            			System.out.println(format_time+" ���� �⼮�� �Ϸ��Ͽ����ϴ�.");System.out.println("=================================");break;
            		}else {
            			System.out.println("�⼮ üũ�� ���� �ʰڽ��ϴ�.");System.out.println("================================="); break;
            		}
            	}else {
            		System.out.println("�ٽ� �Է��ϼ���.");
            		System.out.println("=================================");
            	}
        	}
        	
        	
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