import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>(); // Lista para almacenar productos
        Scanner teclado = new Scanner(System.in);
        int a;

        System.out.println("Bienvenidos a tu tienda de confianza");
        do {
            System.out.print("Que quieres hacer hoy?: " +
                    "\n1. Crear producto:" +
                    "\n2. Vender producto:" +
                    "\n3. Reponer stock:" +
                    "\n4. Aplicar descuento:" +
                    "\n5. Mostrar información:" +
                    "\n6. Aumentar precio:" +
                    "\n7. Calcular valor total en inventario:" +
                    "\n8. Eliminar productos por codigo."
            );
            a = teclado.nextInt();
            teclado.nextLine();

            switch (a) {
                case 1:
                    System.out.println("Crear producto");
                    Producto.crearProducto(teclado, productos);
                    break;
                case 2:
                    System.out.println("Vender producto");
                    //Revisa que halla stock del producto en el inventario
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos en el inventario.");
                    } else {
                        System.out.print("Digite el Código del producto a vender: ");
                        int codigoVender = teclado.nextInt();
                        System.out.print("Cantidad a vender: ");
                        int cantidadVender = teclado.nextInt();
                        teclado.nextLine(); // Limpiar buffer
                        // Busca la existencia del productp en el inventario
                        boolean encontrado = false;
                        for (Producto p : productos) {
                            if (p.getCodigo_producto() == codigoVender) {
                                p.vender_producto(cantidadVender);
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            System.out.println("Producto no encontrado.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Reponer stock");
                    System.out.print("Código del producto a reponer: ");
                    int codigoReponer = teclado.nextInt();
                    System.out.print("Cantidad a reponer: ");
                    int cantidadReponer = teclado.nextInt();

                    // Busca la existencia del productp en el inventario
                    for (Producto p : productos) {
                        if (p.getCodigo_producto() == codigoReponer) {
                            p.reponer_stock(cantidadReponer);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Aplicar descuento");
                    System.out.print("Ingrese el código del producto para aplicar descuento: ");
                    int codigoDescuento = teclado.nextInt();
                    System.out.print("Ingrese el porcentaje de descuento a aplicar (sin el %): ");
                    double porcentaje = teclado.nextDouble();
                    // Busca la existencia del productp en el inventario
                    boolean encontrado = false;
                    for (Producto p : productos) {
                        if (p.getCodigo_producto() == codigoDescuento) {
                            p.aplicarDescuento(porcentaje);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Producto no existe.");
                    }
                    break;
                case 5:
                    System.out.println("Mostrar información");
                    System.out.print("Código del producto a mostrar: ");
                    int codigoMostrar = teclado.nextInt();
                    for (Producto p : productos) {
                        if (p.getCodigo_producto() == codigoMostrar) {
                            p.mostrarInformacion();
                        }
                    }
                    break;
                case 6:
                    System.out.println("Aumentar precio");
                    System.out.print("Digite el codigo del producto para aumentar precio: ");
                    int codigoAumentar = teclado.nextInt();
                    System.out.print("Ingrese el porcentaje de aumento (sin el %): ");
                    double porcentajeAumento = teclado.nextDouble();

                    boolean productoEncontrado = false;
                    for (Producto p : productos) {
                        if (p.getCodigo_producto() == codigoAumentar) {
                            p.aumentarPrecio(porcentajeAumento);
                            productoEncontrado = true;
                            break;
                        }
                    }

                    if (!productoEncontrado) {
                        System.out.println("⚠ Producto no encontrado.");
                    }
                    break;
                case 7:
                    Producto.calcularValorTotalInventario(productos);
                    break;
                case 8:
                    Producto.eliminarProducto(productos, teclado);
                    break;
                default:
                    System.out.println("No se aceptan opciones diferentes a las mencionadas anteriormente.");
            }
        } while (a != 0);

        teclado.close(); // Cerrar el scanner
    }
}