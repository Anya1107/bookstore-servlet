package by.bookstore.repository.inmemory;

import by.bookstore.entity.City;
import by.bookstore.repository.CityRepository;
import by.bookstore.repository.DB;

import java.util.List;

public class InMemoryCityRepository implements CityRepository {
    private DB db = new InMemoryDB();
    private List<City> cities;

    {
        cities=db.read(City.class);
    }

    private static CityRepository cityRepository;

    private InMemoryCityRepository(){}

    public static CityRepository getInstance(){
        if(cityRepository==null){
            cityRepository=new InMemoryCityRepository();
            return cityRepository;
        } else {
            return cityRepository;
        }
    }

    @Override
    public void add(City city) {
        int lastCityId = db.getLastId(City.class);
        city.setId(++lastCityId);
        db.setId(lastCityId,City.class);
        cities.add(city);
        db.write(cities,City.class);
    }

    @Override
    public void delete(String name) {
        for (int i = 0; i < cities.size(); i++) {
            if(cities.get(i).getName().equals(name)){
                cities.remove(cities.get(i));
            }
        }
        db.write(cities,City.class);
    }

    @Override
    public City findByName(String name) {
        for (City cities : cities) {
            if (cities == null) break;
            if (cities.getName().equals(name)) {
                return cities;
            }
        }
        return null;
    }

    @Override
    public City[] findAll() {
        return cities.toArray(new City[0]);
    }
}
