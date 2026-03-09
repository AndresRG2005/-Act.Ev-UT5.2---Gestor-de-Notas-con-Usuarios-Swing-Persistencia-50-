package andresrg.ui;

import andresrg.model.Usuario;
import andresrg.persistence.UsuarioRepository;
import andresrg.security.PasswordUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegisterDialog extends JDialog {

    private JTextField txtUsername;
    private JPasswordField txtPass1, txtPass2;

    public RegisterDialog(JFrame parent, UsuarioRepository repo) {
        super(parent, "Registro de Usuario", true);

        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel("Contraseña:"));
        txtPass1 = new JPasswordField();
        add(txtPass1);

        add(new JLabel("Repetir contraseña:"));
        txtPass2 = new JPasswordField();
        add(txtPass2);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnRegistrar);
        add(btnCancelar);

        btnRegistrar.addActionListener(e -> registrar(repo));
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void registrar(UsuarioRepository repo) {
        String user = txtUsername.getText().trim();
        String p1 = new String(txtPass1.getPassword());
        String p2 = new String(txtPass2.getPassword());

        if (user.isEmpty() || p1.isEmpty() || p2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
            return;
        }

        if (!p1.equals(p2)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        List<Usuario> usuarios = repo.cargarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getUsername().equals(user)) {
                JOptionPane.showMessageDialog(this, "El usuario ya existe.");
                return;
            }
        }

        String hash = PasswordUtils.hashPassword(p1);
        usuarios.add(new Usuario(user, hash));
        repo.guardarUsuarios(usuarios);

        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
        dispose();
    }
}
