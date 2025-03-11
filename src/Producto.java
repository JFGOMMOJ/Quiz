import java.util.List;
import java.util.Scanner;

public class Producto {
    private int codigo_producto;
    private String nombre_producto;
    private int precio_producto;
    private int stock;

    public Producto(int codigo_producto, String nombre_producto, int precio_producto, int stock) {
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo_producto=" + codigo_producto +
                ", nombre_producto=" + nombre_producto +
                ", precio_producto=" + precio_producto +
                ", stock=" + stock +
                '}';
    }

    public int getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Este metodo es static, ya que no depende de una instancia específica de Producto.
    public static void crearProducto(Scanner teclado, List<Producto> productos) {
        System.out.print("Codigo Producto: ");
        int codigo_producto = teclado.nextInt();
        teclado.nextLine();

        // Verificar si el código ya existe
        for (Producto p : productos) {
            if (p.getCodigo_producto() == codigo_producto) {
                System.out.println("Error: Ya existe un producto con ese código.");
                return;
            }
        }

        System.out.print("Nombre Producto: ");
        String nombre_producto = teclado.nextLine();
        System.out.print("Precio  Producto: ");
        int precio_producto = teclado.nextInt();
        System.out.print("Cantidad en stock del producto: ");
        int stock = teclado.nextInt();

        Producto producto = new Producto(codigo_producto, nombre_producto, precio_producto, stock);
        productos.add(producto);
        System.out.println("Producto creado con exito.");
    }

    //Vender producto: Reduce la cantidad en stock si hay suficiente disponibilidad.
    public void vender_producto(int cantidad) {
        if (stock >= cantidad) {
            stock -= cantidad;
            System.out.println("Venta realizada con éxito. " +
                    "\nEl valor a cobrar es: $"+ (cantidad * precio_producto) + " " +
                    "\nQuedan " + stock + " unidades de " + nombre_producto + " en stock");
        } else {
            System.out.println("No hay suficiente stock disponible. Stock actual: " + stock);
        }
    }

    // 3. Reponer stock: Aumenta la cantidad en stock cuando llega nueva mercancía.
    public void reponer_stock(int cantidad) {
        if (cantidad > 0) {
            stock += cantidad;
            System.out.println("Stock actualizado. " +
                    "\nNueva cantidad: " + stock + " unidades de " + nombre_producto);
        } else {
            System.out.println("La cantidad a reponer debe ser mayor que cero");
        }
    }
    // 4. Aplicar descuento: Reduce el precio del producto en un porcentaje dado(2%).
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = precio_producto * (porcentaje / 100);
            precio_producto -= descuento;
            System.out.println("Se aplico un descuento del " + porcentaje + "%.");
            System.out.println("Nuevo precio: " + precio_producto);
        } else {
            System.out.println("El porcentaje debe estar entre 1% y 100%.");
        }
    }

    //5. Mostrar información: Imprime los detalles del producto. (NO toString)
    public void mostrarInformacion() {
        System.out.println("Código: " + codigo_producto);
        System.out.println("Nombre: " + nombre_producto);
        System.out.println("Precio: $" + precio_producto);
        System.out.println("Stock disponible: " + stock);
    }

    //6. Aumentar precio: Incrementa el precio del producto en un porcentaje dado.(6%)
    public void aumentarPrecio(double porcentaje) {
        if (porcentaje > 0) {
            double aumento = precio_producto * (porcentaje / 100);
            precio_producto += aumento;
            System.out.println("Se aumentó el precio en un " + porcentaje + "%.");
            System.out.println("Nuevo precio: $" + precio_producto);
        } else {
            System.out.println("El porcentaje debe ser mayor a 0.");
        }
    }
    //7. Calcular valor total en inventario:
    public static void calcularValorTotalInventario(List<Producto> productos) {
        double totalInventario = 0;

        System.out.println("\n📦 Resumen del Inventario:");
        if (productos.isEmpty()) {
            System.out.println("⚠ No hay productos en el inventario.");
        } else {
            for (Producto p : productos) {
                //Multiplica la cantidad en stock por el precio del producto.
                double valorProducto = p.getPrecio_producto() * p.getStock();
                System.out.println("Código: " + p.getCodigo_producto() +
                        " | Nombre: " + p.getNombre_producto() +
                        " | Precio: $" + p.getPrecio_producto() +
                        " | Stock: " + p.getStock() +
                        " | Valor Total: $" + valorProducto);
                totalInventario += valorProducto;
            }
            //Mostrar total de inventario
            System.out.println("\n💰 Total del inventario: $" + totalInventario);
        }
    }

    //8. Eliminar productos por codigo.
    public static void eliminarProducto(List<Producto> productos, Scanner teclado) {
        System.out.println("\n Eliminar Producto");
        System.out.print("Ingrese el código del producto: ");
        int codigoEliminar = teclado.nextInt();

        boolean eliminado = productos.removeIf(p -> p.getCodigo_producto() == codigoEliminar);
        if (eliminado) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }



}

