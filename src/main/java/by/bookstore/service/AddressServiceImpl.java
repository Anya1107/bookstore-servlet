package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.repository.AddressRepository;

public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void add(Address address) {
        addressRepository.add(address);
    }

    @Override
    public void delete(int id) {
        addressRepository.delete(id);
    }

    @Override
    public Address findById(int id) {
         return addressRepository.findById(id);
    }

    @Override
    public Address findByName(String name) {
        return  addressRepository.findByName(name);
    }

    @Override
    public Address[] findAll() {
       return  addressRepository.findAll();
    }
}
