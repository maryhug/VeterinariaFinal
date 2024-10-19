package Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Methods.Appointments.Add;
import Methods.Appointments.Delete;
import Methods.Appointments.ListAppoint;
import Methods.Appointments.Modify;
import Authentication.LoginUsers;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Appointments extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelMov;

    public Appointments() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 755, 520);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel contentPane_1 = new JPanel();
        contentPane_1.setLayout(null);
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane_1.setBounds(0, 0, 739, 481);
        contentPane.add(contentPane_1);

        JPanel panelResponse = new JPanel();
        panelResponse.setLayout(null);
        panelResponse.setBounds(240, 11, 489, 459);
        contentPane_1.add(panelResponse);

        JLabel lblCitasMedicas = new JLabel("Citas Medicas");
        lblCitasMedicas.setHorizontalAlignment(SwingConstants.CENTER);
        lblCitasMedicas.setFont(new Font("Hello Valentica", Font.PLAIN, 50));
        lblCitasMedicas.setBounds(10, 11, 469, 60);
        panelResponse.add(lblCitasMedicas);

        JPanel panel = new JPanel();
        panel.setBounds(10, 82, 469, 366);
        panelResponse.add(panel);
        panel.setLayout(null);

        JLabel lblWhatYou = new JLabel("Que desea hacer con la cita?");
        lblWhatYou.setBounds(65, 25, 162, 22);
        panel.add(lblWhatYou);

        JButton btnAgg = new JButton("Agregar");
        btnAgg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add addPanel = new Add();
                panelMov.removeAll();
                addPanel.setBounds(0, 0, panelMov.getWidth(), panelMov.getHeight());
                panelMov.add(addPanel);
                panelMov.revalidate();
                panelMov.repaint();
            }
        });
        btnAgg.setBounds(264, 25, 130, 22);
        panel.add(btnAgg);

        JButton btnMod = new JButton("Modificar");
        btnMod.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 Modify addPanel = new Modify();
                 panelMov.removeAll();
                 addPanel.setBounds(0, 0, panelMov.getWidth(), panelMov.getHeight());
                 panelMov.add(addPanel);
                 panelMov.revalidate();
                 panelMov.repaint();
        	}
        });
        btnMod.setBounds(264, 54, 130, 22);
        panel.add(btnMod);

        JButton btnList = new JButton("Listar");
        btnList.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ListAppoint addPanel = new ListAppoint();
                panelMov.removeAll();
                addPanel.setBounds(0, 0, panelMov.getWidth(), panelMov.getHeight());
                panelMov.add(addPanel);
                panelMov.revalidate();
                panelMov.repaint();
        	}
        });
        btnList.setBounds(264, 82, 130, 22);
        panel.add(btnList);

        JButton btnDelete = new JButton("Eliminar");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Delete addPanel = new Delete();
                panelMov.removeAll();
                addPanel.setBounds(0, 0, panelMov.getWidth(), panelMov.getHeight());
                panelMov.add(addPanel);
                panelMov.revalidate();
                panelMov.repaint();
        	}
        });
        btnDelete.setBounds(264, 110, 130, 22);
        panel.add(btnDelete);

        panelMov = new JPanel();
        panelMov.setBounds(10, 156, 450, 210);
        panel.add(panelMov);
        panelMov.setLayout(null);

        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(null);
        panelOptions.setBounds(10, 11, 225, 459);
        contentPane_1.add(panelOptions);

        JButton btnPetsRegistration = new JButton("Ingresar Pacientes");
        btnPetsRegistration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                PetsRegistration l = new PetsRegistration();
                l.setVisible(true);
            }
        });
        btnPetsRegistration.setBackground(UIManager.getColor("Button.background"));
        btnPetsRegistration.setBounds(0, 57, 225, 49);
        panelOptions.add(btnPetsRegistration);

        JButton btnPatientList = new JButton("Lista de Pacientes");
        btnPatientList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                PatientList lista = new PatientList();
                lista.setVisible(true);
            }
        });
        btnPatientList.setBackground(UIManager.getColor("Button.background"));
        btnPatientList.setBounds(0, 110, 225, 49);
        panelOptions.add(btnPatientList);

        JButton btnMedicalHistory = new JButton("Historial Clinico");
        btnMedicalHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MedicalHistory l = new MedicalHistory();
                l.setVisible(true);
            }
        });
        btnMedicalHistory.setBounds(0, 162, 225, 49);
        panelOptions.add(btnMedicalHistory);

        JButton btnAppointments = new JButton("Agenda de Citas");
        btnAppointments.setBackground(new Color(240, 240, 240));
        btnAppointments.setBounds(0, 213, 225, 49);
        panelOptions.add(btnAppointments);

        JButton btnSearchPatients = new JButton("Busqueda de Pacientes");
        btnSearchPatients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SearchPatients l = new SearchPatients();
                l.setVisible(true);
            }
        });
        btnSearchPatients.setBounds(0, 268, 225, 49);
        panelOptions.add(btnSearchPatients);

        JButton btnExit = new JButton("Salir del programa");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginUsers l = new LoginUsers();
                l.setVisible(true);
            }
        });
        btnExit.setBounds(0, 399, 225, 49);
        panelOptions.add(btnExit);
    }
}

