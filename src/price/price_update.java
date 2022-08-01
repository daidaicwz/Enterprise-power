package price;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class price_update extends JFrame {
    private JLabel l1,l2,monthName;
    private JButton b1,b2;
    private JTextField month;
    private JTextField t1,t2;
    private String monthName1;
    private String s1,s2;

        public price_update(){
            super("price_update");
            setSize(700,400);
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch (Exception e){}

            Container container =getContentPane();
            monthName = new JLabel("请输入要修改价格的月份",JLabel.LEFT);
            l1 = new JLabel("当月峰价",JLabel.RIGHT);
            l2 = new JLabel("当月谷价",JLabel.RIGHT);

            b1 = new JButton("修改");
            b2 = new JButton("取消");

            t1 = new JTextField(200);
            t2 = new JTextField(200);
            month = new JTextField(200);

            monthName.setBounds(80,50,180,30);
            l1.setBounds(80,100,100,30);
            l2.setBounds(80,130,100,30);
            month.setBounds(200,50,100,25);
            b1.setBounds(370,52,60,25);
            b2.setBounds(460,52,60,25);
            t1.setBounds(200,100,350,30);
            t2.setBounds(200,130,350,30);

            ButtonActionPerformed buttoner = new ButtonActionPerformed();
            ButtonActionPerformed2 buttoner2 = new ButtonActionPerformed2();


            b1.addActionListener(buttoner);
            b2.addActionListener(buttoner2);

            container.add(b1);
            container.add(b2);
            container.add(month);
            container.add(monthName);
            container.add(t1);
            container.add(t2);
            container.add(l1);
            container.add(l2);
            container.setBackground(Color.LIGHT_GRAY);
            container.setLayout(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

    private class ButtonActionPerformed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            monthName1 = month.getText();
            s1 = t1.getText();
            s2 = t2.getText();
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
                pstmt = (PreparedStatement)con.prepareStatement("update price set PeakPrice=?," +
                        "ValleyPrice=? where Month=?");
                pstmt.setString(1,s1);
                pstmt.setString(2,s2);
                pstmt.setString(3,monthName1);
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
            price_info app3 = new price_info();
            app3.setLocationRelativeTo(null);
        }
    }
}
