package com.orlando.controlador;

import com.orlando.config.Fecha;
import com.orlando.modelo.Carrito;
import com.orlando.modelo.Cliente;
import com.orlando.modelo.Compra;
import com.orlando.modelo.Producto;
import com.orlando.modeloDAO.ClienteDAO;
import com.orlando.modeloDAO.CompraDAO;
import com.orlando.modeloDAO.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    ProductoDAO pdao = new ProductoDAO();
    Producto p = new Producto();
    ClienteDAO cdao = new ClienteDAO();
    Cliente c = new Cliente();
    List<Producto> productos = new ArrayList();
    
    List<Carrito> listaCarrito = new ArrayList();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;
    int r;
    int idp;
    Carrito car;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion=request.getParameter("accion");
        productos = pdao.listar();
        switch(accion){
            case "Login":
                if (accion.equals("Ingresar")) {
                    String nom = request.getParameter("txtnom");
                    String email = request.getParameter("txtemail");
                    c.setNom(nom);
                    c.setEmail(email);
                    r = cdao.validar(c);
                    if (r == 1) {
                        request.getSession().setAttribute("nom", nom);
                        request.getSession().setAttribute("email", email);
                        request.getRequestDispatcher("principal.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }
                break;
            case "Comprar":
                totalPagar = 0.0;
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                item = item+1;
                car = new Carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad * p.getPrecio());
                listaCarrito.add(car);
                for(int i = 0; i < listaCarrito.size(); i++){
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "AgregarCarrito":
                int pos=0;
                cantidad=1;
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                if(listaCarrito.size() > 0){
                    for(int i = 0; i < listaCarrito.size(); i++){
                        if(idp == listaCarrito.get(i).getIdProducto()){
                            pos = i;
                        }
                    }
                    if(idp == listaCarrito.get(pos).getIdProducto()){
                        cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                        double subTotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                        listaCarrito.get(pos).setCantidad(cantidad);
                        listaCarrito.get(pos).setSubTotal(subTotal);
                    } else{
                        item = item+1;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProducto(p.getId());
                        car.setNombres(p.getNombres());
                        car.setDescripcion(p.getDescripcion());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * p.getPrecio());
                        listaCarrito.add(car);
                    }
                }else {
                    item = item+1;
                    car = new Carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombres(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad * p.getPrecio());
                    listaCarrito.add(car);
                }
                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("idp"));
                for(int i = 0; i < listaCarrito.size(); i++){
                    if(listaCarrito.get(i).getIdProducto()==idproducto){
                        listaCarrito.remove(i);
                    }
                }
                break;
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", listaCarrito);
                for(int i = 0; i < listaCarrito.size(); i++){
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "GenerarCompra":
                Cliente cliente = new Cliente();
                cliente.setId(1);
                CompraDAO dao = new CompraDAO();
                Compra compra = new Compra(cliente, 1, Fecha.FechaBD(), totalPagar, "Pagado", listaCarrito);
                int res = dao.GenerarCompra(compra);
                if(res!=0&&totalPagar>0){
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                break;
            default:
                request.setAttribute("cont", listaCarrito.size());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
