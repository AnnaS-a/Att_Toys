import Controller.Controller;
import Metod.FileActions;
import Metod.FileInterfase;
import Metod.Repository;
import Metod.RepositoryFile;
import Valid.Validate;
import View.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileInterfase fileInterfase = new FileActions("shopToys.txt");
        FileInterfase filePrize = new FileActions("waitingPrize.txt");
        Repository repository = new RepositoryFile(fileInterfase, filePrize);
        Validate validate = new Validate();
        Controller controller = new Controller(repository, validate);
        ViewUser view = new ViewUser(controller, validate);
        
        
        




        view.run();
    }
}
