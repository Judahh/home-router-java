/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.ButtonGroup;

import connection.RouterHandler;
import java.awt.Component;
import util.Validation;

/**
 * 
 * @author JH
 */
public class FastEthernet extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form FastEthernet
	 */
	public FastEthernet() {
		initComponents();

		// grupos dos radiobuttons
		ButtonGroup group1 = new ButtonGroup();
		group1.add(jRadioButton10mbps);
		group1.add(jRadioButton100mbps);
		ButtonGroup group2 = new ButtonGroup();
		group2.add(FullDuplexJRadioButton);
		group2.add(HalfDuplexJRadioButton);

	}

	// numero da interface em si
	private String number;
	private RouterHandler vTelnet;

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
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
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        PortStatusjCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        BandwidthjCheckBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        MacjTextField = new javax.swing.JTextField();
        IpjTextField = new javax.swing.JTextField();
        MaskjTextField = new javax.swing.JTextField();
        TxjTextField = new javax.swing.JTextField();
        OkjButton = new javax.swing.JButton();
        CanceljButton = new javax.swing.JButton();
        jRadioButton10mbps = new javax.swing.JRadioButton();
        jRadioButton100mbps = new javax.swing.JRadioButton();
        FullDuplexJRadioButton = new javax.swing.JRadioButton();
        HalfDuplexJRadioButton = new javax.swing.JRadioButton();

        setMinimumSize(new java.awt.Dimension(190, 283));
        setName("fastEthernetJPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(190, 283));

        jLabel1.setText("Port Status:");

        PortStatusjCheckBox.setText("On");

        jLabel2.setText("Bandwidth:");

        BandwidthjCheckBox.setSelected(true);
        BandwidthjCheckBox.setText("Auto");
        BandwidthjCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BandwidthjCheckBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Duplex:");

        jLabel4.setText("MAC Address:");

        jLabel5.setText("IP Address:");

        jLabel6.setText("Subnet Mask:");

        jLabel7.setText("Tx Ring Limit:");

        OkjButton.setText("OK");
        OkjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkjButtonActionPerformed(evt);
            }
        });

        CanceljButton.setText("Cancel");

        jRadioButton10mbps.setText("10Mbps");
        jRadioButton10mbps.setEnabled(false);

        jRadioButton100mbps.setSelected(true);
        jRadioButton100mbps.setText("100Mbps");
        jRadioButton100mbps.setEnabled(false);
        jRadioButton100mbps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton100mbpsActionPerformed(evt);
            }
        });

        FullDuplexJRadioButton.setSelected(true);
        FullDuplexJRadioButton.setText("Full Duplex");

        HalfDuplexJRadioButton.setText("Half Duplex");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OkjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CanceljButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jRadioButton100mbps)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(FullDuplexJRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(HalfDuplexJRadioButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(104, 104, 104))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(MacjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))
                                            .addGap(59, 59, 59)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(BandwidthjCheckBox)
                                                .addComponent(PortStatusjCheckBox)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(TxjTextField)
                                                .addComponent(IpjTextField)
                                                .addComponent(MaskjTextField)))
                                        .addComponent(jRadioButton10mbps)))))
                        .addGap(0, 4, Short.MAX_VALUE)))
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
                    .addComponent(BandwidthjCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton10mbps)
                    .addComponent(jRadioButton100mbps))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FullDuplexJRadioButton)
                    .addComponent(HalfDuplexJRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OkjButton)
                    .addComponent(CanceljButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void OkjButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_OkjButtonActionPerformed
		Validation v = Validation.getInstance();
		boolean portStatus = PortStatusjCheckBox.isSelected();
		String bandwidth;
		if (!BandwidthjCheckBox.isSelected()) {
			if (jRadioButton10mbps.isSelected()) {
				bandwidth = new String("10");
			} else {
				bandwidth = new String("100");
			}
		} else {
			bandwidth = new String("auto");
		}

		String duplex;

		if (FullDuplexJRadioButton.isSelected()) {
			duplex = new String("full-duplex");
		} else {
			duplex = new String("half-duplex");
		}

		if ((v.validateIP(IpjTextField.getText()))
				&& (v.validateMask(MaskjTextField.getText()) && (v.validateMAC(MacjTextField.getText()) && (!TxjTextField.getText().equals(
						""))))) {
			vTelnet.setFastEthernetInterface(number, portStatus, bandwidth, duplex, MacjTextField.getText(), IpjTextField.getText(),
					MaskjTextField.getText(), TxjTextField.getText());

		}
	}// GEN-LAST:event_OkjButtonActionPerformed

	private void jRadioButton100mbpsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton100mbpsActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jRadioButton100mbpsActionPerformed

	private void BandwidthjCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BandwidthjCheckBoxActionPerformed
		if (!BandwidthjCheckBox.isSelected()) {
			jRadioButton100mbps.setEnabled(true);
			jRadioButton10mbps.setEnabled(true);
		} else {
			jRadioButton100mbps.setEnabled(false);
			jRadioButton10mbps.setEnabled(false);
		}

	}// GEN-LAST:event_BandwidthjCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox BandwidthjCheckBox;
    private javax.swing.JButton CanceljButton;
    private javax.swing.JRadioButton FullDuplexJRadioButton;
    private javax.swing.JRadioButton HalfDuplexJRadioButton;
    public javax.swing.JTextField IpjTextField;
    public javax.swing.JTextField MacjTextField;
    public javax.swing.JTextField MaskjTextField;
    private javax.swing.JButton OkjButton;
    public javax.swing.JCheckBox PortStatusjCheckBox;
    public javax.swing.JTextField TxjTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton100mbps;
    private javax.swing.JRadioButton jRadioButton10mbps;
    // End of variables declaration//GEN-END:variables
}