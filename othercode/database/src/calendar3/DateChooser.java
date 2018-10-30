package calendar3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DateChooser extends JPanel implements ActionListener,
		ChangeListener {

	private static final long serialVersionUID = 1L;
	int startYear = 1930; // Ĭ�ϡ���С����ʾ���
	int lastYear = 2050; // Ĭ�ϡ������ʾ���
	int width = 250; // ������
	int height = 250; // ����߶�
	
	Font f=new Font("����", Font.BOLD, 12);

	Color backGroundColor = Color.gray; // ��ɫ
	// ���������ɫ----------------//
	Color palletTableColor = Color.white; // �������ɫ
	Color todayBackColor = Color.orange; // ���챳��ɫ
	Color weekFontColor = Color.blue; // ��������ɫ
	Color dateFontColor = Color.black; // ��������ɫ
	Color weekendFontColor = Color.red; // ��ĩ����ɫ

	// ��������ɫ------------------//
	Color controlLineColor = new Color(217, 239, 243); // ��������ɫ217,239,243
	Color controlTextColor = Color.black; // ��������ǩ����ɫ

	Color rbFontColor = Color.white; // RoundBox����ɫ
	Color rbBorderColor = Color.red; // RoundBox�߿�ɫ
	Color rbButtonColor = Color.pink; // RoundBox��ťɫ
	Color rbBtFontColor = Color.red; // RoundBox��ť����ɫ

	JDialog dialog;
	JSpinner yearSpin;
	JSpinner monthSpin;
	JButton[][] daysButton = new JButton[6][7];
	JTextField jtf;

	public DateChooser() {
		setLayout(new BorderLayout());
		setBorder(new LineBorder(backGroundColor, 2));
		setBackground(backGroundColor);

		JPanel topYearAndMonth = createYearAndMonthPanal();
		add(topYearAndMonth, BorderLayout.NORTH);
		JPanel centerWeekAndDay = createWeekAndDayPanal();
		add(centerWeekAndDay, BorderLayout.CENTER);
		pack();

	}

	private JPanel createYearAndMonthPanal() {
		Calendar c = getCalendar();
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH) + 1;

		JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		result.setBackground(controlLineColor);

		yearSpin = new JSpinner(new SpinnerNumberModel(currentYear, startYear,
				lastYear, 1));
		yearSpin.setPreferredSize(new Dimension(65, 27));
		yearSpin.setName("Year");
		yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));
		yearSpin.addChangeListener(this);
		result.add(yearSpin);

		JLabel yearLabel = new JLabel("��");
		yearLabel.setForeground(controlTextColor);
		yearLabel.setFont(f);
		result.add(yearLabel);

		monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 12, 1));
		monthSpin.setPreferredSize(new Dimension(60, 27));
		monthSpin.setName("Month");
		monthSpin.addChangeListener(this);
		result.add(monthSpin);

		JLabel monthLabel = new JLabel("��");
		monthLabel.setForeground(controlTextColor);
		monthLabel.setFont(f);
		result.add(monthLabel);

		return result;
	}

	private JPanel createWeekAndDayPanal() {
		String colname[] = { "��", "һ", "��", "��", "��", "��", "��" };
		JPanel result = new JPanel();
		// ���ù̶����壬������û����ı�Ӱ���������
		result.setFont(new Font("����", Font.PLAIN, 12));
		result.setLayout(new GridLayout(7, 7));
		result.setBackground(Color.white);
		JLabel cell;

		for (int i = 0; i < 7; i++) {
			cell = new JLabel(colname[i],0);
			//cell.setHorizontalAlignment(JLabel.CENTER);
			if (i == 0 || i == 6)
				cell.setForeground(weekendFontColor);
			else
				cell.setForeground(weekFontColor);
			result.add(cell);
		}

		int actionCommandId = 0;
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++) {
				JButton numberButton = new JButton();
				numberButton.setBorder(null);
				numberButton.setHorizontalAlignment(SwingConstants.RIGHT);
				numberButton.setActionCommand(String.valueOf(actionCommandId));
				numberButton.addActionListener(this);
				numberButton.setBackground(palletTableColor);
				numberButton.setForeground(dateFontColor);
				if (j == 0 || j == 6)
					numberButton.setForeground(weekendFontColor);
				else
					numberButton.setForeground(dateFontColor);
				daysButton[i][j] = numberButton;
				result.add(numberButton);
				actionCommandId++;
			}

		return result;
	}
   
	private void pack(){
		dialog = new JDialog();
		dialog.setUndecorated(true);
		dialog.setModal(true);
		dialog.getContentPane().add(this, BorderLayout.CENTER);
		dialog.pack();
		dialog.setSize(width, height);
	}
	public void show(JTextField jtf) {
		this.jtf=jtf;
		dialog.setLocation(getAppropriateLocation(jtf));
		flushWeekAndDay();
		dialog.setVisible(true);
	}

	private Point getAppropriateLocation(JComponent owner) {
		return new Point(owner.getLocationOnScreen().x, owner
				.getLocationOnScreen().y
				+ owner.getHeight());
	}

	private void flushWeekAndDay() {
		Calendar c = getCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				String s = "";
				if (dayNo >= 1 && dayNo <= maxDayNo)
					s = String.valueOf(dayNo);
				daysButton[i][j].setText(s);
				daysButton[i][j].setHorizontalAlignment(JButton.CENTER);
				dayNo++;
			}
		}
		dayColorUpdate(false);
		
	}

	private void dayColorUpdate(boolean isOldDay) {
		Calendar c = getCalendar();
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, 1);
		int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);
		int i = actionCommandId / 7;
		int j = actionCommandId % 7;
		if (isOldDay)
			daysButton[i][j].setForeground(dateFontColor);
		else
			daysButton[i][j].setForeground(todayBackColor);
	}

	private Calendar getCalendar() {
		Calendar result = Calendar.getInstance();
		result.setTime(getDate());
		return result;
	}

	private Date getDate() {
		try {
			return getDefaultDateFormat().parse(jtf.getText());
		} catch (Exception e) {
			return getNowDate();
		}

	}

	public static Date getNowDate() {
		return Calendar.getInstance().getTime();
	}

	private int getSelectedYear() {
		return ((Integer) yearSpin.getValue()).intValue();
	}

	private int getSelectedMonth() {
		return ((Integer) monthSpin.getValue()).intValue();
	}

	public void stateChanged(ChangeEvent e) {

		JSpinner source = (JSpinner) e.getSource();
		Calendar c = getCalendar();

		dayColorUpdate(true);

		if (source.getName().equals("Year"))
			c.set(Calendar.YEAR, getSelectedYear());
		else
			c.set(Calendar.MONTH, getSelectedMonth() - 1);

		jtf.setText(getDefaultDateFormat().format(c.getTime()));
		flushWeekAndDay();

	}

	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().length() == 0)
			return;
		dayColorUpdate(true);
		source.setForeground(todayBackColor);
		int newDay = Integer.parseInt(source.getText());
		Calendar c = getCalendar();
		c.set(Calendar.DAY_OF_MONTH, newDay);
		jtf.setText(getDefaultDateFormat().format(c.getTime()));
		dialog.dispose();
	}

	public static SimpleDateFormat getDefaultDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
}
