package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.io.FileNotFoundException;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.DuplicatePartnerException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.InvalidDaysException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.UnknownPartnerException;
import ggc.core.exception.MissingFileAssociationException;

/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current warehouse. */
  private String _filename = "";

  /** The wharehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  //FIXME define other attributes
  //FIXME define constructor(s)
  //FIXME define other methods

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException  {
    //FIXME implement serialization method
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException | DuplicatePartnerException | UnknownPartnerException e) {
      throw new ImportFileException(textfile, e);
    }
  }

  public Date getDate() {
    return _warehouse.getDate();
  }

  public void advanceDate(int offset) throws InvalidDaysException {
    _warehouse.advanceDate(offset);
    
  }

  public void registerPartner(String id, String name, String address) throws DuplicatePartnerException {
    _warehouse.registerPartner(id, name, address);
  }

  public Partner getPartnerWithId(String id) throws UnknownPartnerException {
    return _warehouse.getPartnerWithId(id);
  }

  public Collection<Partner> getPartners() {
    return Collections.unmodifiableCollection(_warehouse.getPartners().values());
  }

  public void clearPartnerNotifications(String id) throws UnknownPartnerException {
    Partner partner = getPartnerWithId(id);
    partner.clearNotifications();
  }

  public List<Notification> getPartnerNotifications(String id) throws UnknownPartnerException {
    Partner partner = getPartnerWithId(id);
    return partner.getNotifications();
  }

}
