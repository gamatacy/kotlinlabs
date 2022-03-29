package Commands;

import Collection.collectionManager;

public class infoCommand extends Command{
    private collectionManager cManager;

    public infoCommand(collectionManager CM){
        super("info");
        this.cManager = CM;
    }

    @Override
    public void execute() {
        System.out.println(
                cManager.getProductsCollection().getClass()
        );
    }
}
