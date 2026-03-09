package andresrg.persistence;

import andresrg.model.Usuario;
import java.io.*;
import java.util.*;

public class UsuarioRepository {

    private final File file;

    public UsuarioRepository(String ruta) {
        this.file = new File(ruta);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        if (!file.exists()) return usuarios;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    usuarios.add(new Usuario(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void guardarUsuarios(List<Usuario> usuarios) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Usuario u : usuarios) {
                pw.println(u.getUsername() + ";" + u.getPasswordHash());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
