package Metod;

import java.util.List;

public interface Repository{
    List<Toy> getAllToys();
    String CreateToy(Toy toy, String nameAdd, String countAdd);
    void UpdateToy(Toy toy, Fields field, String param);
    void delToy(List toys);
    void ToyPrize();
}
