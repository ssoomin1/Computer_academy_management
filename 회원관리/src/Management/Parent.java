//�кθ� ���� �⺻����
package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

public class Parent{
	static final String driver="com.mysql.jdbc.Driver";
	static final String url="jdbc:mysql://localhost:3306/personaldata?useTimezone=true&serverTimezone=UTC";
	static final String user="root";
	static final String pw="111111";
	private static Connection conn;

	public static void main(String[] args) throws ClassNotFoundException{
		Parentworking();
	}
	
	public static void Parentworking() throws ClassNotFoundException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,pw);
			//���� Ȯ��
			//System.out.println("connect"); 
		    
			Scanner scan=new Scanner(System.in);
			System.out.println("================��ȭ��ȣ ���� : 010-xxxx-xxxx,��¥ ����:yyyy-MM-dd====================");
			System.out.println("                                 1. �кθ� �߰�    2.���α׷� �����ϱ�                                           ");
			System.out.println("==============================================================================");
			int ChooseNum=scan.nextInt();
			//�кθ� �߰��ϱ�
			if(ChooseNum ==1) {
				
				System.out.print("�̸� : ");
                String parent_name = scan.next();
                System.out.print("��ȭ��ȣ : ");
                String parents_Pnum = scan.next();
                System.out.print("�������: ");
                String birthday=scan.next();
                System.out.print("�л����� ���� : ");
                String relation = scan.next();
                //insert information
                String sql="INSERT INTO parent_db VALUES ((SELECT (MAX(parent_id) + 1) FROM parent_db AS pdb),?,?,?,?)";
                pst=conn.prepareStatement(sql);
                pst.setString(1, parent_name);
                pst.setString(2,parents_Pnum);
                pst.setString(3, birthday);
                pst.setString(4, relation);
                
                
                
                System.out.println("==�̸�, ��ȭ��ȣ, �������, �л����� �����Դϴ�.==");
        		System.out.println(parent_name+", "+parents_Pnum+", "+birthday+", "+relation+", ");
        		System.out.print("\n");
        		System.out.print("�½��ϱ�?(y/n)>> ");
        		String answer=scan.next();
        		switch(answer) {
        		//���� �ߺ��Ǵ� ��ȭ��ȣ�� �ִٸ� �ߺ��Ǿ��ִٰ� �߰� ������ �߰�
        		case "y":pst.executeUpdate();
                System.out.println("ȸ�������� �ԷµǾ����ϴ�. ");
                System.out.println("");
                Parentworking(); break;
                //��µ� ���°� Ʋ�ȴٸ� 
        		case "n": System.out.println("������ �ٽ� �Է����ּ���."); Parentworking();break;
        		}		
			}
			else if(ChooseNum==2) {
				System.out.println("�кθ� ���� ���α׷��� �����մϴ�.");
			}
			}catch(SQLException e) {
				//e.printStackTrace();
				//System.out.println("SQL Error: "+e.getMessage());
				System.out.println("!!!�̹� �ִ� �����Դϴ�!!!");Parentworking();
			}
		    finally{
		    	//if(rs != null) {rs.close();}
		    	if(pst!=null)try {pst.close();}catch(SQLException sqle) {}
		    	if(conn!=null)try {conn.close();}catch(SQLException sqle) {}
			}
	   }
  }