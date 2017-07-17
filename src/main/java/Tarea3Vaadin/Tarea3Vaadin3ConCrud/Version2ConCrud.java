package Tarea3Vaadin;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;


@Theme("mytheme")
public class Version2ConCrud extends UI {

    private RegistroPanaderia service = RegistroPanaderia.getInstance();
    private Grid<Panaderia> grid = new Grid<>(Panaderia.class);
    private TextField filterText = new TextField();
    private Form form = new Form(this);

    @Override
    protected void init(VaadinRequest vaadinRequest) {


        VerticalLayout content = new VerticalLayout();
        setContent(content);

        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");


        Label title = new Label("Sistema de Gestion de Panaderias y Equipos para Panaderias");
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand




        final VerticalLayout layoutt = new VerticalLayout();

        layoutt.addComponents(title);





        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("");



        Button addCustomerBtn = new Button("Agregar un Nuevo Pedido");
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Panaderia());
        });

        HorizontalLayout toolbarr = new HorizontalLayout(title);

        HorizontalLayout toolbar = new HorizontalLayout(addCustomerBtn);

        grid.setColumns("nombre", "apellido", "email","cursos","nacimiento");

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbarr,toolbar, main);

        // fetch list of Customers from service and assign it to Grid
        updateList();

        setContent(layout);

        form.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                form.setVisible(false);
            } else {
                form.setCustomer(event.getValue());
            }
        });
    }

    public void updateList() {
        List<Panaderia> customers = service.findAll(filterText.getValue());
        grid.setItems(customers);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Version2ConCrud.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
