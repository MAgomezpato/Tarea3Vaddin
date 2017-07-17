package Tarea3Vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;


@Theme("mytheme")
public class Principal extends UI {

    

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        HorizontalLayout hlayout = new HorizontalLayout();
        HorizontalLayout hlayout2 = new HorizontalLayout();
        HorizontalLayout hlayout3 = new HorizontalLayout();
        VerticalLayout vlayout = new VerticalLayout();

        TextField codigo=new TextField("Codigo:");
        TextField panaderia=new TextField("Panaderia:");
        TextField pedido=new TextField("Pedido:");
        TextField equipo=new TextField("Equipo:");
        Button agregar=new Button("Agregar");
        Button nuevo=new Button("Nuevo");
        Button eliminar=new Button("Eliminar");
        Button salir=new Button("Salir");

        Grid<Principal> tabla=new Grid<>();
        tabla.addColumn(Principal::getCaption).setCaption("Codigo");
        tabla.addColumn(Principal::getCaption).setCaption("Panaderia");
        tabla.addColumn(Principal::getCaption).setCaption("Agregar");
        tabla.addColumn(Principal::getCaption).setCaption("Equipo");

        hlayout.addComponents(codigo,panaderia);
        hlayout2.addComponents(pedido, equipo);
        hlayout3.addComponents(agregar, nuevo, eliminar, salir);
        vlayout.addComponent(hlayout);
        vlayout.addComponent(hlayout2);
        vlayout.addComponent(hlayout3);
        vlayout.addComponent(tabla);
        setContent(vlayout);





        agregar.addClickListener( e -> {
            tabla.SetItems
        });

        /*TextField codigo = new TextField();
        codigo.setCaption("Codigo:");

        TextField panaderia = new TextField();
        panaderia.setCaption("Panaderia:");

        TextField pedido = new TextField();
        pedido.setCaption("Pedido:");

        ComboBox<String> equipo = new ComboBox<>("Equipo");
        equipo.setItems("Horno", "Batidora", "Amasadora", "Mesa", "Clavijero");

        Button agregar = new Button("Agregar");
        Button nuevo = new Button("Nuevo");
        Button eliminar = new Button("Eliminar");
        Button eliminarTodo = new Button("Eliminar Todo");
        Button salir = new Button("Salir");*/

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Principal.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
