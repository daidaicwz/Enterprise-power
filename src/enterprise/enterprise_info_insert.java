package enterprise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class enterprise_info_insert extends JFrame {
    private JLabel l1,l2,l3,l4,l5;
    private JButton b1;
    private JTextField t1,t2,t3,t4,t5;
    private String s1,s2,s3,s4,s5;
    private JLabel text;

    public enterprise_info_insert(){
        super("enterprise_info_insert");
        setSize(700,400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        Container container =getContentPane();
        text = new JLabel("输入要添加的企业信息",JLabel.LEFT);
        l1 = new JLabel("企业编号",JLabel.RIGHT);
        l2 = new JLabel("企业名称",JLabel.RIGHT);
        l3 = new JLabel("企业地址",JLabel.RIGHT);
        l4 = new JLabel("联系电话",JLabel.RIGHT);
        l5 = new JLabel("联系人",JLabel.RIGHT);
        b1 = new JButton("添加");
        t1 = new JTextField(200);
        t2 = new JTextField(200);
        t3 = new JTextField(200);
        t4 = new JTextField(200);
        t5 = new JTextField(200);

        text.setBounds(80,60,200,30);
        l1.setBounds(80,100,100,30);
        l2.setBounds(80,130,100,30);
        l3.setBounds(80,160,100,30);
        l4.setBounds(80,190,100,30);
        l5.setBounds(80,220,100,30);
        b1.setBounds(370,52,60,25);
        t1.setBounds(200,100,350,30);
        t2.setBounds(200,130,350,30);
        t3.setBounds(200,160,350,30);
        t4.setBounds(200,190,350,30);
        t5.setBounds(200,220,350,30);

        ButtonActionPerformed buttoner = new ButtonActionPerformed();

        b1.addActionListener(buttoner);

        container.add(l1);
        container.add(l2);
        container.add(l3);
        container.add(l4);
        container.add(l5);
        container.add(text);
        container.add(b1);
        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(t5);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private class ButtonActionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            s1 = t1.getText();
            s2 = t2.getText();
            s3 = t3.getText();
            s4 = t4.getText();
            s5 = t5.getText();
            String dirver = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dirver);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            String database = "jdbc:mysql://localhost:3306/enterprise?"
                    +"useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";
            String username = "root";
            String password = "123456";
            Connection con = null;
            PreparedStatement pstmt ;
            try {
                con = DriverManager.getConnection(database, username, password);
                con.setAutoCommit(true);
                System.out.println("和数据库建立连接");
                pstmt = (PreparedStatement)con.prepareStatement("INSERT INTO enterprise_info(Num,Etpname," +
                        "Address,Tel,Contacts)"+"VALUES(?,?,?,?,?)");
                pstmt.setString(1,s1);
                pstmt.setString(2,s2);
                pstmt.setString(3,s3);
                pstmt.setString(4,s4);
                pstmt.setString(5,s5);
                pstmt.executeUpdate();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        enterprise_info_insert app = new enterprise_info_insert();
        app.setLocationRelativeTo(null);
    }

}
