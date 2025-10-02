package com.example.examen_01;

import java.util.*;
public class RestaurantRepository {
    private static RestaurantRepository INSTANCE; private final List<Restaurant> data=new ArrayList<>(); private int autoId=1;
    private RestaurantRepository(){ add("La Parrilla","Av. Sol #123","555-111-2222","Parrilla"); add("Sushi Go","Calle Luna 45","555-333-4444","Japonesa"); }
    public static synchronized RestaurantRepository get(){ if(INSTANCE==null) INSTANCE=new RestaurantRepository(); return INSTANCE;}
    public Restaurant add(String n,String d,String t,String tc){ Restaurant r=new Restaurant(autoId++,n,d,t,tc); data.add(r); return r; }
    public List<Restaurant> all(){ return new ArrayList<>(data); }
    public Restaurant findByName(String n){ for(Restaurant r:data) if(r.getNombre().equalsIgnoreCase(n.trim())) return r; return null;}
    public boolean deleteById(int id){ return data.removeIf(r->r.getId()==id); }
    public boolean update(Restaurant u){ for(int i=0;i<data.size();i++){ if(data.get(i).getId()==u.getId()){data.set(i,u); return true;}} return false;}
}
