package by.bookstore.repository.file;

import by.bookstore.entity.Address;
import by.bookstore.repository.AddressRepository;
import by.bookstore.repository.DB;

import java.util.Arrays;
import java.util.List;

public class FileAddressRepository implements AddressRepository {
    private DB db = new FileDB();
    private List<Address> temp;

    public static void main(String[] args) {
        FileAddressRepository fileAddressRepository = new FileAddressRepository();
        fileAddressRepository.add(new Address("test"));
        fileAddressRepository.add(new Address("test2"));
        System.out.println(Arrays.toString(fileAddressRepository.findAll()));
    }

    {
        temp = db.read(Address.class);
    }

    @Override
    public void add(Address address) {
        int lastAddressId = db.getLastId(Address.class);
        address.setId(++lastAddressId);
        db.setId(lastAddressId,Address.class);
        temp.add(address);
        db.write(temp,Address.class);
    }

    @Override
    public void delete(int id) {
        for(Address address:temp){
            if(address==null) break;
            if(address.getId()==id){
                temp.remove(address);
            }
        }
        db.write(temp,Address.class);
    }

    @Override
    public Address findById(int id) {
        for(Address address: temp){
            if(address.getId()==id){
                return address;
            }
        }
        return null;
    }

    @Override
    public Address findByName(String name) {
        for (Address address : temp) {
            if(address.getAddress().equals(name)){
                return address;
            }
        }
        return null;
    }

    @Override
    public Address[] findAll() {
        return temp.toArray(new Address[0]);
    }
}
