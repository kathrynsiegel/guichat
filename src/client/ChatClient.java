package client;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import user.User;

public class ChatClient extends JFrame {
    
    User user;
    Map<User, JLabel> userLabels;
    JPanel users;
    JPanel onlineUsers;
    JScrollPane userScroll;
    JLabel welcome;
    JPanel welcomePanel;

    
    public ChatClient(User user) {
        
        this.setSize(200, 400);
        
        userLabels = new HashMap<User, JLabel>();

        onlineUsers = new JPanel();
        onlineUsers.setLayout(new BoxLayout(onlineUsers, BoxLayout.PAGE_AXIS));
        
        users = new JPanel();
        users.setLayout(new BoxLayout(users, BoxLayout.PAGE_AXIS));
        users.add(new JLabel("Click on a friend to chat!"));
        users.add(onlineUsers);

        userScroll = new JScrollPane(users);
       
        welcome = new JLabel("Welcome, " + user.getUsername() + "!");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        
        welcomePanel = new JPanel();
        welcomePanel.add(welcome);
       
        
        // Add color
        users.setBackground(Color.white);
        onlineUsers.setBackground(Color.white);
        welcomePanel.setBackground(Color.DARK_GRAY);
        welcome.setForeground(Color.white);
       
        
        // Add padding
        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        onlineUsers.setBorder(BorderFactory.createCompoundBorder(onlineUsers.getBorder(),paddingBorder));
        users.setBorder(BorderFactory.createCompoundBorder(users.getBorder(),paddingBorder));
        welcome.setBorder(BorderFactory.createCompoundBorder(welcome.getBorder(),paddingBorder));

        createGroupLayout();

    }
    
    
    public void addUser(User user) {
        JLabel userLabel = new JLabel(user.getUsername());
        userLabels.put(user, userLabel);
        new UserListener(userLabel);
        onlineUsers.add(userLabel);
       
    }
    
    public void removeUser(User user) {
        onlineUsers.remove(userLabels.get(user));
        onlineUsers.revalidate();
    }
    
    
    
    private void createGroupLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        Group h = layout.createParallelGroup();
        h.addComponent(welcomePanel, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        h.addComponent(userScroll, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        
        Group v = layout.createSequentialGroup();
        v.addComponent(welcomePanel, 40, 40, 40);
        v.addComponent(userScroll, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        
        layout.setHorizontalGroup(h);
        layout.setVerticalGroup(v);

    }

    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatClient main = new ChatClient(new User("Ben"));

                main.addUser(new User("Casey"));
                main.addUser(new User("Katie"));
                main.addUser(new User("Alex"));
                main.setVisible(true);
            }
        });
    }
}