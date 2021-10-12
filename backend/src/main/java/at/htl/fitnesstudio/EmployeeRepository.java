package at.htl.fitnesstudio;


import at.htl.fitnesstudio.entity.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class EmployeeRepository  implements PanacheRepository<Employee> {

    private Map<Integer, Employee> employees;


    public Employee findByName(String name) {
        return find("name",name).singleResult();
    }

    public List<Employee> findAllEmployees() {
        return findAll().list();
    }

    @Transactional
    public Employee save(Employee employee) {
        persist(employee);
        return findByName(employee.getName());
    }

    @Transactional
    public boolean updateEmployee(long id,Employee e){
        Employee old = findEmpById(id);
        if(old == null){
            return false;
        }
        old.setSalary(e.getSalary());
        old.setName(e.getName());
        return true;
    }

    public Employee findEmpById(long id) {
        return findById(id);
    }

    @Transactional
    public boolean deleteEmployeeById(long id) {
        if(findEmpById(id)==null)
        {
            return false;
        }
        deleteById(id);
        return true;
    }
    /*

    public EmployeeRepository() {
        employees = new HashMap<>();
        init();
    }

    void init() {
        employees.clear();
        save(new Employee("Gary",250));
        save(new Employee("Hamudi",150));
        save(new Employee("Benz",500));
    }

    public void save(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    public void delete(int id) {
        employees.remove(id);
    }

    public void deleteAll() {
        employees.values().removeAll(employees.values());
    }

    public Employee findById(int id) {
        return employees.get(id);
    }

    public List<Employee> findByName(String name) {
        return employees
                .values()
                .stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Employee> findAll() {
        return Collections.unmodifiableList(
                new LinkedList<>(employees.values())
        );
    }
     */
}
