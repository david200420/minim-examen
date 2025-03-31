package edu.upc.dsa;

import edu.upc.dsa.exceptions.TrackNotFoundException;
import edu.upc.dsa.models.Product;

import java.util.List;

public interface TracksManager {


    public Product addTrack(String id, String title, String singer);
    public Product addTrack(String title, String singer);
    public Product addTrack(Product t);
    public Product getTrack(String id);
    public Product getTrack2(String id) throws TrackNotFoundException;

    public List<Product> findAll();
    public void deleteTrack(String id);
    public Product updateTrack(Product t);

    public void clear();
    public int size();
}
