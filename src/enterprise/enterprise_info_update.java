package enterprise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class enterprise_info_update extends JFrame {
    private JLabel etpname;
    private JLabel c1,c2,c3,c4,c5;
    private JTextField t1,t2,t3,t4,t5;
    private JTextField search;
    private String etpname1,s1,s2,s3,s4,s5;
    private JButton b1,b2;

    public enterprise_info_update(){
        super("price_update");
        setSize(700,400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        Container container =getContentPane();
        etpname = new JLabel("输入要修改的企业名：",JLabel.LEFT);
        c1 = new JLabel("企业编号",JLabel.RIGHT);
        c2 = new JLabel("企业名称",JLabel.RIGHT);
        c3 = new JLabel("企业地址",JLabel.RIGHT);
        c4 = new JLabel("联系电话",JLabel.RIGHT);
        c5 = new JLabel("联系人",JLabel.RIGHT);
        b1 = new JButton("修改");
        b2 = new JButton("取消");
        t1 = new JTextField(200);
        t2 = new JTextField(200);
        t3 = new JTextField(200);
        t4 = new JTextField(200);
        t5 = new JTextField(200);
        search = new JTextField(200);

        etpname.setBounds(80,50,180,30);
        c1.setBounds(80,100,100,30);
        c2.setBounds(80,130,100,30);
        c3.setBounds(80,160,100,30);
        c4.setBounds(80,190,100,30);
        c5.setBounds(80,220,100,30);
        search.setBounds(200,50,100,25);
        b1.setBounds(370,52,60,25);
        b2.setBounds(460,52,60,25);
        t1.setBounds(200,100,350,30);
        t2.setBounds(200,130,350,30);
        t3.setBounds(200,160,350,30);
        t4.setBounds(200,190,350,30);
        t5.setBounds(200,220,350,30);

        ButtonActionPerformed buttoner = new ButtonActionPerformed();
        ButtonActionPerformed2 buttoner2 = new ButtonActionPerformed2();

        b1.addActionListener(buttoner);
        b2.addActionListener(buttoner2);

        container.add(etpname);
        container.add(c1);
        container.add(c2);
        container.add(c3);
        container.add(c4);
        container.add(c5);
        container.add(search);
        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(t5);
        container.add(b1);
        container.add(b2);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private class ButtonActionPerformed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            etpname1=search.getText();
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
                con = DriverManager.getConnection(database,username,password);
                con.setAutoCommit(true);
                System.out.println("和数据库建立连接");
                pstmt = (PreparedStatement)con.prepareStatement("update enterprise_info set Num=?," +
                        "Etpname=?,Address=?,Tel=?,Contacts=? where Etpname=?");
                pstmt.setString(1,s1);
                pstmt.setString(2,s2);
                pstmt.setString(3,s3);
                pstmt.setString(4,s4);
                pstmt.setString(5,s5);
                pstmt.setString(6,etpname1);
                pstmt.executeUpdate();
                pstmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class ButtonActionPerformed2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            enterprise_info app1 = new enterprise_info();
            app1.setLocationRelativeTo(null);
        }
    }

}
