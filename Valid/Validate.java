package Valid;


public class Validate {
    public void checkToy(String name, String count) throws Exception {
        if (name.length() == 0) {
            throw new Exception("Такого наименования игрушки нет");
        }
        if (count.length() == 0) {
            throw new Exception("Отсутствует количество");
        }
    }
}
