package by.bookstore.repository.inmemory;

import by.bookstore.entity.Address;
import by.bookstore.repository.AddressRepository;
import by.bookstore.repository.DB;

import java.util.Arrays;
import java.util.List;

public class InMemoryAddressRepository implements AddressRepository {
    private DB db=new InMemoryDB();
    private List<Address> addresses;

    {
        addresses=db.read(Address.class);
    }

    public static void main(String[] args) {
        InMemoryAddressRepository inMemoryAddressRepository=new InMemoryAddressRepository();
        inMemoryAddressRepository.add(new Address("test3"));
        inMemoryAddressRepository.add(new Address("test4"));
        System.out.println(Arrays.toString(inMemoryAddressRepository.findAll()));
    }

    private static AddressRepository addressRepository;

    private InMemoryAddressRepository(){}

    public static AddressRepository getInsatnce(){
        if(addressRepository==null){
            addressRepository=new InMemoryAddressRepository();
            return addressRepository;
        } else{
            return addressRepository;
        }
    }

    @Override
    public void add(Address address) {
        int lastAddressId = db.getLastId(Address.class);
        address.setId(++lastAddressId);
        db.setId(lastAddressId,Address.class);
        addresses.add(address);
        db.write(addresses,Address.class);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < addresses.size(); i++) {
            if(addresses.get(i).getId()==id){
                addresses.remove(addresses.get(i));
            }
        }
        db.write(addresses,Address.class);
    }

    @Override
    public Address findById(int id) {
        for (Address address : addresses) {
            if (address == null) break;
            if (address.getId() == id) {
                return address;
            }
        }
        return null;
    }

    @Override
    public Address findByName(String name) {
        for (Address address : addresses) {
            if (address == null) break;
            if (address.getAddress().equals(name)) {
                return address;
            }
        }
        return null;
    }

    @Override
    public Address[] findAll() {
        return addresses.toArray(new Address[0]);
    }
}
