package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Cliente;
import vallegrande.edu.pe.model.ClienteDAO;
import java.util.List;

public class ClienteController {
    private final ClienteDAO dao = new ClienteDAO();

    public List<Cliente> obtenerClientes() {
        return dao.listar();
    }

    public void agregarCliente(String nom, String ape, String dni, String cor, String tel, String pai) {
        Cliente c = new Cliente();
        c.setNombre(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCorreo(cor);
        c.setTelefono(tel);
        c.setPais(pai);
        dao.insertar(c);
    }

    public void eliminarCliente(int id) {
        dao.eliminar(id);
    }
}