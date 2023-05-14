package kg.marsbekov.springdemo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Qualifier("storageService")
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final RestTemplate restTemplate;
    private List<String> list = new ArrayList<>();

    public String getCats() {
        ResponseEntity<String> response =
                restTemplate.exchange("https://cat-fact.herokuapp.com/facts", HttpMethod.GET, null, String.class);
        return response.getBody();
    }
    private boolean isExist(String str) {
        return list.contains(str);
    }


    public List<String> getList(){
//        if (list.isEmpty()){
//            throw new EmptyListException("List is Empty");
//        }
        return list;

    }

    public void addToList(String str) {
        if (isExist(str)) {
            log.info("String {} is  already exist", str);
            return;
        }
        list.add(str);
        log.info("String {} added to list", str);
    }

    public boolean deleteStringFromList(String str) {
        if (!isExist(str)){
            log.info("String {} not exist in list", str);
            return false;
        }
        boolean isDeleted = list.remove(str);
        log.info("String {} deleted from list", str);
        return isDeleted;
    }
    public boolean isExistInList(String str) {
        return isExist(str);
    }


}
