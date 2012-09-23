/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.RouterHandler;

/**
 *
 * @author JH
 */
public class Serial extends javax.swing.JPanel {

    /**
     * Creates new form Serial
     */
    public Serial() {
        initComponents();
    }
    
  //numero da interface em si
    private String number;
    private RouterHandler vTelnet;
    
    public void setNumber(String number){
        this.number = number;
    }
    
    public String getNumber(){
        return this.number;
    }
    
    

    public RouterHandler getvTelnet() {
		return vTelnet;
	}

	public void setvTelnet(RouterHandler vTelnet) {
		this.vTelnet = vTelnet;
	}

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PortStatusjCheckBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        MacjTextField = new javax.swing.JTextField();
        IpjTextField = new javax.swing.JTextField();
        MaskjTextField = new javax.swing.JTextField();
        TxjTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ClockRatejComboBox = new javax.swing.JComboBox();
        CanceljButton = new javax.swing.JButton();
        OkjButton = new javax.swing.JButton();

        jLabel1.setText("Port Status:");

        jLabel4.setText("MAC Address:");

        PortStatusjCheckBox.setText("On");

        jLabel5.setText("IP Address:");

        jLabel6.setText("Subnet Mask:");

        jLabel7.setText("Tx Ring Limit:");

        jLabel2.setText("Clock Rate:");

        ClockRatejComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1200", "2400", "4800", "9600", "19200", "38400", "56000", "64000", "72000", "125000", "128000", "148000", "250000", "500000", "800000", "1000000", "1300000", "2000000", "4000000", "Not Set" }));
        ClockRatejComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClockRatejComboBoxActionPerformed(evt);
            }
        });

        CanceljButton.setText("Cancel");

        OkjButton.setText("OK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(MacjTextField))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(32, 32, 32)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(ClockRatejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(PortStatusjCheckBox))
                                            .addGap(10, 10, 10))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TxjTextField)
                                        .addComponent(IpjTextField)
                                        .addComponent(MaskjTextField))))
                            .addComponent(jLabel2))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(OkjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CanceljButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(PortStatusjCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ClockRatejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(MacjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(IpjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(MaskjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OkjButton)
                    .addComponent(CanceljButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ClockRatejComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClockRatejComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClockRatejComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CanceljButton;
    public javax.swing.JComboBox ClockRatejComboBox;
    public javax.swing.JTextField IpjTextField;
    public javax.swing.JTextField MacjTextField;
    public javax.swing.JTextField MaskjTextField;
    private javax.swing.JButton OkjButton;
    public javax.swing.JCheckBox PortStatusjCheckBox;
    public javax.swing.JTextField TxjTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
