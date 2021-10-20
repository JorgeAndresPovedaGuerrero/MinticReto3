package com.usa.ciclo3.reto3.services;

import com.usa.ciclo3.reto3.model.Partyroom;
import com.usa.ciclo3.reto3.repository.PartyroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyroomServices {

    @Autowired
    private PartyroomRepository partyroomRepository;

    public List<Partyroom> getAll(){

        return partyroomRepository.getAll();
    }

    public Optional<Partyroom> getPartyroom(int id) {

        return partyroomRepository.getPartyroom(id);
    }

    public Partyroom save(Partyroom partyroom){
        if(partyroom.getId()==null){
            return partyroomRepository.save(partyroom);
        }else{
            Optional<Partyroom> e=partyroomRepository.getPartyroom(partyroom.getId());
            if(e.isEmpty()){
                return partyroomRepository.save(partyroom);
            }else{
                return partyroom;
            }
        }
    }

    public Partyroom update(Partyroom partyroom){
        if(partyroom.getId()!=null){
            Optional<Partyroom> e=partyroomRepository.getPartyroom(partyroom.getId());
            if(!e.isEmpty()){
                if(partyroom.getName()!=null){
                    e.get().setName(partyroom.getName());
                }
                if(partyroom.getOwner()!=null){
                    e.get().setOwner(partyroom.getOwner()) ;
                }
                if(partyroom.getCapacity() !=null){
                    e.get().setCapacity(partyroom.getCapacity());
                }
                if(partyroom.getDescription()!=null){
                    e.get().setDescription(partyroom.getDescription());
                }
                if(partyroom.getCategory()!=null){
                    e.get().setCategory(partyroom.getCategory());
                }
                partyroomRepository.save(e.get());
                return e.get();
            }else{
                return partyroom;
            }
        }else{
            return partyroom;
        }
    }


    public boolean deletePartyroom(int id) {
        Boolean aBoolean = getPartyroom(id).map(partyroom -> {
            partyroomRepository.delete(partyroom);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}


