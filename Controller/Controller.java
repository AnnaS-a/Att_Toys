package Controller;
import java.util.List;

import Metod.Fields;
import Metod.Repository;
import Metod.Toy;
import Valid.Validate;

public class Controller {
    private final Repository repository;
    private final Validate validate;
    
    public Controller(Repository repository, Validate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    public List<Toy> getToys() throws Exception {
        return repository.getAllToys();
    }

    public void saveToy(Toy toy, String name, String count) throws Exception {
        validate.checkToy(toy.getName(),toy.getCount());
        repository.CreateToy(toy, name, count);
    }

    public Toy readToy(String toyId) throws Exception {
        List<Toy> toys = repository.getAllToys();
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)){
                return toy;
            }
        }
        throw new Exception("Игрушка не найдена");
    }

    public void updateToy(Toy toy, Fields field, String param) throws Exception {
        if(field == Fields.NAME) {
            validate.checkToy(param, toy.getCount());
        }
        if(field == Fields.COUNT) {
            validate.checkToy(toy.getName(),param);
        }
        repository.UpdateToy(toy, field, param);
    }

    public  Toy deleteToy(String toyId) throws Exception {
        List<Toy> toys = repository.getAllToys();
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                toys.remove(toy);
                repository.delToy(toys);
                return toy;
            }
        }
        throw new Exception("Игрушка не найдена");
    }

    public void prizeToy() {
        repository.ToyPrize();
    }
}
