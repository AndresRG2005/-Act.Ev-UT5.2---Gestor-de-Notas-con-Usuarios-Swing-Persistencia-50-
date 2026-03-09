package andresrg.ui;

import andresrg.model.Nota;
import andresrg.model.Usuario;
import andresrg.persistence.NotaRepository;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private Usuario usuario;
    private NotaRepository notaRepo;

    private List<Nota> notas;
    private DefaultListModel<Nota> listModel;

    private JList<Nota> listaNotas;
    private JTextField txtTitulo, txtBuscar;
    private JTextArea txtContenido;
    private JLabel lblEstado;

    public MainFrame(Usuario usuario) {
        super("Gestor de Notas - Usuario: " + usuario.getUsername());

        this.usuario = usuario;
        this.notaRepo = new NotaRepository();
        this.notas = notaRepo.cargarNotas((String) usuario.getUsername());

        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

      
        listModel = new DefaultListModel<>();
        notas.forEach(listModel::addElement);

        listaNotas = new JList<>(listModel);
        listaNotas.addListSelectionListener(e -> cargarNotaSeleccionada());
        add(new JScrollPane(listaNotas), BorderLayout.WEST);

       
        JPanel panelCentro = new JPanel(new BorderLayout());

        txtTitulo = new JTextField();
        txtContenido = new JTextArea();

        panelCentro.add(txtTitulo, BorderLayout.NORTH);
        panelCentro.add(new JScrollPane(txtContenido), BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBuscar = new JPanel(new BorderLayout());
        txtBuscar = new JTextField();
        panelBuscar.add(new JLabel("Buscar: "), BorderLayout.WEST);
        panelBuscar.add(txtBuscar, BorderLayout.CENTER);
        add(panelBuscar, BorderLayout.NORTH);

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }
        });

       
        JPanel panelBotones = new JPanel();

        JButton btnNueva = new JButton("Nueva");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnBorrarTodas = new JButton("Borrar todas");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");

        panelBotones.add(btnNueva);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnBorrarTodas);
        panelBotones.add(btnCerrarSesion);

        add(panelBotones, BorderLayout.SOUTH);

        lblEstado = new JLabel("Listo.");
        add(lblEstado, BorderLayout.PAGE_END);

        btnNueva.addActionListener(e -> crearNota());
        btnGuardar.addActionListener(e -> guardarNota());
        btnEliminar.addActionListener(e -> eliminarNota());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnBorrarTodas.addActionListener(e -> borrarTodas());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        setVisible(true);
    }

    private void cargarNotaSeleccionada() {
        Nota n = listaNotas.getSelectedValue();
        if (n != null) {
            txtTitulo.setText(n.getTitulo());
            txtContenido.setText(n.getContenido());
        }
    }

    private void crearNota() {
        String titulo = txtTitulo.getText().trim();
        String contenido = txtContenido.getText();

        if (titulo.isEmpty()) {
            lblEstado.setText("El título no puede estar vacío.");
            return;
        }

        int id = notas.size() + 1;
        Nota nueva = new Nota(id, titulo, contenido);

        notas.add(nueva);
        listModel.addElement(nueva);

        guardar();
        lblEstado.setText("Nota creada.");
    }

    private void guardarNota() {
        Nota n = listaNotas.getSelectedValue();
        if (n == null) {
            lblEstado.setText("Selecciona una nota.");
            return;
        }

        n.setTitulo(txtTitulo.getText());
        n.setContenido(txtContenido.getText());

        listaNotas.repaint();
        guardar();
        lblEstado.setText("Nota guardada.");
    }

    private void eliminarNota() {
        Nota n = listaNotas.getSelectedValue();
        if (n == null) {
            lblEstado.setText("Selecciona una nota.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta nota?");
        if (confirm == JOptionPane.YES_OPTION) {
            notas.remove(n);
            listModel.removeElement(n);
            guardar();
            lblEstado.setText("Nota eliminada.");
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtContenido.setText("");
        lblEstado.setText("Campos limpiados.");
    }

    private void borrarTodas() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Borrar TODAS las notas?");
        if (confirm == JOptionPane.YES_OPTION) {
            notas.clear();
            listModel.clear();
            guardar();
            lblEstado.setText("Todas las notas borradas.");
        }
    }

    private void cerrarSesion() {
        guardar();
        new LoginFrame();
        dispose();
    }

    private void guardar() {
        notaRepo.guardarNotas((String) usuario.getUsername(), notas);
    }

    private void filtrar() {
        String texto = txtBuscar.getText().toLowerCase();
        listModel.clear();

        for (Nota n : notas) {
            if (n.getTitulo().toLowerCase().contains(texto)) {
                listModel.addElement(n);
            }
        }
    }
}
