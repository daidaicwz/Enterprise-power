package electricity_consumption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class searchByMonth extends JFrame {
    private JLabel etpname ;
    private JLabel l1,l2,l3,l4;
    private JTextField search;
    private JButton b1;
    private JTextField t1,t2,t3,t4;
    private String etpname1,month;
    private int TotalCharge = 0,TotalConsumption = 0;
    private JComboBox<String> c1;
    private String[] Datalist = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Oct","Sept","Nov","Dec"};

    public searchByMonth(){
        super("按月份选择查看");
        setSize(700,400);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        Container container =getContentPane();
        etpname = new JLabel("输出入要查询的企业名：",JLabel.LEFT);
        l1 = new JLabel("本月用总用电量",JLabel.RIGHT);
        l2 = new JLabel("本月电费",JLabel.RIGHT);
        l3 = new JLabel("企业年用电",JLabel.RIGHT);
        l4 = new JLabel("企业年电费开支",JLabel.RIGHT);
        b1 = new JButton("查询");
        c1 = new JComboBox<String>(Datalist);
        t1 = new JTextField(100);
        t2 = new JTextField(100);
        t3 = new JTextField(100);
        t4 = new JTextField(100);
        search = new JTextField(100);

        etpname.setBounds(70,50,200,30);
        l1.setBounds(80,100,100,30);
        l2.setBounds(80,130,100,30);
        l3.setBounds(80,160,100,30);
        l4.setBounds(80,190,100,30);
        search.setBounds(200,50,100,25);
        b1.setBounds(460,52,60,25);
        c1.setBounds(370,52,80,25);
        t1.setBounds(200,100,350,30);
        t2.setBounds(200,130,350,30);
        t3.setBounds(200,160,350,30);
        t4.setBounds(200,190,350,30);

        ButtonActionPerformed btn = new ButtonActionPerformed();
        b1.addActionListener(btn);

        container.add(etpname);
        container.add(l1);
        container.add(l2);
        container.add(l3);
        container.add(l4);
        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(search);
        container.add(c1);
        container.add(b1);
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private class ButtonActionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            etpname1 = search.getText();
            month = (String) c1.getSelectedItem();
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
            Statement stmt;
            ResultSet rs;
            try {
                con = DriverManager.getConnection(database,username,password);
                con.setAutoCommit(true);
                System.out.println("和数据库建立连接");
                stmt = (Statement) con.createStatement();
                rs = (ResultSet) stmt.executeQuery("select TotalPower,Charge from electricity_consumption " +
                        "where Etpname='"+etpname1+"' and Month='"+month+"'");
                while (rs.next()){
                    t1.setText(""+rs.getInt(1));
                    t2.setText(""+rs.getInt(2));
                }

                rs = (ResultSet) stmt.executeQuery("select TotalPower,Charge from electricity_consumption " +
                        "where Etpname='"+etpname1+"'");
                while (rs.next()){
                    TotalConsumption = rs.getInt(1)+TotalConsumption;
                    TotalCharge = rs.getInt(2)+TotalCharge;
                }
                t3.setText(""+TotalConsumption);
                t4.setText(""+TotalCharge);

                stmt.close();
                con.close();
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        searchByMonth app = new searchByMonth();
        app.setLocationRelativeTo(null);
    }

}
