package enterprise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class enterprise_info_delete extends JFrame {
        private JLabel etpName;
        private JButton b1;
        private JTextField t1;
        private String etpName1;

        public enterprise_info_delete(){
            super("enterprise_info_delete");
            setSize(700,400);
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch (Exception e){}

            Container container =getContentPane();
            etpName = new JLabel("输入要删除的企业名：");
            b1 = new JButton("删除");
            t1 = new JTextField(200);

            etpName.setBounds(200,50,180,30);
            b1.setBounds(290,175,60,25);
            t1.setBounds(320,50,100,25);

            ButtonActionPerformed buttoner = new ButtonActionPerformed();
            b1.addActionListener(buttoner);

            container.add(etpName);
            container.add(b1);
            container.add(t1);
            container.setBackground(Color.LIGHT_GRAY);
            container.setLayout(null);

            setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }

    private class ButtonActionPerformed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            etpName1 = t1.getText();
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
            Statement stmt ;
            try {
                con = DriverManager.getConnection(database,username,password);
                con.setAutoCommit(true);
                System.out.println("和数据库建立连接");
                stmt = (Statement) con.createStatement();
                stmt.executeUpdate("delete from enterprise_info where Etpname='"+etpName1+"'");
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        enterprise_info_delete app = new enterprise_info_delete();
        app.setLocationRelativeTo(null);
    }

}
