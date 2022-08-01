package price;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class price_info extends JFrame {
    private JLabel month;
    private JLabel l1,l2;
    private JButton b1,b2;
    private JTextField search;
    private JTextField t1,t2;
    private String monthName;

    public price_info(){
        super("price_info");
        setSize(700,400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        Container container =getContentPane();
        month = new JLabel("输入月份：",JLabel.RIGHT);
        l1 = new JLabel("当月峰价",JLabel.RIGHT);
        l2 = new JLabel("当月谷价",JLabel.RIGHT);

        b1 = new JButton("查询");
        b2 = new JButton("修改价格");

        t1 = new JTextField(200);
        t2 = new JTextField(200);
        search = new JTextField(200);

        month.setBounds(80,50,100,30);
        l1.setBounds(80,100,100,30);
        l2.setBounds(80,130,100,30);
        search.setBounds(200,50,100,25);
        b1.setBounds(370,52,60,25);
        b2.setBounds(460,52,90,25);
        t1.setBounds(200,100,350,30);
        t2.setBounds(200,130,350,30);

        ButtonActionPerformed buttoner = new ButtonActionPerformed();
        ButtonActionPerformed2 buttoner2 = new ButtonActionPerformed2();

        b1.addActionListener(buttoner);
        b2.addActionListener(buttoner2);

        container.add(b1);
        container.add(b2);
        container.add(month);
        container.add(search);
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
            monthName = search.getText();
            String dirver = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dirver);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            Statement stmt;
            ResultSet rs;
            String database = "jdbc:mysql://localhost:3306/enterprise?"
                    +"useSSL=false&serverTimezone=GMT%2B8&characterEncoding=UTF-8";
            String username = "root";
            String password = "123456";
            Connection con = null;
            try {
                con = DriverManager.getConnection(database,username,password);
                con.setAutoCommit(true);
                System.out.println("和数据库建立连接");
                stmt = (Statement) con.createStatement();
                rs = (ResultSet) stmt.executeQuery("select * from price");
                while (rs.next()){
                    if (rs.getString(1).equals(monthName)){
                        t1.setText(""+rs.getInt(2));
                        t2.setText(""+rs.getInt(3));
                    }
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class ButtonActionPerformed2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            price_update app3 = new price_update();
            app3.setLocationRelativeTo(null);
        }
    }

    public static void main(String[] args){
        price_info app = new price_info();
        app.setLocationRelativeTo(null);
    }

}
