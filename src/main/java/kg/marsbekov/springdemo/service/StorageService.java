package kg.marsbekov.springdemo.service;

import java.util.List;

public interface StorageService {
    String getCats();
    List<String> getList();
    void addToList(String str);
    boolean deleteStringFromList(String str);
    boolean isExistInList(String str);

}
