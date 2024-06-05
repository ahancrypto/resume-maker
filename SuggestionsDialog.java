package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SuggestionsDialog extends JDialog {

    private ArrayList<String> suggestions;
    private JList<String> suggestionsList;

    public SuggestionsDialog(Frame parent, ArrayList<String> suggestions) {
        super(parent, "Username Suggestions", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        this.suggestions = suggestions;

        // Initialize components
        suggestionsList = new JList<>(suggestions.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(suggestionsList);

        // Layout components
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        panel.add(selectButton, BorderLayout.SOUTH);

        add(panel);
        pack();
        setVisible(true);
    }

    public String getSelectedUsername() {
        return suggestionsList.getSelectedValue();
    }
}
