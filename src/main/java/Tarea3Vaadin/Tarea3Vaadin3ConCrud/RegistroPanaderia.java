package Tarea3Vaadin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegistroPanaderia {

    private static RegistroPanaderia instance;
    private static final Logger LOGGER = Logger.getLogger(RegistroPanaderia.class.getName());

    private final HashMap<Long, Panaderia> contacts = new HashMap<>();
    private long nextId = 0;

    private RegistroPanaderia() {
    }


    public static RegistroPanaderia getInstance() {
        if (instance == null) {
            instance = new RegistroPanaderia();
            instance.ensureTestData();
        }
        return instance;
    }

    /**
     * @return all available Customer objects.
     */
    public synchronized List<Panaderia> findAll() {
        return findAll(null);
    }


    public synchronized List<Panaderia> findAll(String stringFilter) {
        ArrayList<Panaderia> arrayList = new ArrayList<>();
        for (Panaderia contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(RegistroPanaderia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Panaderia>() {

            @Override
            public int compare(Panaderia o1, Panaderia o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    /**
     * Finds all Customer's that match given filter and limits the resultset.
     *
     * @param stringFilter
     *            filter that returned objects should match or null/empty string
     *            if all objects should be returned.
     * @param start
     *            the index of first result
     * @param maxresults
     *            maximum result count
     * @return list a Customer objects
     */
    public synchronized List<Panaderia> findAll(String stringFilter, int start, int maxresults) {
        ArrayList<Panaderia> arrayList = new ArrayList<>();
        for (Panaderia contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(RegistroPanaderia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Panaderia>() {

            @Override
            public int compare(Panaderia o1, Panaderia o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        int end = start + maxresults;
        if (end > arrayList.size()) {
            end = arrayList.size();
        }
        return arrayList.subList(start, end);
    }

    /**
     * @return the amount of all customers in the system
     */
    public synchronized long count() {
        return contacts.size();
    }

    /**
     * Deletes a customer from a system
     *
     * @param value
     *            the Customer to be deleted
     */
    public synchronized void delete(Panaderia value) {
        contacts.remove(value.getId());
    }

    /**
     * Persists or updates customer in the system. Also assigns an identifier
     * for new Customer instances.
     *
     * @param entry
     */
    public synchronized void save(Panaderia entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "");
            return;
        }
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Panaderia) entry.clone();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        contacts.put(entry.getId(), entry);
    }

    /**
     * Sample data generation
     */
    public void ensureTestData() {
        if (findAll().isEmpty()) {

        }
    }

}
