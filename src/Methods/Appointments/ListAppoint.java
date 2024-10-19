package Methods.Appointments;
import Authentication.LoginUsers;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListAppoint extends JPanel {

	private static final long serialVersionUID = 1L;
	String username = LoginUsers.globalUsername;
	String path = "src/Utils/Appointments/appointmets" + username + ".txt";
	private static final Color BACKGROUND_COLOR = new Color(240, 245, 250);
	private static final Color HEADER_COLOR = new Color(70, 130, 180);
	private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);

	public ListAppoint() {
		setLayout(new BorderLayout(10, 10));
		setBackground(BACKGROUND_COLOR);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel titleLabel = new JLabel("Lista de Citas");
		titleLabel.setFont(TITLE_FONT);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(HEADER_COLOR);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		add(titleLabel, BorderLayout.NORTH);

		JPanel appointmentsPanel = new JPanel();
		appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));
		appointmentsPanel.setBackground(BACKGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(appointmentsPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane, BorderLayout.CENTER);

		List<String> appointments = loadAppointmentsFromFile();

		for (String appointment : appointments) {
			JPanel card = createAppointmentCard(appointment);
			appointmentsPanel.add(card);
			appointmentsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre cartas
		}

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(BACKGROUND_COLOR);
	}

	private List<String> loadAppointmentsFromFile() {
		List<String> appointments = new ArrayList<>();
		StringBuilder currentAppointment = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty()) {
					if (!currentAppointment.isEmpty()) {
						appointments.add(currentAppointment.toString().trim());
						currentAppointment.setLength(0);
					}
				} else {
					currentAppointment.append(line).append("\n");
				}
			}
			if (!currentAppointment.isEmpty()) {
				appointments.add(currentAppointment.toString().trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appointments;
	}

	private JPanel createAppointmentCard(String appointment) {
		JPanel card = new JPanel();
		card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
		card.setBorder(new LineBorder(Color.GRAY, 1, true));
		card.setPreferredSize(new Dimension(380, 100));
		card.setBackground(new Color(245, 245, 245));
		String[] lines = appointment.split("\n");
		for (String line : lines) {
			JLabel label = new JLabel(line);
			label.setFont(new Font("Arial", Font.PLAIN, 14));
			card.add(label);
		}
		return card;
	}
}

