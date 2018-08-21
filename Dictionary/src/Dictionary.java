import javax.swing.*;
import java.awt.*;

public class Dictionary extends JFrame {

    private JTextArea descriptionTextArea;
    private JTextField wordTextField;

    JButton bFind = new JButton();
    JButton bMore = new JButton();

    private final String form_title = "Словарь";
    private final int form_loc_x = 100;
    private final int form_loc_y = 100;
    private final int form_width = 400;
    private final int form_height = 400;
    public int count = 0;
    public boolean b = false;
    public boolean bm = false; // несколько булевых переменных для логики работы кнопок и сообщений
    public boolean s = true;

    public static boolean isOnlyRussianLetters(String str) {
        return str.matches("[а-яА-Я]+");
    }

    public void buttonFindHandler() throws Exception {

        if(isOnlyRussianLetters(wordTextField.getText())) {

            count = 1;
            String word = wordTextField.getText().trim();
            if (word.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Введите слово!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                b = true;
                wordTextField.requestFocus();
            }

            Stax st = new Stax();
            String result = st.StaxEx(word, count);
            if (result == "" && b == false) {
                JOptionPane.showMessageDialog(new JFrame(), "Такого слова нет в словаре!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                wordTextField.setText("");
                s = false;
                wordTextField.requestFocus();
            }
            b = false;
            descriptionTextArea.append(result + "\n\n");

            if (descriptionTextArea.getText() != "" && s != false) {
                bMore.setEnabled(true);
            }
        }
        else
        {
            if (wordTextField.getText().equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Введите слово!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                wordTextField.requestFocus();
            }
            else {
                JOptionPane.showMessageDialog(new JFrame(), "Слово должно состоять только из букв русского алфавита!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
                wordTextField.setText("");
                wordTextField.requestFocus();
            }
        }
        s = true;
    };

    public void buttonMoreHandler() throws Exception {

        bm = true;
        count++; // счётчик для проверки на существование ещё одного определения
        String word = wordTextField.getText().trim();
        Stax st = new Stax();
        String result = st.StaxEx(word, count);

        if (result == "")
        {
            count = 1;
            JOptionPane.showMessageDialog(new JFrame(), "Для этого слова больше нет определений!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            wordTextField.hasFocus();
            bMore.setEnabled(false);
        }

        descriptionTextArea.append(result + "\n\n");
    };



    public void frameDraw(JFrame frame) {

        descriptionTextArea = new JTextArea(form_height / 19, 50);
        wordTextField = new JTextField();

        JScrollPane scrollPaneMain = new JScrollPane(descriptionTextArea);
        scrollPaneMain.setLocation(0, 0);

        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setEditable(false);
        wordTextField.hasFocus();


        bFind.setText("Искать");
        bFind.setToolTipText("Искать определение в словаре");
        bMore.setText("Ещё определения");
        bMore.setToolTipText("Искать другие определения в словаре");
        bMore.setEnabled(false);

        bFind.addActionListener(e -> {
            try {
                buttonFindHandler();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        bMore.addActionListener(e -> {
            try {
                buttonMoreHandler();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(form_title);
        frame.setLocation(form_loc_x, form_loc_y);
        frame.setSize(form_width, form_height);
        frame.setResizable(false);

        frame.getContentPane().add(BorderLayout.NORTH, scrollPaneMain);
        frame.getContentPane().add(BorderLayout.CENTER, wordTextField);
        frame.getContentPane().add(BorderLayout.EAST, bMore);
        frame.getContentPane().add(BorderLayout.WEST, bFind);

        frame.setVisible(true);

        wordTextField.requestFocus();
    }

    public void AntiStatic() {

        frameDraw(new Dictionary());
    }

    public static void main(String[] args) {

        new Dictionary().AntiStatic();
    }
}
