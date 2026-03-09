package andresrg.persistence;

import andresrg.model.Nota;
import java.io.*;
import java.util.*;

public class NotaRepository {

    private File getFileForUser(String username) {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdirs();
        return new File(dir, "notas_" + username + ".txt");
    }

    public List<Nota> cargarNotas(String username) {
        List<Nota> notas = new ArrayList<>();
        File file = getFileForUser(username);
        if (!file.exists()) return notas;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", 3);
                if (partes.length == 3) {
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    String contenido = partes[2];
                    notas.add(new Nota(id, titulo, contenido));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notas;
    }

    public void guardarNotas(String username, List<Nota> notas) {
        File file = getFileForUser(username);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Nota n : notas) {
                pw.println(n.getId() + ";" + n.getTitulo() + ";" + n.getContenido().replace("\n", "\\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
