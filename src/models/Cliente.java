
package models;

public class Cliente extends BaseModel {
    private String direccion;

    public Cliente(int id, String nombre, String email, String telefono, String direccion) {
        super(id, nombre, email, telefono);
        this.direccion = direccion;
    }

    public Cliente(String nombre, String email, String telefono, String direccion) {
        super(nombre, email, telefono);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente [\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  Nombre: ").append(nombre).append("\n");
        sb.append("  Email: ").append(email).append("\n");
        sb.append("  Teléfono: ").append(telefono).append("\n");
        sb.append("  Dirección: ").append(direccion).append("\n");
        sb.append("]");
        return sb.toString();
    }
}