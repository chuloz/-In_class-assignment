import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AddEmployeesGUI extends JFrame {
    private JTextField empIdField, nameEnField, nameFaField, nameJaField, ageField, salaryField;
    private JButton addButton;
    private JComboBox<String> languageComboBox;
    private JLabel empIdLabel, nameEnLabel, nameFaLabel, nameJaLabel, ageLabel, salaryLabel;
    private AddEmployees addEmployees;

    // Translation maps for each language
    private Map<String, String> labelsEn, labelsFa, labelsJa;
    private Map<String, String> currentLabels;

    public AddEmployeesGUI() {
        addEmployees = new AddEmployees();

        // Initialize translation maps
        initializeLanguageMaps();

        // Set initial language to English
        currentLabels = labelsEn;

        // Window settings
        setTitle("Add Employee");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Create GUI components
        languageComboBox = new JComboBox<>(new String[]{"English", "Farsi", "Japanese"});
        empIdField = new JTextField();
        nameEnField = new JTextField();
        nameFaField = new JTextField();
        nameJaField = new JTextField();
        ageField = new JTextField();
        salaryField = new JTextField();
        addButton = new JButton("Add Employee");

        // Initialize labels
        empIdLabel = new JLabel();
        nameEnLabel = new JLabel();
        nameFaLabel = new JLabel();
        nameJaLabel = new JLabel();
        ageLabel = new JLabel();
        salaryLabel = new JLabel();

        // Set labels based on the initial language (English)
        updateLabels();

        // Add components to window
        add(new JLabel("Select Language:"));
        add(languageComboBox);
        add(empIdLabel);
        add(empIdField);
        add(nameEnLabel);
        add(nameEnField);
        add(nameFaLabel);
        add(nameFaField);
        add(nameJaLabel);
        add(nameJaField);
        add(ageLabel);
        add(ageField);
        add(salaryLabel);
        add(salaryField);
        add(new JLabel());  // Empty cell
        add(addButton);

        // Event listener for language selection
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLanguage = (String) languageComboBox.getSelectedItem();
                switch (selectedLanguage) {
                    case "English":
                        currentLabels = labelsEn;
                        break;
                    case "Farsi":
                        currentLabels = labelsFa;
                        break;
                    case "Japanese":
                        currentLabels = labelsJa;
                        break;
                }
                updateLabels();
            }
        });

        // Add event listener to button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeToDatabase();
            }
        });

        // Close the connection when the window is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                addEmployees.closeConnection();
            }
        });
    }

    private void initializeLanguageMaps() {
        // English labels
        labelsEn = new HashMap<>();
        labelsEn.put("empId", "Employee ID:");
        labelsEn.put("nameEn", "English Name:");
        labelsEn.put("nameFa", "Farsi Name:");
        labelsEn.put("nameJa", "Japanese Name:");
        labelsEn.put("age", "Age:");
        labelsEn.put("salary", "Salary:");
        labelsEn.put("addEmployee", "Add Employee");

        // Farsi labels
        labelsFa = new HashMap<>();
        labelsFa.put("empId", "شناسه کارمند:");
        labelsFa.put("nameEn", "نام به انگلیسی:");
        labelsFa.put("nameFa", "نام به فارسی:");
        labelsFa.put("nameJa", "نام به ژاپنی:");
        labelsFa.put("age", "سن:");
        labelsFa.put("salary", "حقوق:");
        labelsFa.put("addEmployee", "اضافه کردن کارمند");

        // Japanese labels
        labelsJa = new HashMap<>();
        labelsJa.put("empId", "社員ID:");
        labelsJa.put("nameEn", "英語の名前:");
        labelsJa.put("nameFa", "ペルシャ語の名前:");
        labelsJa.put("nameJa", "日本語の名前:");
        labelsJa.put("age", "年齢:");
        labelsJa.put("salary", "給料:");
        labelsJa.put("addEmployee", "社員を追加");
    }

    private void updateLabels() {
        empIdLabel.setText(currentLabels.get("empId"));
        nameEnLabel.setText(currentLabels.get("nameEn"));
        nameFaLabel.setText(currentLabels.get("nameFa"));
        nameJaLabel.setText(currentLabels.get("nameJa"));
        ageLabel.setText(currentLabels.get("age"));
        salaryLabel.setText(currentLabels.get("salary"));
        addButton.setText(currentLabels.get("addEmployee"));
    }

    private void addEmployeeToDatabase() {
        try {
            int empId = Integer.parseInt(empIdField.getText());
            String nameEn = nameEnField.getText();
            String nameFa = nameFaField.getText();
            String nameJa = nameJaField.getText();
            int age = Integer.parseInt(ageField.getText());
            double salary = Double.parseDouble(salaryField.getText());

            addEmployees.addEmployee(empId, nameEn, nameFa, nameJa, age, salary);
            JOptionPane.showMessageDialog(this, "Employee added successfully!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddEmployeesGUI gui = new AddEmployeesGUI();
            gui.setVisible(true);
        });
    }
}
