package de.tub.ise.anwsys.Service;

import de.tub.ise.anwsys.models.Topping;
import de.tub.ise.anwsys.repos.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ToppingService {

    private ToppingRepository toppingRepository;

    @Autowired
    private ToppingService(ToppingRepository toppingRepository) {
        this.toppingRepository = toppingRepository;
    }

    public Topping addTopping(Topping topping)
    {
        if(topping.getId() >0) {
            return topping;
        }
        else {
            return toppingRepository.save(topping);
        }
    }
    public Topping getTopping(Long toppingId) {
        Topping topping = toppingRepository.findOne(toppingId);
        return topping;
    }

    public void deleteTopping(Long toppingId) {
        toppingRepository.delete(toppingId);
    }

    public boolean findTopping(Long toppingId) {
        if (toppingRepository.findOne(toppingId) != null)
            return true;
        else
            return false;
    }

}