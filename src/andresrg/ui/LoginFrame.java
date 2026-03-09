package andresrg.ui;

import andresrg.model.Usuario;
import andresrg.persistence.UsuarioRepository;
import andresrg.security.PasswordUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private UsuarioRepository usuarioRepo;

    public LoginFrame() {
        super("Gestor de Notas - Login");

        usuarioRepo = new UsuarioRepository("data/usuarios.txt");

        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        JButton btnLogin = new JButton("Iniciar sesión");
        JButton btnRegister = new JButton("Registrarse");
        JButton btnSalir = new JButton("Salir");

        add(btnLogin);
        add(btnRegister);
        add(btnSalir);

        btnLogin.addActionListener(e -> login());
        btnRegister.addActionListener(e -> new RegisterDialog(this, usuarioRepo));
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void login() {
        String user = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
            return;
        }

        List<Usuario> usuarios = usuarioRepo.cargarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getUsername().equals(user)) {
                if (PasswordUtils.verifyPassword(pass, u.getPasswordHash())) {
                    JOptionPane.showMessageDialog(this, "Bienvenido " + user);
                    new MainFrame(u);
                    dispose();
                    return;
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Usuario no registrado.");
    }
}
