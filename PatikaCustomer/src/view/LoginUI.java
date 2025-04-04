package view;

import business.UserController;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame{
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JTextField fld_mail;
    private JButton btn_login;
    private JLabel lbl_mail;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private UserController userController;

    public LoginUI(){

        this.userController = new UserController();

        this.add(container);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(400,400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.btn_login.addActionListener(e -> {
            JTextField[] checkList = {this.fld_mail, this.fld_password};
            if (!Helper.isEmailValid(this.fld_mail.getText())){
                Helper.showMsg("Geçerli bir eposta giriniz!");
            }else if (Helper.isFieldListEmpty(checkList)){
                Helper.showMsg("fill");
            }else {
                User user = this.userController.findByLogin(this.fld_mail.getText(), this.fld_password.getText());
                if (user == null){
                    Helper.showMsg("Girdiğiniz bilgilere göre kullanıcı bulunamadı!");
                } else {
                    this.dispose();
                    DashboardUI dashboardUI = new DashboardUI(user);
                }
            }
        });
    }
}
