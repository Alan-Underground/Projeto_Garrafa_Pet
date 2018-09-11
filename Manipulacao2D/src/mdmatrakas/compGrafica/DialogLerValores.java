package mdmatrakas.compGrafica;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class DialogLerValores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static short RESP_OK = 1;
	public static short RESP_CANCEL = 2;
	
	private int respCode = RESP_CANCEL; 
	
	private JTextField txtFieldx;
	private JTextField txtFieldy;
	private JLabel lblLabelx;
	private JLabel lblLabely;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnCompor;
	private JRadioButton rdbtnDefinir;

	public DialogLerValores() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogLerValores.this.respCode = RESP_OK;
				DialogLerValores.this.setVisible(false);
			}
		});
		panel.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogLerValores.this.respCode = RESP_CANCEL;
				DialogLerValores.this.setVisible(false);
			}
		});
		panel.add(btnCancel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		lblLabelx = new JLabel("");
		lblLabelx.setBounds(6, 9, 233, 14);
		lblLabelx.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblLabelx);
		
		txtFieldx = new JTextField();
		txtFieldx.setBounds(249, 6, 241, 20);
		panel_1.add(txtFieldx);
		txtFieldx.setColumns(10);
		
		lblLabely = new JLabel("");
		lblLabely.setBounds(6, 35, 233, 14);
		lblLabely.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblLabely);
		
		txtFieldy = new JTextField();
		txtFieldy.setBounds(249, 32, 241, 20);
		panel_1.add(txtFieldy);
		txtFieldy.setColumns(10);
		
		rdbtnDefinir = new JRadioButton("Definir");
		rdbtnDefinir.setSelected(true);
		buttonGroup.add(rdbtnDefinir);
		rdbtnDefinir.setBounds(6, 68, 73, 23);
		panel_1.add(rdbtnDefinir);
		
		rdbtnCompor = new JRadioButton("Compor");
		buttonGroup.add(rdbtnCompor);
		rdbtnCompor.setBounds(81, 68, 73, 23);
		panel_1.add(rdbtnCompor);
		
		this.setBounds(50, 50, 500, 150);
	}
	
	public int getRespCode() {
		return respCode;
	}
	
	public void setName(String str) {
		this.setTitle(str);
	}
	public void setLabelXName(String str) {
		lblLabelx.setText(str);
	}
	public void setLabelYName(String str) {
		lblLabely.setText(str);
	}
	public float getFieldX() {
		try {
			return Float.parseFloat(txtFieldx.getText());
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	public float getFieldY() {
		try {
			return Float.parseFloat(txtFieldy.getText());
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	public boolean getDefinir() {
		return rdbtnDefinir.isSelected();
	}
}
