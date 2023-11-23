package com.example.shipnhanh.service.impl;



import com.example.shipnhanh.entity.MerchantsEntity;
import com.example.shipnhanh.exception.Validate;
import com.example.shipnhanh.repository.MerchantsRepository;
import com.example.shipnhanh.service.MerchantsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MerchantsImpl implements MerchantsService {
    private final MerchantsRepository repository;
    private final Validate validate;

    public MerchantsImpl(MerchantsRepository repository, Validate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    @Override
    public MerchantsEntity save(MerchantsEntity x){
        return repository.save(valiDate(x));
    }

    @Override
    public MerchantsEntity findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<MerchantsEntity> findAll(int pageNumber, int maxRecord) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<MerchantsEntity> page = repository.findAll(pageable);
        return page;
    }

    @Override
    public Page<MerchantsEntity> findByName(int pageNumber, int maxRecord, String name) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        Page<MerchantsEntity> page = repository.findByName(name,pageable);
        return page;
    }

    private MerchantsEntity valiDate(MerchantsEntity x){

        MerchantsEntity y = new MerchantsEntity();
        y.setId(x.getId());
        y.setNameMachanse (validate.isValidateString(x.getNameMachanse ()));
        y.setAddress(validate.isValidateString(x.getAddress()));
        y.setLatitude(validate.convertLatitude(x.getLatitude()));
        y.setLongitude(validate.convertLongitude(x.getLongitude()));
        y.setNumberphone(validate.convertNumberPhone(x.getNumberphone()));
        y.setTimeclose(x.getTimeclose());
        y.setTimeopen(x.getTimeopen());
        y.setImage(x.getImage());
        return y;
    }
}
