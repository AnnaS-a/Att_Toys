package Metod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositoryFile implements Repository {
    private ToyMapper mapper = new ToyMapper();
    private FileInterfase fileInterfase;
    private FileInterfase filePrize;
    

    public RepositoryFile(FileInterfase fileInterfase, FileInterfase filePrize) {
        this.fileInterfase = fileInterfase;
        this.filePrize = filePrize;

    }

    @Override
    public String CreateToy(Toy toy, String nameAdd, String countAdd) {
        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy m : toys) {
            if (m.getName().trim().equals(nameAdd.trim())) {
                System.out.println("Такое наименование игрушки есть");
                changeToyAdd(m, countAdd);
                List<String> lines = new ArrayList<>();
                for (Toy it : toys) {
                    lines.add(mapper.map(it));
                }
                fileInterfase.saveAllLines(lines);
                return null;
            }else{
                    int id = Integer.parseInt(m.getId());
                    if (max < id) {
                        max = id;
                    }
                }    
            }
            int newId = max + 1;
            String i = String.format("%d", newId);
            toy.setId(i);
            toys.add(toy);
            List<String> lines = new ArrayList<>();
            for (Toy it : toys) {
                lines.add(mapper.map(it));
            }
            fileInterfase.saveAllLines(lines);
            return i;
        }

    

    // метод изменения количества игрушек после добавления
    public int changeToyAdd(Toy t, String countAdd) {
        int cAdd = Integer.parseInt(t.getCount().trim());
        int c = Integer.parseInt(countAdd.trim());
        int newCount = c + cAdd;
        String nC = Integer.toString(newCount);
        t.setCount(nC);
        System.out.printf("Количество этой игрушки изменено на: %s\n",t.getCount());
        return Integer.parseInt(t.getId().trim());
    }

    @Override
    public List<Toy> getAllToys() {
        List<String> lines = fileInterfase.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public void UpdateToy(Toy toy, Fields field, String param) {
        if (field == Fields.NAME) {
            toy.setName(param);
        } else if (field == Fields.COUNT) {
            toy.setCount(param);
        } else if (field == Fields.DROPPING) {
            toy.setDropping(param);
        }
        saveToy(toy);
    }

    private void saveToy(Toy toy) {
        List<String> lines = new ArrayList<>();
        List<Toy> toys = getAllToys();
        for (Toy item : toys) {
            if (toy.getId().equals(item.getId())) {
                lines.add(mapper.map(toy));
            } else {
                lines.add(mapper.map(item));
            }
        }
        fileInterfase.saveAllLines(lines);
    }

    public void delToy(List toys) {
        List<String> lines = new ArrayList<>();
        List<Toy> del_toy = toys;
        for (Toy item : del_toy) {
            lines.add(mapper.map(item));
        }
        fileInterfase.saveAllLines(lines);
        System.out.println("Удалено!");
    }


    public void ToyPrize() {
        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy item : toys) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        //System.out.printf("max ID: %d!", max);
        Random r = new Random();
        int prizeID = r.nextInt(1, max);
        System.out.printf("Выпала игрушка с ID: %d.\n", prizeID);
        int countP = 1;
        String drop = null;
        for (Toy item : toys) {
            int id = Integer.parseInt(item.getId().trim());
            int c = Integer.parseInt(item.getCount().trim());
            if (id == prizeID){
                if(c > 0){
                    String nameP = item.getName();
                drop = item.getDropping();
                changeToyPrize(item, countP);
                List<String> prize = new ArrayList<>();
                //for (Toy it : toys) {
                    prize.add(mapper.map(item));
                //}
                filePrize.saveAllLines(prize);
                List<String> lines = new ArrayList<>();
                for (Toy it : toys) {
                    lines.add(mapper.map(it));
                }
                fileInterfase.saveAllLines(lines);
                System.out.printf("\nВас ждет игрушка: %s\n", nameP);
                
                }
            }   
        }
    }

    // метод изменения количества игрушек после розыгрыша
    public void changeToyPrize(Toy t, int countP) {
        int cToyP = Integer.parseInt(t.getCount().trim());
        int newCount = cToyP - countP;
        System.out.printf("Игрушке изменено количество с %d на %d", cToyP, newCount);
        String nC = Integer.toString(newCount);
        t.setCount(nC);
    }

}
