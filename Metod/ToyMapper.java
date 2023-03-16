package Metod;
public class ToyMapper {
    
    public String map(Toy toy) {
        return String.format("%s, %s, %s, %s", toy.getId(), toy.getName(), toy.getCount(), toy.getDropping());
    }

    public Toy map(String line) {
        String[] lines = line.split(",",-1);
        return new Toy(lines[0],  lines[1], lines[2], lines[3]);
    }

}
