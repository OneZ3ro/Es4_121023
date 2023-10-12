package angelomoreno.entities;

import java.util.Random;

public class Costumer {
    private long id;
    private String name;
    private int tier;

    public Costumer(String name) {
        Random rndm = new Random();
        this.name = name;
        this.tier = rndm.nextInt(1,3);
        this.id = rndm.nextInt(3000, 4000);
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                "}";
    }
}
