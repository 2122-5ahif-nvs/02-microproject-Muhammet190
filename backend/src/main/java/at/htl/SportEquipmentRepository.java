package at.htl;

import at.htl.fitnesstudio.entity.SportEquipment;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;
@ApplicationScoped
public class SportEquipmentRepository  {

    private static SportEquipmentRepository instance;
    private List<SportEquipment> equipments;
    private SportEquipmentRepository(){
        equipments = CreateEquipments();
    }

    private List<SportEquipment> CreateEquipments() {

        List<SportEquipment> equipmentList = new LinkedList<>();
         equipmentList.add(new SportEquipment("Leg press","Techno Gym"));
         equipmentList.add(new SportEquipment("Shoulder Press","Techno Gym"));
        equipmentList.add(new SportEquipment("Smith Machine","Techno Gym"));
        equipmentList.add(new SportEquipment("Leg Extension","Techno Gym"));
        return equipmentList;
    }

   public static SportEquipmentRepository getInstance(){
        if (instance == null){
            instance = new SportEquipmentRepository();
        }
        return instance;
    }

    public List<SportEquipment> getEquipments() {
        return equipments;
    }

    public SportEquipment AddEquipment (String name, String brand){
        SportEquipment sportEquipment = new SportEquipment(name,brand);
        equipments.add(sportEquipment);
        return sportEquipment;
    }

    public SportEquipment UpdateEquipment (SportEquipment oldValue, String newName, String newBrand){
        SportEquipment updatedEquipment = new SportEquipment();

        for (SportEquipment item: equipments) {

            if (item.getName().equals(oldValue.getName())){
                item.setName(newName);
                item.setBrand(newBrand);
                updatedEquipment = item;
            }
        }
        return updatedEquipment;
    }

    public SportEquipment DeleteEquipment (String name){
        SportEquipment deletedEquipment = (SportEquipment) equipments.stream().filter(sEquipment -> sEquipment.getName().equals(name)).findAny().get();
        equipments.remove(deletedEquipment);
        return deletedEquipment;
    }
}
