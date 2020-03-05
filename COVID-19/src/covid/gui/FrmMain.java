package covid.gui;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import covid.main.COVID;
import covid.main.COVID_DATA;
import covid.main.COVID_TIMER;

@SuppressWarnings({ "serial" })
public class FrmMain extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JSpinner spinner;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton radioButton;
	private JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
				            UIManager.getSystemLookAndFeelClassName());
					FrmMain frame = new FrmMain();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmMain() {
		Logger logger = new Logger();		
		logger.setGUI(this);	
		COVID_DATA cd = new COVID_DATA();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("업데이트 시작");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cd.SetAlertStat();
				cd.SetSoundtStat();
				cd.SetDebugStat();
				new COVID_TIMER().schedulePrune((Integer)spinner.getValue());
				new Logger().log_Info("Timer Started!");
				}
		});
		btnNewButton.setBounds(546, 238, 136, 50);
		contentPane.add(btnNewButton);
		
		label = new JLabel("<실시간 지역별 확진자>");
		label.setBounds(33, 57, 305, 82);
		contentPane.add(label);
		
		radioButton = new JRadioButton("실시간 알림");
		radioButton.setBounds(546, 132, 121, 23);
		radioButton.setSelected(true);
		contentPane.add(radioButton);
		
		spinner = new JSpinner(new SpinnerListModel(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 30, 60}));
		spinner.setBounds(626, 206, 40, 22);
		spinner.setValue(3);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("업데이트 주기");
		lblNewLabel_1.setBounds(546, 205, 79, 23);
		contentPane.add(lblNewLabel_1);
		
		JButton button = new JButton("수동 업데이트");
		button.setBounds(546, 298, 136, 50);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cd.SetAlertStat();
				cd.SetSoundtStat();
				cd.SetDebugStat();
				logger.log_Info("수동으로 업데이트를 진행합니다.");
				try {
					new COVID().updateCOVID(true);
				} catch (InterruptedException | ExecutionException e) {
					logger.log_Warn("업데이트중 오류가 발생하였습니다.");
				}
			}
		});
		contentPane.add(button);
		
		JLabel lblNewLabel_2 = new JLabel("분");
		lblNewLabel_2.setBounds(670, 205, 12, 22);
		contentPane.add(lblNewLabel_2);
		
		rdbtnNewRadioButton = new JRadioButton("알림 효과음");
		rdbtnNewRadioButton.setBounds(546, 107, 121, 23);
		rdbtnNewRadioButton.setSelected(true);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_3 = new JLabel("COVID-INFO v1.0");
		lblNewLabel_3.setBounds(546, 403, 97, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ⓒ 2020. TH all rights reserved.");
		lblNewLabel_4.setBounds(546, 418, 181, 33);
		contentPane.add(lblNewLabel_4);
		
		rdbtnNewRadioButton_1 = new JRadioButton("디버그 모드 (개발자용)");
		rdbtnNewRadioButton_1.setBounds(546, 157, 181, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		textField_4 = new JTextField();
		textField_4.setBorder(null);
		textField_4.setBounds(546, 358, 150, 21);
		textField_4.setText(new COVID_DATA().getLastUpdate());
		textField_4.setEditable(false);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBorder(null);
		textField_5.setOpaque(false);
		textField_5.setBounds(83, 11, 384, 69);
		textField_5.setText("COVID-INFO v1.0");
		textField_5.setFont(new Font("COVID-INFO v1.0", Font.HANGING_BASELINE, 30));
		textField_5.setEditable(false);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 79, 80);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/covid/resources/info.png")).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));	
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(23, 389, 499, 37);	
		lblNewLabel_5.setIcon(new ImageIcon(this.getClass().getResource("/covid/resources/sub_1.png")));
		lblNewLabel_5.addMouseListener(new MouseAdapter() {         
			@Override
            public void mouseReleased(MouseEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("http://ncov.mohw.go.kr/index_main.jsp"));
					} catch (IOException | URISyntaxException e1) {
						new Logger().log_Warn("브라우저를 열 수 없습니다.");
					}
				}
            	
            }
        });
		contentPane.add(lblNewLabel_5);
		
		textField_6 = new JTextField();
		textField_6.setOpaque(false);
		textField_6.setBorder(null);
		textField_6.setText("(클릭시 이동)");
		textField_6.setBounds(431, 374, 79, 74);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 132, 364, 236);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(367, 11, 337, 93);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 17, 325, 69);
		panel.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JLabel lblAa = new JLabel("※공식 자료가 16시에 업데이트 됨에 따라, 16시에 업데이트 됩니다.");
		lblAa.setBounds(33, 111, 407, 15);
		contentPane.add(lblAa);
		
		textField = new JTextField();
		textField.setOpaque(false);
		textField.setBorder(null);
		textField.setBounds(83, 66, 272, 14);
		textField.setText("업데이트 필요");
		contentPane.add(textField);
		textField.setColumns(10);
	
		
	}
	public Boolean getAlarmStat() {
		return radioButton.isSelected();
	}
	public Boolean getSoundStat() {
		return rdbtnNewRadioButton.isSelected();
	}
	public Boolean getDebugStat() {
		return rdbtnNewRadioButton_1.isSelected();
	}
	public void updateDisplay(String value) {
		textArea.setText(value);
	}
	
	public void updateTime(String value) {
		textField_4.setText(value);
	}
	
	public void updateNum(String value) {
		textField.setText(value);
	}
	
	public void updateLogDisplay(String value) {
		textArea_1.append(value);
	}
}
