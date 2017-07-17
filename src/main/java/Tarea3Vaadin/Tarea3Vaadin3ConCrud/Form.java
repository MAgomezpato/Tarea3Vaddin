package Tarea3Vaadin;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class Form extends FormLayout {

    private TextArea title = new TextArea("");
    private TextField nombre = new TextField("Codigo Panaderia");
    private TextField apellido = new TextField("Nombre Panaderia");
    private TextField email = new TextField("Tipo Pedido");
    private NativeSelect<Equipo> cursos = new NativeSelect<>("Equipo Industrial");
    private DateField birthdate = new DateField("Fecha");
    private DateField nacimiento = new DateField("Fecha del Pedido");
    private Button save = new Button("Agregar");
    private Button delete = new Button("Eliminar");

    private RegistroPanaderia service = RegistroPanaderia.getInstance();
    private Panaderia customer;
    private Version2ConCrud version2ConCrud;
    private Binder<Panaderia> binder = new Binder<>(Panaderia.class);

    public Form(Version2ConCrud version2ConCrud) {

        VerticalLayout content = new VerticalLayout();


        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");


        Label title = new Label("Agregar un Nuevo Pedido");
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand

        this.version2ConCrud = version2ConCrud;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(title,nombre, apellido, email,cursos,nacimiento, buttons);


        cursos.setItems(Equipo.values());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(KeyCode.ENTER);

        binder.bindInstanceFields(this);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
    }

    public void setCustomer(Panaderia customer) {
        this.customer = customer;
        binder.setBean(customer);

        // Show delete button for only customers already in the database
        delete.setVisible(customer.isPersisted());
        setVisible(true);
        nombre.selectAll();
    }

    private void delete() {
        service.delete(customer);
        version2ConCrud.updateList();
        setVisible(false);
    }

    private void save() {
        service.save(customer);
        version2ConCrud.updateList();
        setVisible(false);
    }

}
