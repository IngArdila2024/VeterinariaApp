package controllers;

import dao.ClienteDAO;
import models.Cliente;

import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void agregarCliente(String nombre, String email, String telefono, String direccion) {
        Cliente cliente = new Cliente(nombre, email, telefono, direccion);
        clienteDAO.agregarCliente(cliente);
        System.out.println("Cliente agregado correctamente: " + cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteDAO.obtenerClientes();
    }
}
