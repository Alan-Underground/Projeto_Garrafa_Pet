package mdmatrakas.compGrafica;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.SpringLayout;

import mdmatrakas.compGrafica.model.Modelo;

import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogSelecionarModelo extends JDialog {
	private static final long serialVersionUID = 1L;

	public static short RESP_OK = 1;
	public static short RESP_CANCEL = 2;
	
	private short respCode = RESP_CANCEL; 
	private JList<String> list;
	
	public DialogSelecionarModelo() {
		setTitle("Seleção de modelo");
		setResizable(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSelecionarModelo.this.setVisible(false);
				DialogSelecionarModelo.this.respCode = RESP_CANCEL;
			}
		});
		sl_panel.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnCancel, -10, SpringLayout.EAST, panel);
		panel.add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogSelecionarModelo.this.setVisible(false);
				DialogSelecionarModelo.this.respCode = RESP_OK;
			}
		});
		sl_panel.putConstraint(SpringLayout.SOUTH, btnOk, 0, SpringLayout.SOUTH, btnCancel);
		sl_panel.putConstraint(SpringLayout.EAST, btnOk, -6, SpringLayout.WEST, btnCancel);
		panel.add(btnOk);
		
		list = new JList<String>();
		list.setListData(getModels());
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(12); // exibe 12 linhas de uma vez
		list.setSelectedIndex(0);
		
		// adiciona um JScrollPane que contém JList ao frame
		JScrollPane scrool = new JScrollPane(list);
		sl_panel.putConstraint(SpringLayout.NORTH, scrool, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, scrool, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrool, -6, SpringLayout.NORTH, btnCancel);
		sl_panel.putConstraint(SpringLayout.EAST, scrool, 0, SpringLayout.EAST, btnCancel);

		panel.add(scrool);
		
//		setSize(850, 300);		
		this.setBounds(50, 50, 850, 300);
	}
	
	private String[] getModels() {
		List<Modelo> l = AppComputacaoGrafica.getApp().getDocumento().getUniverso().getModelos();
		String[] models = new String[l.size()];
		int i = 0;
		for(Modelo m : l) {
			models[i++] = m.toString();			
		}
		return models;
	}
	
	public short getRespCode() {
		return respCode;
	}
	
	public Modelo getSelectedModel() {
		List<Modelo> l = AppComputacaoGrafica.getApp().getDocumento().getUniverso().getModelos();
		return l.get(list.getSelectedIndex());
	}
}
