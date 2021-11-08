package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.core.Warehouse;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.BatchComparator;
//FIXME import classes

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductBatchesUnderGivenPrice extends Command<WarehouseManager> {

  public DoLookupProductBatchesUnderGivenPrice(WarehouseManager receiver) {
    super(Label.PRODUCTS_UNDER_PRICE, receiver);
    //FIXME add command fields
    addStringField("id", Message.requestProductKey());
  }

  @Override
  public void execute() throws CommandException {
    //FIXME implement command
   /* try {
      _display.popup(_receiver.requestPriceLimit());
      
      _display.addLine(_receiver.getBatches());
      _display.display();
    } 
      catch (UnknownProductKeyException e) {
      throw new UnknownProductKeyException(e.getProductKey());
      */
      try {
        ArrayList<Lookup> lookup = new ArrayList<>();
        for(Product product : getProducts()) {
          for(Batch batch : product.getBatches()) {
            if (_receiver.requestPriceLimit() > batch.getPrice())
            lookup.add(batch);
        _display.popup(_receiver.lookup);
        

      }
    }
      }
      catch (UnknownProductKeyException e) {
          throw new UnknownProductKeyException(e.getProductKey());}
  }

}
