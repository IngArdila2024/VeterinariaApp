package dao;

import config.DatabaseConnection;
import models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Constantes con las sentencias SQL
    private static final String SQL_INSERTAR_CLIENTE = "INSERT INTO clientes (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECCIONAR_CLIENTES = "SELECT * FROM clientes";
    private static final String SQL_ACTUALIZAR_CLIENTE = "UPDATE clientes SET nombre = ?, email = ?, telefono = ?, direccion = ? WHERE id = ?";
    private static final String SQL_ELIMINAR_CLIENTE = "DELETE FROM clientes WHERE id = ?";

    /**
     * Inserta un nuevo cliente en la base de datos.
     * También asigna el ID generado automáticamente al objeto cliente.
     * @param cliente Objeto Cliente a insertar.
     */
    public void agregarCliente(Cliente cliente) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERTAR_CLIENTE, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1)); // Asigna el ID generado al cliente
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los clientes almacenados en la base de datos.
     * @return Lista de objetos Cliente.
     */
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECCIONAR_CLIENTES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                );

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Actualiza los datos de un cliente existente en la base de datos.
     * @param cliente Cliente con la información nueva (debe tener un ID válido).
     */
    public void actualizarCliente(Cliente cliente) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ACTUALIZAR_CLIENTE)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());
            stmt.setInt(5, cliente.getId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente actualizado correctamente.");
            } else {
                System.out.println("No se encontró el cliente para actualizar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    /**
     * Elimina un cliente de la base de datos según su ID.
     * @param clienteId ID del cliente a eliminar.
     */
    public void eliminarCliente(int clienteId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ELIMINAR_CLIENTE)) {

            stmt.setInt(1, clienteId);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("No se encontró el cliente para eliminar.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
