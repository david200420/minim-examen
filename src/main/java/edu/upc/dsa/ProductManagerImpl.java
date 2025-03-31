package edu.upc.dsa;

import edu.upc.dsa.exceptions.TrackNotFoundException;
import edu.upc.dsa.models.Product;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class TracksManagerImpl implements TracksManager {
    private static TracksManager instance;
    protected List<Product> products;
    final static Logger logger = Logger.getLogger(TracksManagerImpl.class);

    private TracksManagerImpl() {
        this.products = new LinkedList<>();
    }

    public static TracksManager getInstance() {
        if (instance==null) instance = new TracksManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.products.size();
        logger.info("size " + ret);

        return ret;
    }

    public Product addTrack(Product t) {
        logger.info("new Product " + t);

        this.products.add (t);
        logger.info("new Product added");
        return t;
    }

    public Product addTrack(String title, String singer){
        return this.addTrack(null, title, singer);
    }

    public Product addTrack(String id, String title, String singer) {
        return this.addTrack(new Product(id, title, singer));
    }

    public Product getTrack(String id) {
        logger.info("getTrack("+id+")");

        for (Product t: this.products) {
            if (t.getId().equals(id)) {
                logger.info("getTrack("+id+"): "+t);

                return t;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    public Product getTrack2(String id) throws TrackNotFoundException {
        Product t = getTrack(id);
        if (t == null) throw new TrackNotFoundException();
        return t;
    }


    public List<Product> findAll() {
        return this.products;
    }

    @Override
    public void deleteTrack(String id) {

        Product t = this.getTrack(id);
        if (t==null) {
            logger.warn("not found " + t);
        }
        else logger.info(t+" deleted ");

        this.products.remove(t);

    }

    @Override
    public Product updateTrack(Product p) {
        Product t = this.getTrack(p.getId());

        if (t!=null) {
            logger.info(p+" rebut!!!! ");

            t.setSinger(p.getSinger());
            t.setTitle(p.getTitle());

            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return t;
    }

    public void clear() {
        this.products.clear();
    }
}