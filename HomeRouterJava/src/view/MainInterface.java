/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author JH
 */
public class MainInterface extends javax.swing.JFrame {
    /**
     * Creates new form MainInterface
     */
    ArrayList<CLI> cliArray;
    public MainInterface() {
        initComponents();
        cliArray=new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        HostjTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PortjTextField = new javax.swing.JTextField();
        LocalHostCheckBox = new javax.swing.JCheckBox();
        TelnetPortCheckBox = new javax.swing.JCheckBox();
        ConnectButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        InterfacesjMenuItem = new javax.swing.JMenuItem();
        SwitchingjMenuItem = new javax.swing.JMenuItem();
        RoutingjMenuItem = new javax.swing.JMenuItem();
        SettingsjMenuItem = new javax.swing.JMenuItem();
        ConnectivityjMenuItem = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home Router 1.0 Beta");
        setIconImage(Toolkit.getDefaultToolkit().getImage(ConnectWindow.class.getResource("/resources/router icon.png")));

        jLabel1.setText("New Connection:");

        jLabel2.setText("Host:");

        HostjTextField.setText("10.10.0.151");

        jLabel3.setText("Port:");

        PortjTextField.setText("2001");

        LocalHostCheckBox.setText("Local Host");
        LocalHostCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalHostCheckBoxActionPerformed(evt);
            }
        });

        TelnetPortCheckBox.setText("Telnet Port");
        TelnetPortCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelnetPortCheckBoxActionPerformed(evt);
            }
        });

        ConnectButton.setText("Connect");
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HostjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PortjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ConnectButton))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TelnetPortCheckBox)
                            .addComponent(LocalHostCheckBox))))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(HostjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LocalHostCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(PortjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TelnetPortCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConnectButton)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("+", jPanel1);

        jMenu2.setText("File");

        jMenuItem9.setText("Open File");
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Save File");
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");

        InterfacesjMenuItem.setText("Interfaces");
        jMenu3.add(InterfacesjMenuItem);

        SwitchingjMenuItem.setText("Switching");
        jMenu3.add(SwitchingjMenuItem);

        RoutingjMenuItem.setText("Routing");
        jMenu3.add(RoutingjMenuItem);

        SettingsjMenuItem.setText("Settings");
        SettingsjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsjMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(SettingsjMenuItem);

        ConnectivityjMenuItem.setText("Connectivity");
        jMenu3.add(ConnectivityjMenuItem);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Help");

        jMenuItem8.setText("Commands");
        jMenu5.add(jMenuItem8);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed
        try {
            CLI cli=new CLI(HostjTextField.getText(),Integer.parseInt(PortjTextField.getText()));
            cliArray.add(cli);
            jTabbedPane1.addTab(cli.getRouterName(),cli);
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(this, "Unknown Exception");
            e.printStackTrace();
	} catch (ConnectException e) {
            JOptionPane.showMessageDialog(this, "Connection refused");
            e.printStackTrace();
	} catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Enter an IP address");
            e.printStackTrace();
	}
        
    }//GEN-LAST:event_ConnectButtonActionPerformed

    private void LocalHostCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalHostCheckBoxActionPerformed
        if(LocalHostCheckBox.isSelected()){
            HostjTextField.setText("localhost");
            HostjTextField.setEditable(false);
        }else{
            HostjTextField.setEditable(true);
            HostjTextField.setText("10.10.0.151");
        }
    }//GEN-LAST:event_LocalHostCheckBoxActionPerformed

    private void TelnetPortCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelnetPortCheckBoxActionPerformed
        if(TelnetPortCheckBox.isSelected()){
            PortjTextField.setText("23");
            PortjTextField.setEditable(false);
        }else{
            PortjTextField.setEditable(true);
            PortjTextField.setText("2001");
        }
    }//GEN-LAST:event_TelnetPortCheckBoxActionPerformed

    private void SettingsjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsjMenuItemActionPerformed
        if(jTabbedPane1.getSelectedIndex()==0){
            
        }else{
            cliArray.get(jTabbedPane1.getSelectedIndex()-1).SettingsjFrame.setVisible(true);
        }
    }//GEN-LAST:event_SettingsjMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public void startInterface() {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnectButton;
    private javax.swing.JMenuItem ConnectivityjMenuItem;
    private javax.swing.JTextField HostjTextField;
    private javax.swing.JMenuItem InterfacesjMenuItem;
    private javax.swing.JCheckBox LocalHostCheckBox;
    private javax.swing.JTextField PortjTextField;
    private javax.swing.JMenuItem RoutingjMenuItem;
    private javax.swing.JMenuItem SettingsjMenuItem;
    private javax.swing.JMenuItem SwitchingjMenuItem;
    private javax.swing.JCheckBox TelnetPortCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
